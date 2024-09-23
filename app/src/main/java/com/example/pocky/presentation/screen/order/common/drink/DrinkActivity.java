package com.example.pocky.presentation.screen.order.common.drink;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivityDrinkBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.confirmation.ConfirmationActivity;

import java.util.Arrays;
import java.util.List;

public class DrinkActivity extends AppCompatActivity {
    private static List<Integer> imageList;
    private static List<String> isSelected;
    private ActivityDrinkBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDrinkBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Menu arr = MenuSingleton.getInstance(); // 메뉴 객체 싱글톤 가져오기

        init();

        RecyclerView recyclerView = findViewById(R.id.drink_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        DrinkActivityAdapter adapter = new DrinkActivityAdapter(new DrinkActivityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String isSelected) {
                    if(isSelected == "예"){
                        arr.setRequid(true);
                    }else if(isSelected == "아니오"){
                        arr.setRequid(false);
                    }
                    //받아온 데이터 처리
            }
        }, imageList, isSelected);
        recyclerView.setAdapter(adapter);

        binding.backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.setRequid(false);
                finish();
            }
        });

        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr.getRequid() == null){
                    Toast myToast = Toast.makeText(getApplicationContext(),"먼저 음료 유무를 선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Log.d("DrinkActivity","최종적으로 선택된 아이템 : " + arr.getMenuName()+" "+ arr.getMenuImage() +" " + arr.getSideName() + " " + arr.getRequid());
                    Intent intent = new Intent(getApplicationContext(), ConfirmationActivity.class); // 최종 메뉴 확인 화면 이동
                    startActivity(intent);
                }
            }
        });
    }



    void init(){
        imageList = Arrays.asList(
                R.drawable.resize_coke,
                R.drawable.resize_nodrink
        );

        isSelected = Arrays.asList(
                "예",
                "아니오"
        );
    }

}
