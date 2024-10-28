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
import com.example.pocky.presentation.screen.main.frgment.feed.bottomsheet.CommentBottomSheetDialog;
import com.example.pocky.presentation.screen.main.frgment.feed.bottomsheet.QrBottomSheet;

public class FeeddetailActivity extends AppCompatActivity {

    private ActivityFeeddetailBinding binding;
    private static final String TAG = "FeedDetailActivity";
    private FeedDetailViewModel viewModel;
    private QrBottomSheet qrBottomsheet;

    private CommentBottomSheetDialog commentBottomSheetDialog;

    private FeedData data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        data = (FeedData) intent.getSerializableExtra("FeedData");

        // 뷰 초기화
        initView();

    }

    private void initView(){

        // 뷰 초기화
        binding = ActivityFeeddetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 뷰모델 초기화
        FeedDetailViewModelFactory factory = new FeedDetailViewModelFactory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(FeedDetailViewModel.class);

        //QR 바텀 다이얼로그 초기화
        qrBottomsheet = new QrBottomSheet();

        // comment 바텀 다이얼로그 초기화
        commentBottomSheetDialog = new CommentBottomSheetDialog(data.getFeedUid());

        binding.feedqrOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // qr 띄우기
                qrBottomsheet.setQrBitmap(viewModel.decodeBase64ToBitmap(data.getQrImage()));
                qrBottomsheet.show(getSupportFragmentManager(),qrBottomsheet.getTag());
            }
        });

        binding.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 댓글 띄우기
                commentBottomSheetDialog.show(getSupportFragmentManager(),commentBottomSheetDialog.getTag());

            }
        });

        Glide.with(this)
                .load(data.getMenuImage())
                .into(binding.mainImage);

        binding.maincomment.setText(data.getTitle());
        binding.titleText.setText(data.getContent());
    }

}
