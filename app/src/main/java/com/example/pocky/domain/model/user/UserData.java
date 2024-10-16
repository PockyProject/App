package com.example.pocky.domain.model.user;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("useruid")
    private String useruid;
    @SerializedName("nickname")
    private String nickname;
    @SerializedName("userimage")
    private String userimage;
    @SerializedName("age")
    private int age;

}
