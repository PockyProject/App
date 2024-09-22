package com.example.pocky.domain.model.menu;

public class Menu {

    private int menuImage;
    private String menuName;
    private String sideName;
    private Boolean requid;

    public int getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getSideName() {
        return sideName;
    }

    public void setSideName(String sideName) {
        this.sideName = sideName;
    }
    public Boolean getRequid() {
        return requid;
    }

    public void setRequid(Boolean requid) {
        this.requid = requid;
    }

}
