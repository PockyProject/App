package com.example.pocky.model.user;

import android.util.Log;
import android.widget.Toast;

public class UserInfo {
    private String nickname;
    private String profileURl;

    private UserInfo(){};
    public void init(String nickname, String profileURl){
                this.nickname = nickname;
                this.profileURl = profileURl;
    }

    private static class LazyHolder{
        private static final UserInfo INSTANCE = new UserInfo();
    }

    public static UserInfo getInstance(){
        return LazyHolder.INSTANCE;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileURl() {
        return profileURl;
    }
}
