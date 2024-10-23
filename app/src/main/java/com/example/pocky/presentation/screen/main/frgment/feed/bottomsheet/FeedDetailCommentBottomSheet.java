package com.example.pocky.presentation.screen.main.frgment.feed.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pocky.R;
import com.example.pocky.databinding.BottomSheetCommentBinding;
import com.example.pocky.domain.model.comment.CommentData;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class FeedDetailCommentBottomSheet  extends BottomSheetDialogFragment {
    static String TAG = "ModalBottomSheet";
    private List<CommentData> arr;
    private CommentAdapter adapter;

    private BottomSheetCommentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_comment,container,false);
        ConstraintLayout bottomSheet = view.findViewById(R.id.bottomSheet);
        binding = BottomSheetCommentBinding.inflate(getLayoutInflater());

        arr = new ArrayList<>();

        initView(bottomSheet);
        initAdpater();

        return view;
    }



    private void initView(ConstraintLayout content){

        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(content);
        bottomSheetBehavior.setDraggable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback(){
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if(i == BottomSheetBehavior.STATE_DRAGGING){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                //없어도 됌
            }
        });
    }


    private void initAdpater(){

        // Adapter 초기화
        adapter = new CommentAdapter();
        binding.commentRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.commentRecyclerView.setAdapter(adapter);

    }

    private void setCommentData(List<CommentData> data){
        arr = data;
    }

}


