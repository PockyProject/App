package com.example.pocky.domain.model.recommend;

public class RecommendDTO {
    private String menu1;
    private String menu2;
    private String menu3;

    public RecommendDTO(String menu1, String menu2, String menu3) {
        this.menu1 = menu1;
        this.menu2 = menu2;
        this.menu3 = menu3;
    }

    public String getMenu1() {
        return menu1;
    }

    public String getMenu2() {
        return menu2;
    }

    public String getMenu3() {
        return menu3;
    }
}
