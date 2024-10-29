package com.example.pocky.domain.model.recommend;

public class viewRecommendDTO {
    private String menuName;
    private int menuImage;
    private String menuQrName;

    public String getMenuQrName() {
        return menuQrName;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuImage() {
        return menuImage;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }
    public void setMenuQrName(String menuQrName) {
        this.menuQrName = menuQrName;
    }

}
