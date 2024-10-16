package com.example.pocky.presentation.screen.main.frgment.mypage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.pocky.databinding.FragmentMypageBinding;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorAdapter;
import com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.chooseActivity.ChooseActivity;

import java.util.Collections;
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

        initAdapter(Collections.emptyList());

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getMyFeed();

        viewModel.getFeed().observe((LifecycleOwner) requireContext(), new Observer<List<FeedData>>() {
            @Override
            public void onChanged(List<FeedData> feedData) {

                // 어댑터 초기화 피드 바뀔떄마다 옵저버 패턴으로 호출
                initAdapter(feedData);
            }
        });


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
