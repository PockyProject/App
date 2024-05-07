package com.example.pocky.data.user;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDTO {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Nickname")
    @Expose
    private String NickName;

    public UserDTO(String id, String nickName) {
        this.id = id;
        this.NickName = nickName;
    }
    public String getNickName() {
        return NickName;
    }
    public String getId() {
        return id;
    }

}
