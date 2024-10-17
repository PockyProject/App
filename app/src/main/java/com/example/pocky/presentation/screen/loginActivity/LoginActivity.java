    package com.example.pocky.presentation.screen.loginActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.lifecycle.ViewModelProvider;

    import com.example.pocky.BuildConfig;
    import com.example.pocky.databinding.ActivityLoginBinding;
    import com.example.pocky.domain.model.user.UserData;
    import com.example.pocky.domain.model.user.UserInfo;
    import com.example.pocky.presentation.screen.main.MainActivity;
    import com.kakao.sdk.auth.model.OAuthToken;
    import com.kakao.sdk.common.KakaoSdk;
    import com.kakao.sdk.user.UserApiClient;
    import com.kakao.sdk.user.model.User;

    import java.util.StringTokenizer;
    import java.util.concurrent.ExecutorService;
    import java.util.concurrent.Executors;

    import kotlin.Unit;
    import kotlin.jvm.functions.Function2;

    public class LoginActivity extends AppCompatActivity {
        private final static String TAG = "LoginActivity";
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
            LoginViewModelFactory factory = new LoginViewModelFactory(getApplication());
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
                            Log.d("User", "ProfileUrl :" + user.getKakaoAccount().getProfile().getThumbnailImageUrl());
                            Log.d("User", "연령대 :" + user.getKakaoAccount().getAgeRange());

                            String userid = String.valueOf(user.getId());
                            String nickName = String.valueOf(user.getKakaoAccount().getProfile().getNickname());
                            String profileUrl = user.getKakaoAccount().getProfile().getThumbnailImageUrl();
                            String age = String.valueOf(user.getKakaoAccount().getAgeRange());

                            // 받아온 연령 사용하는 부분만 자르기
                            int convertAge = Integer.parseInt(age.substring(4,6));

                            Log.d(TAG,"변환한 나이 : " + convertAge);

                            // 데이터를 백그라운드 스레드에서 처리
                            ExecutorService executor = Executors.newSingleThreadExecutor();
                            executor.execute(() ->{
                                Log.d("Thread", "쓰레드 실행");

                                //DB에 저장할 유저 데이터 만들기
                                UserData temp = makeUserdata(
                                        userid,
                                        nickName,
                                        profileUrl,
                                        convertAge
                                );

                                // DB 저장
                                viewModel.postUserInfo(temp);
                            });


                            //아이디, 닉네임, 프로필 url 담아서 UserInfo 객체 선언
                            Log.e("로그인", user.getKakaoAccount().getProfile().getThumbnailImageUrl());
                            UserInfo.getInstance().init(
                                    userid,
                                    nickName,
                                    profileUrl
                            );


                            Intent nextintent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(nextintent);
                            finish();
                        } else {
                            Log.d("로그인", "로그인 실패");
                        }
                        return null;
                    }
                });

        }


        private UserData makeUserdata(String useruid, String nickname, String userimage, int age){
            return new UserData(
                    useruid,
                    nickname,
                    userimage,
                    age
            );
        }


            public int convertAgeFormat(String arr){
                int count = 0;
                int age = 0;
                Log.d("convertAgeFormat", String.valueOf(count));
                Log.d("convertAgeFormat", "기존 문자열: "+ arr);
                StringTokenizer st = new StringTokenizer(arr,"_");
                try{
                    Log.d("convertAgeFormat", "토큰 개수 : "+String.valueOf(st.countTokens()));
                    Log.d("convertAgeFormat", "토큰 개수 : "+String.valueOf(st.nextToken()));
                    Log.d("convertAgeFormat", "토큰 개수 : "+String.valueOf(st.nextToken()));
                    Log.d("convertAgeFormat", "토큰 개수 : "+String.valueOf(st.nextToken()));

                    while (st.hasMoreTokens()){
                        if(count == 1){
                            age = Integer.parseInt(st.nextToken());
                            Log.d("convertAgeFormat", "가져온 포맷 " + age);
                            break;
                        }
                        count++;
                        Log.d("convertAgeFormat", String.valueOf(count));
                    }
                    Log.d("convertAgeFormat", String.valueOf(count));
                } catch (Exception e){
                    Log.d(TAG,"나이 문자열 포맷 오류 : " + e.getCause());
                    Log.d(TAG,"나이 문자열 포맷 오류 : " + e.getMessage());
                    return 0;
                }

                Log.d("convertAgeFormat", "최종 포맷 " + age);
                return age;
            }

    }
