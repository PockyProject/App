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
import com.example.pocky.presentation.screen.main.MainActivity;
import com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.chooseActivity.ChooseActivity;

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

                    //등록된 콜백의 결과에 따라 화면 이동 구현
                    viewModel.storedFeedDB(postedData, new AddFeedViewModel.isSuccessCallback<Boolean>() {
                        @Override
                        public void onSuccess(Boolean result) {
                            moveToT(result); // 저장 성공, 실패에 따른 액티비티 이동
                        }

                        @Override
                        public void onFailure(Boolean result) {
                            moveToT(result);
                        }
                    });
                }
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
            Log.d(TAG,"즐겨찾기 메뉴 이미지 : " + favor.getMenuImage());
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
    }

    private void moveToT(Boolean temp){
        if(temp){
            Log.d(TAG,"피드 등록에 성공했습니다");
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }else{
            Log.d(TAG,"피드 등록에 실패했습니다");
            Toast.makeText(getApplicationContext(), "피드 등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), ChooseActivity.class);
            startActivity(intent);
        }
    }
}
