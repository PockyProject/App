package com.example.pocky.domain.repository;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.pocky.domain.model.Favor.FavorModel;


// 테이블 정의

@Entity(tableName = "Favor") //테이블 이름 선언
public class Favor {
    @PrimaryKey(autoGenerate = true) // 기본키 선언
    private int favorNumber;

    private int menuImage;
    private String menuName;

    private String bread;
    private String sauce;
    private String toping;
    private String side;

    public String getMenuName() {
        return menuName;
    }

    public String getSauce() {
        return sauce;
    }

    private String requid;

    public int getMenuImage() {
        return menuImage;
    }

    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getFavorNumber() {
        return this.favorNumber;
    }

    public void setFavorNumber(int favorNumber) {
        this.favorNumber = favorNumber;
    }

    public String getBread() {
        return bread;
    }

    public void setBread(String bread) {
        this.bread = bread;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public String getToping() {
        return toping;
    }

    public void setToping(String toping) {
        this.toping = toping;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getRequid() {
        return requid;
    }

    public void setRequid(String requid) {
        this.requid = requid;
    }

    public Favor(int menuImage, String menuName, int favorNumber, String bread, String sauce, String toping, String side, String requid) {
        this.menuImage = menuImage;
        this.menuName = menuName;
        this.favorNumber = favorNumber;
        this.bread = bread;
        this.sauce = sauce;
        this.toping = toping;
        this.side = side;
        this.requid = requid;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // 동일한 객체일 경우
        if (obj == null || getClass() != obj.getClass()) return false; // 클래스 타입이 다르면 다름

        FavorModel favor = (FavorModel) obj; // 형변환

        // 각각의 필드를 비교하여 동일한지 확인
        return menuImage == favor.getMenuImage() &&
                menuName.equals(favor.getMenuName()) &&
                favorNumber == favor.getFavorNumber() &&
                bread.equals(favor.getBread()) &&
                sauce.equals(favor.getSauce()) &&
                toping.equals(favor.getToping()) &&
                side.equals(favor.getSide()) &&
                requid.equals(favor.getRequid());
    }
}
