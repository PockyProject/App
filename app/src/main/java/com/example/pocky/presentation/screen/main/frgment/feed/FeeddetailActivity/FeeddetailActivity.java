package com.example.pocky.presentation.screen.main.frgment.feed.FeeddetailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.pocky.R;

public class FeeddetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeddetail); // Feeddetail 화면의 레이아웃 설정

        ImageView mainImage = findViewById(R.id.mainImage);
        TextView maintitleText = findViewById(R.id.titleText);
        TextView mainContent = findViewById(R.id.maincomment);

        Intent intent = getIntent();
        int menuImage = intent.getIntExtra("menuImage", R.drawable.baseline_arrow_back_24); // 기본 이미지 설정
        String titleText = intent.getStringExtra("titleText");
        String mainText = intent.getStringExtra("mainText");
        // 전달받은 이미지를 표시
        Glide.with(this)
                .load(menuImage)
                .into(mainImage);
        maintitleText.setText(titleText);
        mainContent.setText(mainText);




    }
}
