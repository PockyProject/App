package com.example.pocky.domain.model.menu;

public class MenuSingleton {
    private static Menu menuArr;


    public static Menu getInstance(){
        if(menuArr == null){
            menuArr = new Menu();
        }
        return menuArr;
    }
}
