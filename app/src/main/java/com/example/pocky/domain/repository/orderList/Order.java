package com.example.pocky.domain.repository.orderList;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.pocky.domain.repository.favor.Favor;

import java.util.Objects;

@Entity(tableName = "Order")
public class Order {

    @PrimaryKey // 기본키 선언
    @NonNull
    private String orderNumber;

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


    public String  getOrderNumber() {
        return this.orderNumber;
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


    public Order(int menuImage, String menuName, String orderNumber, String bread, String sauce, String toping, String side, Boolean requid) {
        this.menuImage = menuImage;
        this.menuName = menuName;
        this.orderNumber = orderNumber;
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

        Order order = (Order) obj; // 형변환

        // 각각의 필드를 비교하여 동일한지 확인
        return menuImage == order.getMenuImage() &&
                menuName.equals(order.getMenuName()) &&
                Objects.equals(orderNumber, order.getOrderNumber()) &&
                bread.equals(order.getBread()) &&
                sauce.equals(order.getSauce()) &&
                toping.equals(order.getToping()) &&
                side.equals(order.getSide()) &&
                requid.equals(order.getRequid());
    }
}
