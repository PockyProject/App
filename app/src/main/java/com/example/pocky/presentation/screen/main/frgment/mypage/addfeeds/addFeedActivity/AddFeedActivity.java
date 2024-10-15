package com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.addFeedActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pocky.databinding.ActivityAddfeedBinding;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.domain.repository.favor.Favor;

public class AddFeedActivity extends AppCompatActivity {

    private static ActivityAddfeedBinding binding;
    private static String TAG = "AddFeedActivity";
    private Boolean isChooseFeed;
    private Boolean isChooseFavor;
    private int imageResouceLocation;
    private static AddFeedViewModel viewModel;
    private static FeedData postedData;
    private String menuName;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //바인딩 설정
        binding = ActivityAddfeedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //뷰모델 초기화
        AddFeedViewModelFactory factory = new AddFeedViewModelFactory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(AddFeedViewModel.class);


        inputEditText();
        accordingToStateValueInit();
        initView();


    }

    private void initView(){ // 뷰 요소 초기화
        binding.menuImage.setImageResource(imageResouceLocation);
        binding.infoMenuTextView.setText(menuName);

        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(postedData.getContent() == null || postedData.getTitle() == null){
                    Toast.makeText(getApplicationContext(), "제목, 내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                } else if(postedData.getTitle().isEmpty() || postedData.getTitle().isEmpty()){
                    Toast.makeText(getApplicationContext(), "제목, 내용을 입력해주세요", Toast.LENGTH_SHORT).show();
                }else{
                    viewModel.storedFeedDB(postedData);
                }
            }
        });
    }

    private void accordingToStateValueInit(){ // 상태에 따른 값 초기화
        Intent intent = getIntent();
        isChooseFeed = intent.getBooleanExtra("isChooseFeed",false);
        isChooseFavor = intent.getBooleanExtra("isChooseFavor",false);

        if(isChooseFeed){
            Menu menu = MenuSingleton.getInstance();
            imageResouceLocation = menu.getMenuImage();
            menuName = menu.getMenuName();
            postedData = viewModel.menuToFeed(menu,"","");
            Log.d(TAG,"새로 만든 주문 : " + menu.getMenuName());

        }else if(isChooseFavor){
            Favor favor = (Favor) intent.getSerializableExtra("data");
            imageResouceLocation = favor.getMenuImage();
            menuName = favor.getMenuName();
            postedData = viewModel.favorToFeed(favor,"","");
            Log.d(TAG,"즐겨찾기에서 가져온 주문 : " + favor.getMenuName());

        }
    }

    private void inputEditText(){
        binding.titleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                postedData.setTitle(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                postedData.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                postedData.setTitle(s.toString());
                Log.d(TAG,"제목 : " + s.toString());
            }
        });

        binding.contentEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                postedData.setContent(s.toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                postedData.setContent(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                postedData.setContent(s.toString());
                Log.d(TAG,"내용 : " + s.toString());
            }
        });
//        titleText = binding.titleEditText.getText().toString();
//        contentText = binding.contentEditText.getText().toString();
    }
}
