package com.example.pocky.domain.repository.Favor;

import androidx.room.Entity;
import androidx.room.PrimaryKey;



// 테이블 정의

@Entity(tableName = "Favor") //테이블 이름 선언
public class Favor {
        @PrimaryKey(autoGenerate = true) // 기본키 선언
        private int id;


        // 테이블 속성 선언
        private int age;
        private String menuName;
        private String breadCategory;
        private String sauce;
        private String topping;


        // 생성자
        public Favor(int age, String menuName, String breadCategory, String sauce, String topping) {
            this.age = age;
            this.menuName = menuName;
            this.breadCategory = breadCategory;
            this.sauce = sauce;
            this.topping = topping;
        }

        // Getter 및 Setter
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public String getBreadCategory() {
            return breadCategory;
        }

        public void setBreadCategory(String breadCategory) {
            this.breadCategory = breadCategory;
        }

        public String getSauce() {
            return sauce;
        }

        public void setSauce(String sauce) {
            this.sauce = sauce;
        }

        public String getTopping() {
            return topping;
        }

        public void setTopping(String topping) {
            this.topping = topping;
        }
    }
