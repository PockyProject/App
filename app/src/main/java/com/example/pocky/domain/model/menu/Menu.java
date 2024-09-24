package com.example.pocky.domain.model.menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {

    private int menuImage;
    private String breadName;
    private String qrBreadName;

    private List<String> sauceName;
    private List<String> qrSauceName;
    private List<String> toppingName;
    private List<String> qrToppingName;
    private String menuName;
    private String qrMenuName;
    private String qrSideName;
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

    public List<String> getSauceName() {
        return sauceName;
    }

    public void setSauceName(String sauceName) {

        if (getSauceName() == null) {
            this.sauceName = new ArrayList<>(); // sauce 필드에 빈 리스트 생성
        }

        if (getSauceName().isEmpty()) {
            getSauceName().add(sauceName);
        }else if(getSauceName().size() < 3){
            getSauceName().add(sauceName);
        }
        else {
            getSauceName().remove(0);
            getSauceName().add(sauceName);
        }
    }

    public List<String> getToppingName() {
        return toppingName;
    }

    public void setToppingName(String toppingName) {

        if (getToppingName() == null) {
            this.toppingName = new ArrayList<>(); // sauce 필드에 빈 리스트 생성
        }

        if (getToppingName().isEmpty()) {
            getToppingName().add(toppingName);
        }else if(getToppingName().size() < 3){
            getToppingName().add(toppingName);
        }
        else {
            getToppingName().remove(0);
            getToppingName().add(toppingName);
        }

    }

    public List<String> getQrToppingName() {
        return qrToppingName;
    }


    public void setQrToppingName(String qrToppingName) {

        if (getQrToppingName() == null) {
            this.qrToppingName = new ArrayList<>(); // sauce 필드에 빈 리스트 생성
        }

        if (getQrToppingName().isEmpty()) {
            getQrToppingName().add(qrToppingName);
        }else if(getQrToppingName().size() < 3){
            getQrToppingName().add(qrToppingName);
        }
        else {
            getQrToppingName().remove(0);
            getQrToppingName().add(qrToppingName);
        }
    }


    public void setQrSauceName(String qrSauceName) {

        if (getQrSauceName() == null) {
            this.qrSauceName = new ArrayList<>(); // sauce 필드에 빈 리스트 생성
        }

        if (getQrSauceName().isEmpty()){
            getQrSauceName().add(qrSauceName);
        }else if(getQrSauceName().size() < 3){
            getQrSauceName().add(qrSauceName);
        }

        else {
            getQrSauceName().remove(0);
            getQrSauceName().add(qrSauceName);
        }
    }

    public List<String> getQrSauceName() {
        return qrSauceName;
    }

    public void deleteQrSauceName(){
        this.qrSauceName = null;
    }

    public void deleteSauceName(){
        this.sauceName = null;
    }

    public void deleteQrToppingName(){
        this.qrToppingName = null;
    }

    public void deleteToppingName(){
        this.toppingName = null;
    }



    public void setqrSideName(String qrSideName) {
        this.qrSideName = qrSideName;
    }

    public String getQrBreadName() {
        return qrBreadName;
    }

    public void setQrBreadName(String qrBreadName) {
        this.qrBreadName = qrBreadName;
    }


    public String getQrSideName() {
        return qrSideName;
    }

    public String getQrMenuName() {
        return qrMenuName;
    }

    public void setQrMenuName(String qrMenuName) {
        this.qrMenuName = qrMenuName;
    }

}
