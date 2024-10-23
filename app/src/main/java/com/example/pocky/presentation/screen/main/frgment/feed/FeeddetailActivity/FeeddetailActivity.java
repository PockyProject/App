package com.example.pocky.presentation.screen.main.frgment.feed.FeeddetailActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pocky.databinding.ActivityFeeddetailBinding;
import com.example.pocky.domain.model.feed.FeedData;

public class FeeddetailActivity extends AppCompatActivity {

    private ActivityFeeddetailBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 뷰 초기화
        initView();

        // 피드 데이터 초기화
        initData();
    }

    private void initView(){
        binding = ActivityFeeddetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()); // Feeddetail 화면의 레이아웃 설정
    }

    private void initData(){
        Intent intent = getIntent();
        FeedData data = (FeedData) intent.getSerializableExtra("FeedData");

        // 전달받은 이미지를 표시
        Glide.with(this)
                .load(data.getMenuImage())
                .into(binding.mainImage);

        binding.maincomment.setText(data.getTitle());
        binding.titleText.setText(data.getContent());
    }
}
