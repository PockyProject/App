package com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.addFeedActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pocky.databinding.ActivityAddfeedBinding;
import com.example.pocky.domain.repository.favor.Favor;

public class AddFeedActivity extends AppCompatActivity {

    private static ActivityAddfeedBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //바인딩 설정
        binding = ActivityAddfeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Favor favor = (Favor) intent.getSerializableExtra("data");
        Log.d("AddFeedAcitivity",favor.getMenuName());


    }
}
