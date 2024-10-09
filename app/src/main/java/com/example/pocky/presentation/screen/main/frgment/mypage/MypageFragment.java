package com.example.pocky.presentation.screen.main.frgment.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.pocky.databinding.FragmentMypageBinding;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorAdapter;
import com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.chooseActivity.ChooseActivity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MypageFragment extends Fragment {
    private final static String TAG = "MypageFragments";
    private FragmentMypageBinding binding;
    private MypageAdapter adapter;
    private MypageViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMypageBinding.inflate(getLayoutInflater());

        //뷰모델 초기화
        MypageViewModelFactory factory = new MypageViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, factory).get(MypageViewModel.class);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //Log.d(TAG,"최근 주문 수 : " + viewModel.getOrderList());

       // 더미데이터
        FeedData ex = new FeedData(
                "feeduid",
                "useruid",
                "title",
                "content",
                1,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        FeedData ex1 = new FeedData(
                "feeduid",
                "useruid",
                "title",
                "content",
                1,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );
        FeedData ex2 = new FeedData(
                "feeduid",
                "useruid",
                "title",
                "content",
                1,
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis())
        );

        List<FeedData> feedData = new ArrayList<>();
        feedData.add(ex);
        feedData.add(ex1);
        feedData.add(ex2);

        //어댑터 초기화
        initAdapter(feedData);

        //프로필 화면 초기화
        initProfile();

        //클릭 이벤트 초기화
        initListener();
    }


    private void initAdapter(List<FeedData> data){
        adapter = new MypageAdapter(new FavorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Favor favor) {

            }
        });
        adapter.submitList(data);
        binding.feedRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.feedRecyclerView.setAdapter(adapter);
    }

    private void initProfile(){
        String image = UserInfo.getInstance().getProfileURl();

        Glide.with(getContext())
                .load(UserInfo.getInstance().getProfileURl())
                .circleCrop()
                .into(binding.profileImageView);

        viewModel.getFavorList().observe(getViewLifecycleOwner(), favors ->{
            binding.favorNumberText.setText("즐겨찾기수\n"+String.valueOf(favors.size()));
        });

        viewModel.getOrderList().observe(getViewLifecycleOwner(), orders -> {
            binding.orderListNumberText.setText("최근 주문 수\n" + String.valueOf(orders.size()));
        });
    }

    private void initListener(){
        binding.addFeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChooseActivity.class);
                startActivity(intent);
            }
        });
    }



}
