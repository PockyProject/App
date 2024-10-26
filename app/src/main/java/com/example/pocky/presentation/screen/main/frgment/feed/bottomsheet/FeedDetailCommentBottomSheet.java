package com.example.pocky.presentation.screen.main.frgment.feed.bottomsheet;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pocky.R;
import com.example.pocky.databinding.BottomSheetCommentBinding;
import com.example.pocky.domain.model.comment.CommentData;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.presentation.screen.main.frgment.feed.feeddetail.FeedDetailViewModel;
import com.example.pocky.presentation.screen.main.frgment.feed.feeddetail.FeedDetailViewModelFactory;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;
import java.util.UUID;

public class FeedDetailCommentBottomSheet  extends BottomSheetDialogFragment {
    static String TAG = "ModalBottomSheet";
    private CommentAdapter adapter;

    private BottomSheetCommentBinding binding;

    private FeedDetailViewModel viewModel;

    private String feedUid = "";
    private String content = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_comment,container,false);
        binding = BottomSheetCommentBinding.inflate(getLayoutInflater());

        // 뷰모델 초기화
        FeedDetailViewModelFactory factory = new FeedDetailViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, factory).get(FeedDetailViewModel.class);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

        viewModel.getComment(feedUid);
        viewModel.getData().observe((LifecycleOwner) requireContext(), commentData -> {
            //Log.d(TAG,commentData.get(0).getContent());
            initAdpater(commentData);
        });
    }

    private void initView(){

        ConstraintLayout bottomSheet = binding.commentBottomSheet;
        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setPeekHeight(0);  // 처음에 완전히 표시되도록 설정
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);  // 시작할 때 확장 상태로 설정
        bottomSheetBehavior.setSkipCollapsed(true);  // 축소 상태를 건너뜀
        bottomSheetBehavior.setDraggable(true);  // 드래그 가능하도록 설정


        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback(){
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                Log.d(TAG,"바텀 상태 : " + i);
                if(i == BottomSheetBehavior.STATE_HIDDEN){
                    dismiss();
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                //없어도 됌
            }
        });

        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 입력한 텍스트 가져오기
                String inputText = binding.textInputLayer.getEditText().getText().toString();
                if(inputText.isEmpty()){
                    Toast.makeText(requireContext(),"한 글자 이상의 댓글을 입력하세요",Toast.LENGTH_SHORT).show();
                }else{
                    postData(inputText);
                    hideKeyboard();
                    binding.textInputLayer.getEditText().setText("");
                }
            }
        });
    }


    private void initAdpater(List<CommentData> data){


        // Adapter 초기화
        adapter = new CommentAdapter();
        binding.commentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.commentRecyclerView.setAdapter(adapter);
        adapter.submitList(data);

    }

    private void postData(String content){

            CommentData data = new CommentData(
                    UUID.randomUUID().toString(),
                    feedUid,
                    UserInfo.getInstance().getNickname(),
                    UserInfo.getInstance().getProfileURl(),
                    content,
                    viewModel.calcCurrentTime(),
                    0,
                    viewModel.calcCurrentTime(),
                    viewModel.calcCurrentTime(),
                    UserInfo.getInstance().getUserId()
            );
            viewModel.postComment(data);
    }

    public void setCommentData(String feedUid){
        this.feedUid = feedUid;
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(INPUT_METHOD_SERVICE);
        if (getView() != null) {
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }

}


