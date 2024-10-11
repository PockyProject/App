package com.example.pocky.presentation.screen.order.common.side;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivitySideBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.drink.DrinkActivity;

import java.util.Arrays;
import java.util.List;

public class SideActivity extends AppCompatActivity {

    private ActivitySideBinding binding;
    private static List<Integer> imageList;
    private static List<String> sideName;
    private static List<String> arrSideName; // 메뉴 객체에 들어갈 리스트
    private Boolean isChooseFeed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySideBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        initIsFeedState();
        init();

        Menu arr = MenuSingleton.getInstance(); // 메뉴 객체 싱글톤 가져오기

        RecyclerView recyclerView = findViewById(R.id.side_RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));


        SideAdapter adapter = new SideAdapter(imageList,sideName,arrSideName, new SideAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String sideName,String qrName) {
                Log.d("SideActivity", "선택된 아이템: " + imageResId);
                Log.d("SideActivity", "선택된 메뉴: " + arr.getMenuName());
                //클릭된 메뉴 이미지, 메뉴 이미지 싱글톤 객체에 담기
                arr.setSideName(sideName);
                arr.setqrSideName(qrName);
            }
        });
        recyclerView.setAdapter(adapter);


        //선택 완료
        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr.getSideName().isEmpty()){
                    Toast myToast = Toast.makeText(getApplicationContext(),"먼저 사이드를 선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Log.d("SideActivity","최종적으로 선택된 아이템 : " +arr.getQrSideName());
                    Intent intent = new Intent(getApplicationContext(), DrinkActivity.class); // 아침메뉴
                    intent.putExtra("isChooseFeed",isChooseFeed);
                    startActivity(intent);
                }

            }
        });

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.setSideName("");
                finish();
            }
        });


    }

    private void initIsFeedState(){
        if(getIntent() != null){
            Intent intent = getIntent();
            isChooseFeed = intent.getBooleanExtra("isChooseFeed",false);
        }else{
            isChooseFeed = false;
        }
    }

    void init(){ // 사이드 이름, 이미지 초기화
        imageList = Arrays.asList(
                R.drawable.resize_baconcheesewedgepotato,
                R.drawable.resize_cheesewedgepotato,
                R.drawable.resize_chickenbaconminiwrap,
                R.drawable.resize_chips,
                R.drawable.resize_chocolatechip,
                R.drawable.resize_cornsoup,
                R.drawable.resize_doublechocolatechip,
                R.drawable.resize_hashbrown,
                R.drawable.resize_milk,
                R.drawable.resize_mushroomsoup,
                R.drawable.resize_oatmealrasin,
                R.drawable.resize_raspberrycheesecake,
                R.drawable.resize_wedgepotato,
                R.drawable.resize_whitechocomacadamia
                // 사이드 이미지 추가
        );

        sideName = Arrays.asList(
                "베이컨치즈웨지포테이토",
                "치즈웨지포테이토",
                "치킨베이컨랩",
                "감자칩",
                "초콜렛칩",
                "콘수프",
                "더블초콜릿칩",
                "해쉬브라운",
                "우유",
                "머쉬룸스프",
                "오트밀라진쿠키",
                "라지베리치즈케이크쿠키",
                "웨지포테이토",
                "화이트마카다미아쿠키"
        );
        arrSideName=Arrays.asList(
                "BACONCHEESEWEDGEPOTATO",
                "CHEESEWEDGEPOTATO",
                "CHICKENBACONWRAP",
                "POTATOCHIP",
                "CHOCOLATECHIP",
                "CORNSOUP",
                "DOUBLECHOCOLATECHIP",
                "HASHBROWN",
                "MILK",
                "MUSHROOMSOUP",
                "OATMEAL",
                "RASPBERRYCHEESECAKECOOKIE",
                "WEDGEPOTATO",
                "WHITEMACADAMIACOOKIE");

        // 사이드 이름 추가
    }
}