package com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.addFeedActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pocky.databinding.ActivityAddfeedBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.domain.repository.favor.Favor;

public class AddFeedActivity extends AppCompatActivity {

    private static ActivityAddfeedBinding binding;
    private Boolean isChooseFeed;
    private Boolean isChooseFavor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //바인딩 설정
        binding = ActivityAddfeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        isChooseFeed = intent.getBooleanExtra("isChooseFeed",false);
        isChooseFavor = intent.getBooleanExtra("isChooseFavor",false);

        if(isChooseFeed){
            Menu menu = MenuSingleton.getInstance();
            Log.d("AddFeedAcitivity","새로 만든 주문 : " + menu.getMenuName());

        }else if(isChooseFavor){
            Favor favor = (Favor) intent.getSerializableExtra("data");
            Log.d("AddFeedAcitivity","즐겨찾기에서 가져온 주문 : " + favor.getMenuName());
        }
    }
}
