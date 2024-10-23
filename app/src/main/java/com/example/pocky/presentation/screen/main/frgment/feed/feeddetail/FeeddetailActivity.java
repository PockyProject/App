package com.example.pocky.presentation.screen.main.frgment.feed.feeddetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.pocky.databinding.ActivityFeeddetailBinding;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorModalBottomsheet;

public class FeeddetailActivity extends AppCompatActivity {

    private ActivityFeeddetailBinding binding;
    private FeedDetailViewModel viewModel;
    private FavorModalBottomsheet bottomsheet;
    private FeedData data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 뷰 초기화
        initView();

        // 피드 데이터 초기화
        initData();
    }

    private void initView(){

        // 뷰 초기화
        binding = ActivityFeeddetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 뷰모델 초기화
        FeedDetailViewModelFactory factory = new FeedDetailViewModelFactory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(FeedDetailViewModel.class);

        //QR 바텀 다이얼로그 초기화
        bottomsheet = new FavorModalBottomsheet();

        binding.feedqrOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // qr 띄우기
                bottomsheet.setQrBitmap(viewModel.decodeBase64ToBitmap(data.getQrImage()));
                bottomsheet.show(getSupportFragmentManager(),bottomsheet.getTag());
            }
        });
    }

    private void initData(){
        Intent intent = getIntent();
        data = (FeedData) intent.getSerializableExtra("FeedData");

        // 전달받은 이미지를 표시
        Glide.with(this)
                .load(data.getMenuImage())
                .into(binding.mainImage);

        binding.maincomment.setText(data.getTitle());
        binding.titleText.setText(data.getContent());
    }
}
