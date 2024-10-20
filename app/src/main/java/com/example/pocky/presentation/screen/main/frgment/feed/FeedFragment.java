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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.FragmentFeedBinding;
import com.example.pocky.presentation.screen.main.frgment.feed.FeeddetailActivity.FeeddetailActivity;


import java.util.Arrays;
import java.util.List;

public class FeedFragment extends Fragment {
    private FragmentFeedBinding binding;
    //private static FeedViewModel viewModel; 후에 모델 추가 예정
    private static FeedAdapter recyclerViewAdapter;
    private static RecyclerView recyclerView;
    private List<Integer> FeedImg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        recyclerView = view.findViewById(R.id.feed_recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

        recyclerViewAdapter = new FeedAdapter(FeedImg);
        recyclerView.setAdapter(recyclerViewAdapter);

        // RecyclerView 클릭 이벤트 리스너 설정
        recyclerViewAdapter.setOnItemClickListener(new FeedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.e("FeedFragment", "Clicked item at position: " + position);
                Toast.makeText(getActivity(), "아이템 " + position + " 클릭됨", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getActivity(), FeeddetailActivity.class);
                startActivity(intent);
            }
        });

        return view; // onCreateView의 마지막에 반환
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FeedImg = Arrays.asList(R.drawable.resize_bestpartyplatter,
                R.drawable.resize_oatmealrasin,
                R.drawable.resize_white,
                R.drawable.resize_oatmealrasin,
                R.drawable.resize_oatmealrasin,
                R.drawable.resize_oatmealrasin,
                R.drawable.resize_oatmealrasin

        );

        recyclerViewAdapter = new FeedAdapter(FeedImg);



        initRecyclerView();  // 리사이클러뷰 초기화
    }

    private void initGlide(FragmentFeedBinding binding) {
    }


    private void initRecyclerView() {

        recyclerView = binding.feedRecyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        recyclerView.setAdapter(recyclerViewAdapter);

    };
}