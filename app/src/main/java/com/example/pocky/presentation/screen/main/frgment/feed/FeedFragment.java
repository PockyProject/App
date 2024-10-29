package com.example.pocky.presentation.screen.main.frgment.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pocky.databinding.FragmentFeedBinding;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.presentation.screen.main.frgment.feed.feeddetail.FeeddetailActivity;

import java.util.List;

public class FeedFragment extends Fragment {
    private FragmentFeedBinding binding;
    private FeedViewModel viewModel;
    private static final String TAG = "FeedFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentFeedBinding.inflate(getLayoutInflater());

        FeedViewModelFactory factory = new FeedViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, factory).get(FeedViewModel.class);

        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getAllFeed();

        viewModel.getFeed().observe(getViewLifecycleOwner(), new Observer<List<FeedData>>() {
            @Override
            public void onChanged(List<FeedData> feedData) {
                initAdapter(viewModel.getFeed().getValue());
            }
        });
    }



    private void initAdapter(List<FeedData> arr) {
        FeedAdapter recyclerViewAdapter = new FeedAdapter(new FeedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                FeedData feedlist = arr.get(position);

                Intent intent = new Intent(getActivity(), FeeddetailActivity.class);
                intent.putExtra("FeedData", feedlist);
                startActivity(intent);
            }
        });

        recyclerViewAdapter.submitList(arr);
        binding.feedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.feedRecyclerView.setAdapter(recyclerViewAdapter);
    };
}