package com.example.pocky.presentation.screen.main.frgment.feed.FeeddetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pocky.R;

public class FeeddetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeddetail); // Feeddetail 화면의 레이아웃 설정

        ImageView mainImage = findViewById(R.id.feedmainImg); // XML에 있는 mainImage ID를 참조

        Intent intent = getIntent();
        int feedImage = intent.getIntExtra("feed_image", R.drawable.baseline_arrow_back_24); // 기본 이미지 설정
        Log.e("FeeddetailActivity", "Received image: " + feedImage);

        // 전달받은 이미지를 표시
        Glide.with(this)
                .load(feedImage)
                .into(mainImage);
    }
}
