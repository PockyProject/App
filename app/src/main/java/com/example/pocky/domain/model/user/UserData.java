package com.example.pocky.domain.model.user;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("userUid")
    private String useruid;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("userImage")
    private String userimage;
    @SerializedName("age")
    private int age;

    public UserData(String useruid, String nickname, String userimage, int age) {
        this.useruid = useruid;
        this.nickname = nickname;
        this.userimage = userimage;
        this.age = age;
    }

    public String getUseruid() {
        return useruid;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUserimage() {
        return userimage;
    }

    public int getAge() {
        return age;
    }
}
