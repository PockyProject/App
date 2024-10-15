package com.example.pocky.domain.repository.favor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


// 테이블 정의

@Entity(tableName = "Favor") //테이블 이름 선언
public class Favor implements Serializable {
    @PrimaryKey // 기본키 선언
    @NonNull
    private String favorNumber;

    private int menuImage;
    private String menuName;

    @Nullable
    private String bread;
    @Nullable
    private List<String> sauce;
    @Nullable
    private List<String> toping;
    private String side;
    private String requid;

    public String getMenuName() {
        return menuName;
    }

    public List<String> getSauce() {
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


    public List<String> getToping() {
        return toping;
    }


    public String getSide() {
        return side;
    }


    public String getRequid() {
        return requid;
    }

    public String getQrBreadName() {
        return qrBreadName;
    }

    public List<String> getQrSauceName() {
        return qrSauceName;
    }

    public List<String> getQrToppingName() {
        return qrToppingName;
    }

    public String getQrMenuName() {
        return qrMenuName;
    }

    public String getQrSideName() {
        return qrSideName;
    }
    public Boolean getQrRequid() {
        return qrRequid;
    }

    private String qrBreadName;
    private List<String> qrSauceName;
    private List<String> qrToppingName;
    private String qrMenuName;
    private String qrSideName;

    private Boolean qrRequid;


    public Favor(int menuImage, String menuName, String favorNumber,
                 String bread,  List<String> sauce, List<String> toping,
                 String side, String requid, String qrBreadName,
                 List<String> qrSauceName, List<String> qrToppingName,
                 String qrMenuName, String qrSideName, Boolean qrRequid)
    {
        this.menuImage = menuImage;
        this.menuName = menuName;
        this.favorNumber = favorNumber;
        this.bread = bread;
        this.sauce = sauce;
        this.toping = toping;
        this.side = side;
        this.requid = requid;
        this.qrBreadName = qrBreadName;
        this. qrSauceName = qrSauceName;
        this. qrToppingName = qrToppingName;
        this.qrMenuName = qrMenuName;
        this.qrSideName = qrSideName;
        this.qrRequid = qrRequid;
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
