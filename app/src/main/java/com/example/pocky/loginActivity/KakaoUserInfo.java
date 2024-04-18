package com.example.pocky.loginActivity;

public class KakaoUserInfo {
    private static final String TAG = "KakaoLogin";

    private String nickname;
    private long id;
    private String profilePhotoUrl;

    public KakaoUserInfo(long id, String nickname, String profilePhotoUrl) {
        this.id = id;
        this.nickname = nickname;
        this.profilePhotoUrl = profilePhotoUrl;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
