package com.example.pocky.presentation.screen.main.frgment.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.pocky.databinding.FragmentMainBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.domain.model.recommend.viewRecommendDTO;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.presentation.screen.order.bread.BreadActivity;
import com.example.pocky.presentation.screen.order.breakfast.BreakFastActivity;
import com.example.pocky.presentation.screen.order.groupmenu.GroupmenuActivity;
import com.example.pocky.presentation.screen.order.salad.SaladActivity;
import com.example.pocky.presentation.screen.order.sandwitch.SandwitchActivity;
import com.example.pocky.presentation.screen.order.smilesupp.SmilesuppActivity;
import com.example.pocky.presentation.screen.order.wrapp.WrappActivity;

import java.util.ArrayList;
import java.util.List;

public class MainFrgment extends Fragment {

    //바인딩 변수 설정
    private static FragmentMainBinding binding;
    private static MainViewModel viewModel;
    private static MainRecyclerViewAdapter recyclerViewAdapter;
    private static RecyclerView recyclerView;
    private List<String> menuName;
    private List<Integer> menuImg;
    List<viewRecommendDTO> arr = new ArrayList<>();
    private static final String TAG = "MainFragment";

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
        //추천메뉴 초기화
        initRecommned();
        //추천 클릭 이벤트 초기화
        clickRecommend();


        return binding.getRoot();
    }


    private void initGlide(FragmentMainBinding binding){
        Glide.with(this)
                .load(UserInfo.getInstance().getProfileURl())
                .centerCrop()
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
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
                        Intent intent = new Intent(getContext(), BreakFastActivity.class); // 아침메뉴
                        startActivity(intent);
                        break;
                    }
                    case 1 : {
                        Intent intent = new Intent(getContext(), SaladActivity.class); // 샐러드
                        startActivity(intent);
                        break;
                    }
                    case 2 : {
                        Intent intent = new Intent(getContext(), SandwitchActivity.class); // 샌드위치
                        startActivity(intent);
                        break;
                    }
                    case 3 : {
                        Intent intent = new Intent(getContext(), WrappActivity.class); // 랩 및 기타
                        startActivity(intent);
                        break;
                    }
                    case 4 : {
                        Intent intent = new Intent(getContext(), GroupmenuActivity.class); // 그룹 메뉴
                        startActivity(intent);
                        break;
                    }
                    case 5 : {
                        Intent intent = new Intent(getContext(), SmilesuppActivity.class); // 스마일 썹
                        startActivity(intent);
                        break;
                    }
                }
            }
        });
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.setArr(menuName,menuImg);

    }

    void initRecommned(){ //추천메뉴 MOCK 데이터 초기화
        viewModel.getCommentData(UserInfo.getInstance().getUserAge());
        viewModel.getRecommendMenuImage().observe((LifecycleOwner) requireContext(), new Observer<List<viewRecommendDTO>>() {
            @Override
            public void onChanged(List<viewRecommendDTO> viewRecommendDTOS) {
                arr = viewRecommendDTOS;
                binding.recommend1stImg.setImageResource(viewRecommendDTOS.get(0).getMenuImage());
                binding.recomment1stText.setText(viewRecommendDTOS.get(0).getMenuName());

                binding.recommend2ndImg.setImageResource(viewRecommendDTOS.get(1).getMenuImage());
                binding.recomment2ndText.setText(viewRecommendDTOS.get(1).getMenuName());

                binding.recommned3rdImg.setImageResource(viewRecommendDTOS.get(2).getMenuImage());
                binding.recommnet3rdText.setText(viewRecommendDTOS.get(2).getMenuName());
            }
        });
    }

    void clickRecommend(){
        Menu menu = MenuSingleton.getInstance();
        binding.recommend1stLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), BreadActivity.class); // 빵 선택
                intent.putExtra("isChooseFeed",false);
                Log.d(TAG,"선택한 메뉴 : " + arr.get(0).getMenuImage());
                menu.setMenuName(arr.get(0).getMenuName());
                menu.setQrMenuName(arr.get(0).getMenuQrName());
                menu.setMenuImage(arr.get(0).getMenuImage());
                startActivity(intent);
            }
        });

        binding.recommend2ndLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), BreadActivity.class); // 빵 선택
                intent.putExtra("isChooseFeed",false);
                menu.setMenuName(arr.get(1).getMenuName());
                menu.setQrMenuName(arr.get(1).getMenuQrName());
                menu.setMenuImage(arr.get(1).getMenuImage());
                startActivity(intent);
            }
        });

        binding.recommend3rdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), BreadActivity.class); // 빵 선택
                intent.putExtra("isChooseFeed",false);
                menu.setMenuName(arr.get(2).getMenuName());
                menu.setQrMenuName(arr.get(2).getMenuQrName());
                menu.setMenuImage(arr.get(2).getMenuImage());
                startActivity(intent);
            }
        });
    }

}
