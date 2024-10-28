package com.example.pocky.presentation.screen.main.frgment.feed.bottomsheet;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.databinding.ItemCommentInputBinding;
import com.example.pocky.databinding.ItemCommentViewBinding;
import com.example.pocky.domain.model.comment.CommentData;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.presentation.screen.main.frgment.feed.feeddetail.FeedDetailViewModel;
import com.example.pocky.presentation.screen.main.frgment.feed.feeddetail.FeedDetailViewModelFactory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class CommentBottomSheetDialog extends BottomSheetDialogFragment {

    private ItemCommentViewBinding binding;
    private ItemCommentInputBinding editBinding;
    private RecyclerView recyclerView;
    private CommentAdapter adpater;
    private FeedDetailViewModel viewModel;
    private String feedUid;
    private UserInfo userInfo;

    public CommentBottomSheetDialog(String feeduid){
        this.feedUid = feeduid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = ItemCommentViewBinding.inflate(inflater,container,false);
        editBinding = ItemCommentInputBinding.inflate(inflater,container,false);


        // 뷰모델 초기화
        FeedDetailViewModelFactory factory = new FeedDetailViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, factory).get(FeedDetailViewModel.class);

        return binding.getRoot();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        bottomSheetDialog.setOnShowListener(dialog -> {
            FrameLayout rootLayout = bottomSheetDialog.findViewById(com.google.android.material.R.id.container);

            if (rootLayout != null) {

                // rootLayout에 뷰 추가 전, 기존 부모가 있으면 제거
                if (binding.getRoot().getParent() != null) {
                    ((ViewGroup) binding.getRoot().getParent()).removeView(binding.getRoot());
                }
                if (editBinding.getRoot().getParent() != null) {
                    ((ViewGroup) editBinding.getRoot().getParent()).removeView(editBinding.getRoot());
                }

                rootLayout.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

                // LinearLayout 생성 및 설정
                LinearLayout linearLayout = new LinearLayout(requireContext());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setLayoutParams(new FrameLayout.LayoutParams(
                        FrameLayout.LayoutParams.MATCH_PARENT,
                        FrameLayout.LayoutParams.MATCH_PARENT
                ));

                // binding과 editBinding 추가
                linearLayout.addView(binding.getRoot(), new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT, 1f  // binding을 화면의 남은 공간을 채우도록 함
                ));

                linearLayout.addView(editBinding.getRoot(), new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT // editBinding이 화면의 아래쪽에 고정되도록 함
                ));

                // LinearLayout을 rootLayout에 추가
                rootLayout.addView(linearLayout);
            } else {
                // Log or handle case where rootLayout is null
                Log.e("CommentBottomSheet", "Root layout not found in BottomSheetDialog.");
            }
        });

        return bottomSheetDialog;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = binding.commentRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adpater = new CommentAdapter(viewModel);
        recyclerView.setAdapter(adpater);
    }

    @Override
    public void onResume() {
        super.onResume();

        viewModel.getComment(feedUid);
        userInfo = UserInfo.getInstance();

        viewModel.getData().observe((LifecycleOwner) requireContext(), commentData -> {
            adpater.submitList(commentData);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        initBottomSheetDialog();

        editBinding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp = editBinding.textInputLayer.getEditText().getText().toString();

                if(!temp.isEmpty()){
                    viewModel.postComment(initCommentData(temp));
                    editBinding.textInputLayer.getEditText().setText("");
                }else{
                    Toast.makeText(requireContext(),"한 글자 이상 입력하세요",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private CommentData initCommentData(String content){
        CommentData data = new CommentData(
                UUID.randomUUID().toString(),
                feedUid,
                userInfo.getNickname(),
                userInfo.getProfileURl(),
                content,
                calcCurrentTime(),
                0,
                calcCurrentTime(),
                calcCurrentTime(),
                userInfo.getUserId()
        );
        return data;
    }

    private void initBottomSheetDialog() {
        // BottomSheetDialog를 가져옵니다.
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) getDialog();

        // design_bottom_sheet ID를 통해 BottomSheet의 루트 레이아웃을 찾습니다.
        View bottomSheet = bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);

        if (bottomSheet != null) {

            bottomSheet.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;

            BottomSheetBehavior<View> behavior = BottomSheetBehavior.from(bottomSheet);

            behavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);

            behavior.setDraggable(true);

            behavior.setPeekHeight(0);

            behavior.setHideable(false);

            BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    // BottomSheet가 완전히 접힌 상태일 때 다이얼로그를 닫습니다.
                    if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                        dismiss();
                    }
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            };

            behavior.addBottomSheetCallback(bottomSheetCallback);
        }
    }


    // 현재 시간 구하는 함수
    private Timestamp calcCurrentTime(){

        // 현재 날짜와 시간 구하기
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 정확한 형식으로 변환 (yyyy-MM-dd HH:mm:ss)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        // 변환된 문자열을 Timestamp로 변환
        Timestamp timestamp = Timestamp.valueOf(formattedDateTime);

        // 로그 출력
        Log.d("AddFeedViewModel", "피드 등록 날짜 및 시간 : " + currentDateTime);
        Log.d("AddFeedViewModel", "피드 등록 날짜 및 시간 : " + timestamp);

        return timestamp;
    }
}