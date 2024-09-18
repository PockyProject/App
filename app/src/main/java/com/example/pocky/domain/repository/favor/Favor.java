package com.example.pocky.domain.repository.favor;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;


// 테이블 정의

@Entity(tableName = "Favor") //테이블 이름 선언
public class Favor {
    @PrimaryKey // 기본키 선언
    @NonNull
    private String favorNumber;

    private int menuImage;
    private String menuName;

    private String bread;
    private String sauce;
    private String toping;
    private String side;
    private Boolean requid;

    public String getMenuName() {
        return menuName;
    }

    public String getSauce() {
        return sauce;
    }

    public int getMenuImage() {
        return menuImage;
    }


    public String  getFavorNumber() {
        return this.favorNumber;
    }

    public String getBread() {
        return bread;
    }


    public String getToping() {
        return toping;
    }


    public String getSide() {
        return side;
    }


    public Boolean getRequid() {
        return requid;
    }


    public Favor(int menuImage, String menuName, String favorNumber, String bread, String sauce, String toping, String side, Boolean requid) {
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

        Favor favor = (Favor) obj; // 형변환

        // 각각의 필드를 비교하여 동일한지 확인
        return menuImage == favor.getMenuImage() &&
                menuName.equals(favor.getMenuName()) &&
                Objects.equals(favorNumber, favor.getFavorNumber()) &&
                bread.equals(favor.getBread()) &&
                sauce.equals(favor.getSauce()) &&
                toping.equals(favor.getToping()) &&
                side.equals(favor.getSide()) &&
                requid.equals(favor.getRequid());
    }
}