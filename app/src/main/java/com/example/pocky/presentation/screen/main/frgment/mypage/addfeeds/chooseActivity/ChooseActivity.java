package com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.chooseActivity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pocky.databinding.ActivitiyChooseBinding;

public class ChooseActivity extends AppCompatActivity {

    private static ActivitiyChooseBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //바인딩 설정
        binding = ActivitiyChooseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}
