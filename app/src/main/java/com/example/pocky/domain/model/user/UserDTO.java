package com.example.pocky.domain.model.user;

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

    public void setId(String id) {
        this.id = id;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }


}
