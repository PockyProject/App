package com.example.pocky.presentation.screen.order.wrapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivityWrappBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.drink.DrinkActivity;

import java.util.Arrays;
import java.util.List;

public class WrappActivity extends AppCompatActivity {
    private static List<Integer> imageList;
    private static List<String> wrappName;
    private ActivityWrappBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWrappBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        init();

        Menu arr = MenuSingleton.getInstance();

        RecyclerView recyclerView = findViewById(R.id.wrapp_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        WrappAdapter adapter = new WrappAdapter(imageList,wrappName, new WrappAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String sideName) {
                //클릭된 메뉴 이미지, 메뉴 이미지 싱글톤 객체에 담기
                arr.setMenuName(sideName);
                arr.setMenuImage(imageResId);

            }
        });

        recyclerView.setAdapter(adapter);

        //선택 완료
        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr.getMenuName() == null || arr.getMenuName().isEmpty()){
                    Toast myToast = Toast.makeText(getApplicationContext(),"먼저 제품을  선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Log.d("BreakFastActivity","최종적으로 선택된 아이템 : " + arr.getMenuName()+" "+ arr.getMenuImage());
                    Intent intent = new Intent(getApplicationContext(), DrinkActivity.class); // 아침메뉴
                    startActivity(intent);
                }

            }
        });

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.setMenuName("");
                arr.setQrMenuName("");
                finish();
            }
        });
    }

    void init(){
        // 샌드위치 이름, 이미지 초기화
        imageList = Arrays.asList(
                R.drawable.resize_chickenbaconminiwrap,
                R.drawable.resize_shrimpeggmayorap,
                R.drawable.resize_steakncheeseavocadowrap
        );

        wrappName = Arrays.asList(
                "치킨베이컨미니랩",
                "쉬림프마요랩",
                "스테이크치즈아보카도랩"
        );
    }
}