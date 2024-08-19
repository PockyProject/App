package com.example.pocky.mainActivity;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.pocky.R;
import com.example.pocky.model.main.MainMenuDTO;
import com.example.pocky.model.user.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MainMenuDTO arr;

    public String initUserData(){
        if (UserInfo.getInstance() != null) {
            try{
                Log.e("MainActivity","userNickname : " + UserInfo.getInstance().getNickname());
                return UserInfo.getInstance().getNickname();
            }catch (NullPointerException e){
                Log.e("MainActivity","유저 정보 객체 호출 실패");
            }
        } else {
            Log.e("MainActivity","userInfo is null");
        }

        return "오류발생";
    }

//    public MainMenuDTO initMenuRecyclerView(){
//        MainMenuDTO arr = new MainMenuDTO();
//        List<String> name = new ArrayList<String>();
//        List<Integer> menuPhoto = new ArrayList<Integer>();
//        name.add("애그마요랩");
//        menuPhoto.add(R.drawable.hameggchezzrap);
//
//        arr.setMenuName(name);
//        arr.setMenuPhoto(mez);
//
//       // arr = new MainMenuDTO(name,menuPhoto);
//        return arr;
//    }

    public List<String> initMenuName(){
        List<String> arr = new ArrayList<>();
        arr.add("아침메뉴");
        arr.add("샐러드");
        arr.add("샌드위치");
        arr.add("랩 및 기타");
        arr.add("그룹메뉴");
        arr.add("스마일 썹");

        return arr;
    };

    public List<Integer> initMenuImg(){
        List<Integer> arr = new ArrayList<>();
        arr.add(R.drawable.resize_hamcheeze);
        arr.add(R.drawable.resize_bltsalad);
        arr.add(R.drawable.resize_foldfork);
        arr.add(R.drawable.resize_shrimpeggmayorap);
        arr.add(R.drawable.resize_bestpartyflatter);
        arr.add(R.drawable.resize_doublechokochip);

        return arr;
    };


}
