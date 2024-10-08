package com.example.pocky.presentation.screen.main.frgment.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.pocky.R;
import com.example.pocky.databinding.FragmentFeedBinding;



import java.util.List;

public class FeedFragment extends Fragment {

    private FragmentFeedBinding binding;

    //private static FeedViewModel viewModel; 후에 모델 추가 예정

    private static FeedAdapter recyclerViewAdapter;

    private static RecyclerView recyclerView;

    private List<String> FeedName;

    private List<Integer> FeedImg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFeedBinding.inflate(getLayoutInflater());


        initRecyclerView();

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initGlide(FragmentFeedBinding binding) {
        //Gilde
    }
    private void initRecyclerView() {

        recyclerView = binding.feedRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(recyclerViewAdapter);

    };
}
