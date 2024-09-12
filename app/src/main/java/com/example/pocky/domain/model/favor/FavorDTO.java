package com.example.pocky.domain.model.favor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FavorDTO {
    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("f1")
    @Expose
    private String f1;

    @SerializedName("f2")
    @Expose
    private String f2;

    @SerializedName("f3")
    @Expose
    private String f3;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }
}
