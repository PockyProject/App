    package com.example.pocky;

    import static android.content.ContentValues.TAG;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.TextView;

    import com.kakao.sdk.auth.model.OAuthToken;
    import com.kakao.sdk.common.KakaoSdk;
    import com.kakao.sdk.user.UserApiClient;
    import com.kakao.sdk.user.model.User;

    import kotlin.Function;
    import kotlin.UNumbersKt;
    import kotlin.Unit;
    import kotlin.jvm.functions.Function2;

    public class LoginActivity extends AppCompatActivity {

        private static final String TAG = "KakaoLogin";
        private View loginButton , logoutButton;
        private TextView nickName;

        private ImageView profileImage;

        private static class KakaoUserInfo {
            private long id;
            private String gender;
            private String ageRange;

            private String nickname;


            public KakaoUserInfo(long id, String gender, String ageRange,String nickname) {
                this.id = id;
                this.gender = gender;
                this.ageRange = ageRange;
                this.nickname = nickname;
            }

            public long getId() {
                return id;
            }

            public String getGender() {
                return gender;
            }

            public String getAgeRange() {
                return ageRange;
            }

            public String getNickname() { return nickname; }

        }

        private KakaoUserInfo userInfo; // 사용자 정보를 저장할 변수
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            Log.d("two", "starting");
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            KakaoSdk.init(this,"432c8e499713dc07778219f7dc6ab3b1");

            loginButton = findViewById(R.id.kakaologin);

            //카카오톡 설치 여부 확인 메서드
            Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
                @Override
                public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                    Log.e("00000813", "Callback Method");
                    //oAuthToken != null 이라면 로그인 성공
                    if (oAuthToken != null) {
                        updateKakaoLoginUi();
                    } else {
                        Log.e(TAG, "invoke: login fail");
                    }
                    return null;
                }
            };
            loginButton.setOnClickListener(new View.OnClickListener(){
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


        private void updateKakaoLoginUi() {

            UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                @Override
                public Unit invoke(User user, Throwable throwable) {
                    if (user != null) {
                        Log.d("KL ID", "invoke: id =" + user.getId());
                        Log.d("KL GENDER", "invoke: gender = "+ user.getKakaoAccount().getGender());
                        Log.d("KL AGE", "invoke: age="+ user.getKakaoAccount().getAgeRange());
                        Log.d("KL NIck", "invoke: id =" + user.getKakaoAccount().getProfile().getNickname());
                        Log.d("KL profile",user.getKakaoAccount().getProfile().getThumbnailImageUrl());

                        Intent nextintent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(nextintent);
                        finish();
                    }
                    else{
                        Log.d("loginfail","로그인이 안되어있음");
                    }
                    return null;
                }
            });

        }



    }
