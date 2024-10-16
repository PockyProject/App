    package com.example.pocky.presentation.screen.loginActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.lifecycle.ViewModelProvider;

    import com.example.pocky.BuildConfig;
    import com.example.pocky.databinding.ActivityLoginBinding;
    import com.example.pocky.domain.model.user.UserInfo;
    import com.example.pocky.presentation.screen.main.MainActivity;
    import com.kakao.sdk.auth.model.OAuthToken;
    import com.kakao.sdk.common.KakaoSdk;
    import com.kakao.sdk.user.UserApiClient;
    import com.kakao.sdk.user.model.User;

    import kotlin.Unit;
    import kotlin.jvm.functions.Function2;

    public class LoginActivity extends AppCompatActivity {
        private ActivityLoginBinding binding; // 바인딩
        private final String KAKAO_KEY = BuildConfig.kakaoSDK;

        private static LoginViewModel viewModel;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            binding = ActivityLoginBinding.inflate(getLayoutInflater());
            View view = binding.getRoot();
            super.onCreate(savedInstanceState);
            setContentView(view);

            //뷰모델 초기화
            LoginViewModelFactory factory = new LoginViewModelFactory(this.getApplication());
            viewModel = new ViewModelProvider(this, factory).get(LoginViewModel.class);

            //카카오 init
            KakaoSdk.init(this,KAKAO_KEY);

            Log.d("LoginActivity",KAKAO_KEY);

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
                        Log.d("User","연령대 :" + user.getKakaoAccount().getAgeRange());
                        //아이디, 닉네임, 프로필 url 담아서 UserInfo 객체 선언
                        Log.e("로그인",user.getKakaoAccount().getProfile().getThumbnailImageUrl());
                        UserInfo.getInstance().init(
                                user.getId().toString(),
                                user.getKakaoAccount().getProfile().getNickname().toString(),
                                user.getKakaoAccount().getProfile().getThumbnailImageUrl().toString()
                        );
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
    }
