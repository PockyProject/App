package com.example.pocky.model.main;

import java.util.List;

public class MainMenuDTO{
//    public MainMenuDTO(List<String> menuName, List<Integer> menuPhoto) {
//        this.menuName = menuName;
//        this.menuPhoto = menuPhoto;
//    }

    public List<String> getMenuName() {
        return menuName;
    }

    public void setMenuName(List<String> menuName) {
        this.menuName = menuName;
    }

    public List<Integer> getMenuPhoto() {
        return menuPhoto;
    }

    public void setMenuPhoto(List<Integer> menuPhoto) {
        this.menuPhoto = menuPhoto;
    }

    List<String> menuName;
    List<Integer> menuPhoto;
}
