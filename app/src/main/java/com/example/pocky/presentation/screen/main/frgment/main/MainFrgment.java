package com.example.pocky.presentation.screen.main.frgment.main;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.pocky.databinding.FragmentMainBinding;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.presentation.screen.shoppingCartActivity.ShoppingCartActivity;

import java.util.List;

public class MainFrgment extends Fragment {

    //바인딩 변수 설정
    private static FragmentMainBinding binding;
    private static MainViewModel viewModel;
    private static MainRecyclerViewAdapter recyclerViewAdapter;
    private static RecyclerView recyclerView;
    private List<String> menuName;
    private List<Integer> menuImg;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //바인딩 설정
        binding = FragmentMainBinding.inflate(inflater, container, false);



        viewModel = new ViewModelProvider(this)
                .get(MainViewModel.class);

        menuName = viewModel.initMenuName();
        menuImg = viewModel.initMenuImg();



        //리사이클러뷰 초기화
        initRecyclerView();
        //유저 이름 초기화
        binding.setName(viewModel.initUserData());
        //프로필사진 초기화
        initGlide(binding);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initGlide(FragmentMainBinding binding){
        Glide.with(this)
                .load(UserInfo.getInstance().getProfileURl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(binding.profileImageView);

    }

    private void initRecyclerView(){
        recyclerView = binding.selectMenuRecyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewAdapter = new MainRecyclerViewAdapter();
        recyclerViewAdapter.setOnItemClickListener(new MainRecyclerViewAdapter.OnItemClickListener() { //리스트 아이템 클릭리스너
            @Override
            public void onItemClick(View v, int position) {
                switch (position){
                    case 0 :{
                        Intent intent = new Intent(getContext(), ShoppingCartActivity.class); // 아침메뉴
                        startActivity(intent);
                    }
                    case 1 : {
                        Intent intent = new Intent(getContext(), ShoppingCartActivity.class); // 샐러드
                        startActivity(intent);
                    }
                    case 2 : {
                        Intent intent = new Intent(getContext(), ShoppingCartActivity.class); // 샌드위치
                        startActivity(intent);
                    }
                    case 3 : {
                        Intent intent = new Intent(getContext(), ShoppingCartActivity.class); // 랩 및 기타
                        startActivity(intent);
                    }
                    case 4 : {
                        Intent intent = new Intent(getContext(), ShoppingCartActivity.class); // 그룹 메뉴
                        startActivity(intent);
                    }
                    case 5 : {
                        Intent intent = new Intent(getContext(), ShoppingCartActivity.class); // 스마일 썹
                        startActivity(intent);
                    }
                }
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setArr(menuName,menuImg);

    }
}
