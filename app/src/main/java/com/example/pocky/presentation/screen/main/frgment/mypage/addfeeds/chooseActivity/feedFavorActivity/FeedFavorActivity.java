package com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.chooseActivity.feedFavorActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pocky.databinding.ActivitiyFeedFavorBinding;
import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorAdapter;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorViewModel;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorViewModelFactory;
import com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.addFeedActivity.AddFeedActivity;

public class FeedFavorActivity extends AppCompatActivity {
    ActivitiyFeedFavorBinding binding;
    FavorViewModel viewModel;
    private FavorAdapter favorAdapter;
    private Favor selectedFavor;  // 클릭된 Favor 데이터를 저장할 변수

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initViewModel();
        initAdapter();
        setUpDataAdapter();
        initListener();
    }

    private void initViewModel(){
        //뷰모델 초기화
        FavorViewModelFactory factory = new FavorViewModelFactory((Application) getApplicationContext());
        viewModel = new ViewModelProvider(this, factory).get(FavorViewModel.class);
    }

    private void initView(){
        binding = ActivitiyFeedFavorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    private void initAdapter(){
        favorAdapter = new FavorAdapter(new FavorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Favor favor) {
                Log.d("FavorFragment","선택된 데이터 : " + favor.getMenuName());
                selectedFavor = favor;
            }
        },viewModel);
        favorAdapter.setIsFeed(true); // 상태 변수 관리 함수 호출
        binding.favorFeedRecyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
        binding.favorFeedRecyclerView.setAdapter(favorAdapter);
    }

    private void setUpDataAdapter(){
        // ViewModel에서 데이터를 가져와서 RecyclerView에 반영
        viewModel.getFavorList().observe(this, favors -> {
            if(favors == null || favors.isEmpty()){ // 즐겨찾기 내역이 있다면, 없다면
                binding.favorFeedRecyclerView.setVisibility(View.INVISIBLE); // 내역 보여주는 리사이클러뷰 숨기기
                binding.emptyView.setVisibility(View.VISIBLE); // 주문유도창 보이기
                binding.selectedBtn.setVisibility(View.INVISIBLE); // 선택 완료 버튼 숨기기
            }else{
                binding.favorFeedRecyclerView.setVisibility(View.VISIBLE); // 내역 보여주는 리사이클러뷰 보이기
                binding.emptyView.setVisibility(View.INVISIBLE);            // 주문유도창 숨기기
                favorAdapter.submitList(favors);  // 데이터를 어댑터에 설정
            }
        });
    }

    private void initListener(){
        binding.selectedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewModel.getFavorList().getValue().isEmpty() || selectedFavor == null){
                    Toast myToast = Toast.makeText(getApplicationContext(),"메뉴를 선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), AddFeedActivity.class);
                    intent.putExtra("data",selectedFavor);
                    intent.putExtra("isChooseFavor",true);
                    startActivity(intent);
                }
            }
        });
    }

}
