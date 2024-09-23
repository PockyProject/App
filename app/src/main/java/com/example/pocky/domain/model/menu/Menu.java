package com.example.pocky.domain.model.menu;

public class Menu {

    private int menuImage;
    private String breadName;
    private String sauceName;
    private String toppingName;
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
    public String getBreadName() {
        return breadName;
    }

    public void setBreadName(String breadName) {
        this.breadName = breadName;
    }

    public String getSauceName() {
        return sauceName;
    }

    public void setSauceName(String sauceName) {
        this.sauceName = sauceName;
    }

    public String getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {
        this.toppingName = toppingName;
    }

}
