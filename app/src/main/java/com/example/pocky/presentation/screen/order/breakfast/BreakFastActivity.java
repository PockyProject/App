package com.example.pocky.presentation.screen.order.breakfast;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivityBreakfastBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.side.SideActivity;

import java.util.Arrays;
import java.util.List;

public class BreakFastActivity extends AppCompatActivity {

    private ActivityBreakfastBinding binding;
    private static List<Integer> imageList;
    private static List<String> menuName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBreakfastBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Menu arr = MenuSingleton.getInstance(); // 메뉴 객체 싱글톤 가져오기
        init(); // 메뉴 이미지, 이름 초기화


        //어댑터 설정
        BreakFastAdapter adapter = new BreakFastAdapter(imageList,menuName, new BreakFastAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String menuName) {
                Log.d("BreakfastActivity", "선택된 아이템: " + imageResId);

                //클릭된 메뉴 이미지, 메뉴 이미지 싱글톤 객체에 담기
                arr.setMenuName(menuName);
                arr.setMenuImage(imageResId);
            }
        });

        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr.getMenuName() == null){
                    Toast myToast = Toast.makeText(getApplicationContext(),"먼저 메뉴를 선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Log.d("BreakFastActivity","최종적으로 선택된 아이템 : " + arr.getMenuName()+" "+ arr.getMenuImage());
                    Intent intent = new Intent(getApplicationContext(), SideActivity.class); // 아침메뉴
                    startActivity(intent);
                }
            }
        });

        binding.backspaceBtn.setOnClickListener(new View.OnClickListener() { // 뒤로 가기 버튼
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.breakFastRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);
    }

    static void init(){
        imageList = Arrays.asList( // 메뉴 이미지 초기화
                R.drawable.resize_hameggcheese,
                R.drawable.resize_hameggcheesewrap,
                R.drawable.resize_westerneggcheese,
                R.drawable.resize_westerneggcheesewrap
        );

        menuName = Arrays.asList( // 메뉴
                "햄치즈",
                "햄치즈에그랩",
                "웨스턴에그치즈",
                "웨스턴에그치즈랩"
        );
    }
}