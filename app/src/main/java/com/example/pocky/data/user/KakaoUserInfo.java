package com.example.pocky.data.user;

import java.io.Serializable;

public class KakaoUserInfo implements Serializable {
    private static final String TAG = "KakaoLogin";

    private String nickname;
    private String profilePhotoUrl;

    public KakaoUserInfo(String nickname, String profilePhotoUrl) {
        this.nickname = nickname;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    public void setProfilePhotoUrl(String profilePhotoUrl) {
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }





}
