    package com.example.pocky.presentation.screen.loginActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;

    import androidx.appcompat.app.AppCompatActivity;

    import com.example.pocky.BuildConfig;
    import com.example.pocky.databinding.ActivityLoginBinding;
    import com.example.pocky.domain.model.RetrofitService;
    import com.example.pocky.domain.model.user.UserDTO;
    import com.example.pocky.domain.model.user.UserInfo;
    import com.example.pocky.domain.model.user.UserInterface;
    import com.example.pocky.presentation.screen.main.MainActivity;
    import com.kakao.sdk.auth.model.OAuthToken;
    import com.kakao.sdk.common.KakaoSdk;
    import com.kakao.sdk.user.UserApiClient;
    import com.kakao.sdk.user.model.User;

    import java.util.List;

    import kotlin.Unit;
    import kotlin.jvm.functions.Function2;
    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class LoginActivity extends AppCompatActivity {
        private ActivityLoginBinding binding; // 바인딩
        public static UserInterface user = RetrofitService.getInstance().getRetrofit().create(UserInterface.class); //레트로핏 객체 불러오기
        private UserDTO setUser;
        private final String KAKAO_KEY = BuildConfig.kakaoSDK;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            binding = ActivityLoginBinding.inflate(getLayoutInflater());
            View view = binding.getRoot();
            super.onCreate(savedInstanceState);
            setContentView(view);

            //카카오 init
            KakaoSdk.init(this,KAKAO_KEY);

            //카카오톡 설치 여부 확인 메서드
            Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
                @Override
                public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                    //oAuthToken != null 이라면 로그인 성공
                    if (oAuthToken != null) {
                        Log.e("토큰인증","토큰인증 성공");
                        updateKakaoLoginUi();
                    } else {
                        Log.e("토큰인증", "토큰인증 실패");
                    }
                    return null;
                }
            };
            binding.kakaologinBtn.setOnClickListener(new View.OnClickListener(){ //로그인btn 이벤트
                @Override
                public void onClick(View view){
                    if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(LoginActivity.this)){
                        UserApiClient.getInstance().loginWithKakaoTalk(LoginActivity.this, callback);
                    }
                    else{
                        UserApiClient.getInstance().loginWithKakaoAccount(LoginActivity.this , callback);
                    }
                }
            });

        }

        //카카오 로그인
        private void updateKakaoLoginUi() {
            UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                @Override
                public Unit invoke(User user, Throwable throwable) {
                    if (user != null) {
                        Log.d("User", "id : " + user.getId());
                        Log.d("User", "NickName : " + user.getKakaoAccount().getProfile().getNickname());
                        Log.d("User","ProfileUrl :" + user.getKakaoAccount().getProfile().getThumbnailImageUrl());
                        //아이디, 닉네임, 프로필 url 담아서 UserInfo 객체 선언
                        //userInfo = new KakaoUserInfo(user.getId(), userInfo.getNickname(),user.getKakaoAccount().getProfile().getProfileImageUrl());
                        callApi(String.valueOf(user.getId()),user.getKakaoAccount().getProfile().getNickname());
                        Log.e("로그인",user.getKakaoAccount().getProfile().getThumbnailImageUrl());
                        UserInfo.getInstance().init(user.getKakaoAccount().getProfile().getNickname().toString(),
                                user.getKakaoAccount().getProfile().getThumbnailImageUrl().toString());
                        //Log.e("User",UserInfo.getInstance().getNickname());
                        Intent nextintent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(nextintent);
                        finish();
                    }
                    else{
                        Log.d("로그인","로그인 실패");
                    }
                    return null;
                }
            });

        }

        //API 호출
        private void callApi(String id, String nickName){
            Call<List<UserDTO>> GetCall = user.getId(id,nickName);
            GetCall.enqueue(new Callback<List<UserDTO>>() {
                @Override
                public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {
                    List<UserDTO> data = response.body();
                    if(response.isSuccessful()){
                        if(data.isEmpty()){
                            call(id,nickName);
                        }else{
                            Log.e("DB","기존회원");
                            Log.e("DB","기존회원 : " + data.get(0).getId());
                        }
                    }else{
                      Log.e("DB",response.errorBody().toString());

                    }
                }
                @Override
                public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                    Log.e("DB", "DB호출 실패" + t.getMessage().toString());
                }
            });
        }

        private  void call(String id, String nickName){
            setUser = new UserDTO(id,nickName);
            Call<UserDTO> PostCall = user.setUserInfo(setUser);
            PostCall.enqueue(new Callback<UserDTO>() {
                @Override
                public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                    Log.e("DB","Post 성공" + response.message());
                }
                @Override
                public void onFailure(Call<UserDTO> call, Throwable t) {
                    Log.e("DB","Post 실패" +  t.getMessage().toString());
                }
            });
        };
    }
