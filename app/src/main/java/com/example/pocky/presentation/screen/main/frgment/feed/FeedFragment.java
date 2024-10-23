package com.example.pocky.presentation.screen.main.frgment.feed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.FragmentFeedBinding;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.presentation.screen.main.frgment.feed.FeeddetailActivity.FeeddetailActivity;


import java.util.Arrays;
import java.util.List;

public class FeedFragment extends Fragment {
    private FragmentFeedBinding binding;
    private FeedViewModel viewModel;
    private static FeedAdapter recyclerViewAdapter;
    private List<FeedData> feedlist;
    private static RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentFeedBinding.inflate(getLayoutInflater());

        FeedViewModelFactory factory = new FeedViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, factory).get(FeedViewModel.class);
        View view = binding.getRoot();

        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getAllFeed();
        viewModel.getFeed().observe((LifecycleOwner)requireContext(), new Observer<List<FeedData>>() {
            @Override
            public void onChanged(List<FeedData> feedData) {
                initRecyclerView(feedData);  // 리사이클러뷰 초기화
                Log.d("feedData1020",feedData.get(0).getFeedUid());
            }
        });

    }

    private void initGlide(FragmentFeedBinding binding) {
    }

    private void initRecyclerView(List<FeedData> arr) {

        recyclerViewAdapter = new FeedAdapter(new FeedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FeedData feedlist = arr.get(position);
                Intent intent = new Intent(getActivity(), FeeddetailActivity.class);
                intent.putExtra("menuImage", feedlist.getMenuImage());
                intent.putExtra("titleText", feedlist.getTitle());
                intent.putExtra("mainText", feedlist.getContent());
                intent.putExtra("likeCount", feedlist.getLikeCount());
                intent.putExtra("qrImage", feedlist.getQrImage());
                intent.putExtra("feedDate", feedlist.getWritedDate());
                intent.putExtra("usrName",feedlist.getUserUid());
                //intent.putExtra("usrCommnet",
                startActivity(intent);
            }
        });
        recyclerViewAdapter.submitList(arr);
        recyclerView = binding.feedRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerViewAdapter);



    };
}