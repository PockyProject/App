package com.example.pocky.presentation.screen.order.sandwitch;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivitySandwitchBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.bread.BreadActivity;

import java.util.Arrays;
import java.util.List;

public class SandwitchActivity extends AppCompatActivity {

    private static List<Integer> imageList;
    private static List<String> sandwitchName;
    private static List<String> qrSandwitchName; // 메뉴 객체에 들어갈 리스트
    private ActivitySandwitchBinding binding;
    private Boolean isChooseFeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivitySandwitchBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        initIsFeedState();
        init();

        Menu arr = MenuSingleton.getInstance();

        RecyclerView recyclerView = findViewById(R.id.sandwitch_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SandwitchAdpater adapter = new SandwitchAdpater(imageList,sandwitchName,qrSandwitchName, new SandwitchAdpater.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String sideName, String qrName) {
                //클릭된 메뉴 이미지, 메뉴 이미지 싱글톤 객체에 담기
                arr.setMenuName(sideName);
                arr.setQrMenuName(qrName);
                arr.setMenuImage(imageResId);


                Log.d("SideActivity", "선택된 아이템: " + imageResId);
                Log.d("SideActivity", "선택된 메뉴: " + arr.getMenuName());
                Log.d("SideActivity", "선택된 메뉴: " + arr.getQrMenuName());
            }
        });

        recyclerView.setAdapter(adapter);

        //선택 완료
        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr.getMenuName() == null || arr.getMenuName().isEmpty()){
                    Toast myToast = Toast.makeText(getApplicationContext(),"먼저 빵을  선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Log.d("BreakFastActivity","최종적으로 선택된 아이템 : " + arr.getMenuName()+" "+ arr.getMenuImage());
                    Intent intent = new Intent(getApplicationContext(), BreadActivity.class); // 빵 선택
                    intent.putExtra("isChooseFeed",isChooseFeed);
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

    private void initIsFeedState(){
        if(getIntent() != null){
            Intent intent = getIntent();
            isChooseFeed = intent.getBooleanExtra("isChooseFeed",false);
        }else{
            isChooseFeed = false;
        }
    }

    void init(){
        // 샌드위치 이름, 이미지 초기화
        imageList = Arrays.asList(
                R.drawable.resize_bltsandwitch,
                R.drawable.resize_chickenbaconavocadosandwitch,
                R.drawable.resize_chickenslicesandwitch,
                R.drawable.resize_chickenteriyakisandwitch,
                R.drawable.resize_eggslicesandwitch,
                R.drawable.resize_eggmayosandwitch,
                R.drawable.resize_hamsandwitch,
                R.drawable.resize_italianbmtsandwitch,
                R.drawable.resize_kbbqsandwitch,
                R.drawable.resize_pullporkcheesesandwitch,
                R.drawable.resize_roastedchickensandwitch,
                R.drawable.resize_rotisseriebbqchickensandwitch,
                R.drawable.resize_shrimpsandwitch,
                R.drawable.resize_spicyitaliansandwitch,
                R.drawable.resize_spicyshrimpsandwitch,
                R.drawable.resize_steakandcheesesandwitch,
                R.drawable.resize_subwayclubsandwitch,
                R.drawable.resize_veggiesandwitch
        );

        sandwitchName = Arrays.asList(
                "비엘티샌드위치",
                "치킨아보카도샌드위치",
                "치킨슬라이스샌드위치",
                "치킨데리야끼샌드위치",
                "에그슬라이스샌드위치",
                "에그마요샌드위치",
                "햄샌드위치",
                "이탈리안비엠티샌드위치",
                "K비비큐샌드위치",
                "폴포크치즈샌드위치",
                "로티세리샌드위치",
                "로티세리비비큐샌드위치",
                "쉬림프샌드위치",
                "스파이시이탈리안샌드위치",
                "스파이시쉬림프샌드위치",
                "스테이크앤치즈샌드위치",
                "서브웨이클럽샌드위치",
                "베지샌드위치"
        );
        qrSandwitchName=Arrays.asList(
                "BLTSANDWICH",
                "CHICKENAVOCADOSANDWICH",
                "CHICKENSLICESANDWICH",
                "CHICKENTERIYAKISANDWICH",
                "EGGSLICESANDWICH",
                "EGGMAYOSANDWICH",
                "HAMSANDWICH",
                "ITALIANBMTSANDWICH",
                "KBBQSANDWITCH",
                "PORKCHEESESANDWICH",
                "ROASTEDCHICKENSANDWICH",
                "ROTISSERIEBBQCHICKEN",
                "SHRIMPSANDWICH",
                "SPICYITALIANSANDWICH",
                "SPICYSHRIMPSANDWICH",
                "STEAKCHEESESANDWICH",
                "SUBWAYCLUBSANDWICH",
                "VEGGIESANDWICH"
                );

        // 사이드 이름 추가
    }
}

