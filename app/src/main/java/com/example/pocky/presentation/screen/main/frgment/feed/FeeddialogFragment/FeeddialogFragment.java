package com.example.pocky.presentation.screen.main.frgment.feed.FeeddialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivityFeeddialogBinding;
import com.example.pocky.domain.model.Comment.CommentData;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import org.w3c.dom.Comment;

import java.util.List;

public class FeeddialogFragment extends BottomSheetDialogFragment {

    private FeeddialogModel viewModel;
    private ActivityFeeddialogBinding binding;
    private static FeeddialogAdapter dialogrecyclerViewAdapter;
    private static RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState){
        binding = ActivityFeeddialogBinding.inflate(getLayoutInflater());
        FeeddialogModelFactory factory = new FeeddialogModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, factory).get(FeeddialogModel.class);
        return binding.getRoot();
    }


    @Override
    public void onStart(){
        super.onStart();
        if(getDialog() != null) {
            View bottomSheet = getDialog().findViewById(com.google.android.material.R.id.design_bottom_sheet);
            if (bottomSheet != null) {
                ViewGroup.LayoutParams layoutParams = bottomSheet.getLayoutParams();
                layoutParams.height = 800;
                bottomSheet.setLayoutParams(layoutParams);
            }
        }
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getComment();
        viewModel.getCommentData().observe((LifecycleOwner)requireContext(), new Observer<List<CommentData>>() {
            @Override
            public void onChanged(List<CommentData> commentData) {
                initRecyclerView(commentData);  // 리사이클러뷰 초기화
                Log.d("commentdata1020",commentData.get(0).getFeedUid());
            }
        });

    }

    private void initRecyclerView(List<CommentData> arr) {

        dialogrecyclerViewAdapter = new FeeddialogAdapter(new FeeddialogAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                CommentData commentlist = arr.get(position);
                Intent intent = new Intent();
                intent.putExtra("menuImage", commentlist.getContent());
                startActivity(intent);
            }
        });
        dialogrecyclerViewAdapter.submitList(arr);
        recyclerView = binding.commentRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(dialogrecyclerViewAdapter);

    };
}

