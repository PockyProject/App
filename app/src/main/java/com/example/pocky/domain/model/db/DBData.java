package com.example.pocky.domain.model.db;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DBData {
    @SerializedName("userId")
    private String userId;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("menuImage")
    private int menuImage;

    @SerializedName("bread")
    private String bread;

    @SerializedName("sauce")
    private List<String> sauce;

    @SerializedName("topping")
    private List<String> topping;

    @SerializedName("liquid")
    private boolean liquid;

    public DBData(String userId, String nickname, int menuImage, String bread, List<String> sauce, List<String> topping, boolean liquid) {
        this.userId = userId;
        this.nickname = nickname;
        this.menuImage = menuImage;
        this.bread = bread;
        this.sauce = sauce;
        this.topping = topping;
        this.liquid = liquid;
    }

}
