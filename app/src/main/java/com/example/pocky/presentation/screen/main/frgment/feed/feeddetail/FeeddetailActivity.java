package com.example.pocky.presentation.screen.main.frgment.feed.feeddetail;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.pocky.R;
import com.example.pocky.databinding.ActivityFeeddetailBinding;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.presentation.screen.main.frgment.feed.bottomsheet.CommentBottomSheetDialog;
import com.example.pocky.presentation.screen.main.frgment.feed.bottomsheet.QrBottomSheet;

public class FeeddetailActivity extends AppCompatActivity {

    private ActivityFeeddetailBinding binding;
    private static final String TAG = "FeedDetailActivity";
    private FeedDetailViewModel viewModel;
    private QrBottomSheet qrBottomsheet;

    private CommentBottomSheetDialog commentBottomSheetDialog;

    private FeedData data;
    private int likeCount;
    private Boolean isLiked;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        data = (FeedData) intent.getSerializableExtra("FeedData");
        likeCount = data.getLikeCount();
        Log.d(TAG, "초기 좋아요 개수 : " + String.valueOf(likeCount));
        initIsLiked(likeCount);

        // 뷰 초기화
        initView();

    }

    private void initView() {

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

        //좋아요 버튼 초기화
        if (!isLiked) {
            binding.feedlikeBtn.setImageResource(R.drawable.likecount_empty);
        } else {
            binding.feedlikeBtn.setImageResource(R.drawable.likecount_filed);
        }

        binding.feedqrOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // qr 띄우기
                qrBottomsheet.setQrBitmap(viewModel.decodeBase64ToBitmap(data.getQrImage()));
                qrBottomsheet.show(getSupportFragmentManager(), qrBottomsheet.getTag());
            }
        });

        binding.commentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 댓글 띄우기
                commentBottomSheetDialog.show(getSupportFragmentManager(), commentBottomSheetDialog.getTag());

            }
        });

        binding.feedlikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isLiked) {
                    // 좋아요가 안된 상태에서 좋아요 증가
                    binding.feedlikeBtn.setImageResource(R.drawable.likecount_filed);
                    likeCount += 1;
                    isLiked = true;
                    viewModel.postLikeCount(makeCount(likeCount));
                    binding.likeCountTextView.setText(likeCount+"명이\n좋아합니다");
                } else {
                    // 이미 좋아요 상태일 때 좋아요 취소
                    if (likeCount > 0) {
                        binding.feedlikeBtn.setImageResource(R.drawable.likecount_empty);
                        likeCount -= 1;
                        viewModel.postLikeCount(makeCount(likeCount));
                        binding.likeCountTextView.setText(likeCount+"명이\n좋아합니다");
                    }
                    isLiked = false;
                    viewModel.postLikeCount(makeCount(likeCount));
                    binding.likeCountTextView.setText(likeCount+"명이\n좋아합니다");
                }
                Log.d(TAG, "현재 좋아요 개수 :" + likeCount);
            }
        });

        Glide.with(this)
                .load(data.getMenuImage())
                .into(binding.mainImage);

        binding.maincomment.setText(data.getTitle());
        binding.titleText.setText(data.getContent());
    }

    void initIsLiked(int count) {
        if (count < 1) {
            isLiked = false;
        } else {
            isLiked = true;
        }
    }

    FeedData makeCount(int likeCount){
        FeedData temp = new FeedData(
                data.getFeedUid(),
                UserInfo.getInstance().getUserId(),
                data.getTitle(),
                data.getContent(),
                likeCount,
                data.getWritedDate(),
                data.getDeleteAt(),
                data.getUpdateAt(),
                data.getQrImage(),
                data.getMenuImage()
        );
        Log.d(TAG,"테스트 "+UserInfo.getInstance().getUserId());
        return temp;
    }


}
