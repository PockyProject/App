package com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.chooseActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pocky.databinding.ActivitiyChooseBinding;
import com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.chooseActivity.feedFavorActivity.FeedFavorActivity;
import com.example.pocky.presentation.screen.order.sandwitch.SandwitchActivity;

public class ChooseActivity extends AppCompatActivity {

    private static ActivitiyChooseBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }



    public void initView(){
        //바인딩 설정
        binding = ActivitiyChooseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }


    public void initListener(){
        binding.gotoFavorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FeedFavorActivity.class);
                startActivity(intent);
            }
        });

        binding.gotoOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent = new Intent(getApplicationContext(), SandwitchActivity.class);
                  intent.putExtra("isChooseFeed",true);
                  startActivity(intent);
            }
        });
    }
}
