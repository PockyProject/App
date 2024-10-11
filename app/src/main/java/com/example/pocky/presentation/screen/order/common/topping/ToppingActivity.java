package com.example.pocky.presentation.screen.order.common.topping;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivityToppingBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.side.SideActivity;

import java.util.Arrays;
import java.util.List;

public class ToppingActivity extends AppCompatActivity {

    private static List<Integer> imageList;
    private static List<String> toppingName;
    private static List<String> qrToppingName; // 메뉴 객체에 들어갈 리스트
    private ActivityToppingBinding binding;
    private Boolean isFeed; // 피드에서 사용하는지 확인하는 상태변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityToppingBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        initIsFeedState();
        init();

        Menu arr = MenuSingleton.getInstance();

        RecyclerView recyclerView = findViewById(R.id.topping_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ToppingAdapter adapter = new ToppingAdapter(imageList,toppingName,qrToppingName, new ToppingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String toppingName, String qrToppingName) {
                //클릭된 메뉴 이미지, 메뉴 이미지 싱글톤 객체에 담기

                arr.setToppingName(toppingName);
                arr.setQrToppingName(qrToppingName);

                Log.d("SideActivity", "선택된 아이템: " + imageResId);
                Log.d("SideActivity", "선택된 메뉴: " + arr.getSauceName());
                Log.d("SideActivity", "선택된 메뉴: " + arr.getQrSauceName());
            }
        });

        recyclerView.setAdapter(adapter);

        //선택 완료
        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr.getToppingName() == null || arr.getToppingName().isEmpty()){
                    Toast myToast = Toast.makeText(getApplicationContext(),"먼저 소스를  선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Log.d("BreakFastActivity","최종적으로 선택된 아이템 : " +
                            arr.getToppingName().get(0)+" "+
                            arr.getQrToppingName().get(0));
                    Intent intent = new Intent(getApplicationContext(), SideActivity.class); // 사이드 선택
                    intent.putExtra("isFeed",isFeed);
                    startActivity(intent);
                }

            }
        });

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.deleteQrToppingName();
                arr.deleteToppingName();
                finish();
            }
        });
    }


    private void initAdapter(){

    }


    private void initIsFeedState(){
        if(getIntent() != null){
            Intent intent = getIntent();
            isFeed = intent.getBooleanExtra("isFeed",false);
        }else{
            isFeed = false;
        }
    }


    void init(){
        // 소스 이름, 이미지 초기화

        imageList = Arrays.asList(
                R.drawable.resize_abocado,
                R.drawable.resize_bacon,
                R.drawable.resize_eggslice,
                R.drawable.resize_meet,
                R.drawable.resize_omelet,
                R.drawable.resize_pepperoni,
                R.drawable.resize_eggmayo,
                R.drawable.resize_americancheese,
                R.drawable.resize_mozzarellacheese,
                R.drawable.resize_shreddedcheese,
                R.drawable.resize_cucumber,
                R.drawable.resize_halapinyo,
                R.drawable.resize_lettuce,
                R.drawable.resize_olive,
                R.drawable.resize_onion,
                R.drawable.resize_pickle,
                R.drawable.resize_pimento,
                R.drawable.resize_tomato
                // 소스 이미지 추가
        );


        toppingName = Arrays.asList(
                "아보카도",
                "베이컨",
                "에그슬라이스",
                "미트",
                "오믈렛",
                "페퍼로니",
                "에그마요",
                "아메리칸치즈",
                "모짜렐라치즈",
                "슈래드치즈",
                "오이",
                "할라피뇨",
                "양상추",
                "올리브",
                "양파",
                "피클",
                "피망",
                "토마토"
        );
        qrToppingName = Arrays.asList(
                "AVOCADO",
                "BACON",
                "EGGSLICE",
                "MEAT",
                "OMELETTE",
                "PEPPERONI",
                "EGGMAYO",
                "AMERICANCHEESE",
                "MOZZARELLACHEESE",
                "SHOEREDDCHEESE",
                "CUCUMBER",
                "JALAPENO",
                "LETTUCE",
                "OLIVES",
                "ONION",
                "PICKLE",
                "PIMENTO",
                "TOMATO"
        );
    }
}