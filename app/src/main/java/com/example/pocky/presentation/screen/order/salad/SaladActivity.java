package com.example.pocky.presentation.screen.order.salad;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivitySaladBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.side.SideActivity;

import java.util.Arrays;
import java.util.List;

public class SaladActivity extends AppCompatActivity {

    private static List<Integer> imageList;
    private static List<String> saladName;
    private static List<String> qrSaladName; // 메뉴 객체에 들어갈 리스트
    private ActivitySaladBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySaladBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        init();

        Menu arr = MenuSingleton.getInstance();

        RecyclerView recyclerView = findViewById(R.id.salad_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SaladAdapter adapter = new SaladAdapter(imageList,saladName,qrSaladName, new SaladAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String sideName, String qrName) {
                //클릭된 메뉴 이미지, 메뉴 이미지 싱글톤 객체에 담기
                arr.setMenuName(sideName);
                arr.setQrMenuName(qrName);
                arr.setMenuImage(imageResId);

                Log.d("SaladActivity", "선택된 아이템: " + imageResId);
                Log.d("SaladActivity", "선택된 메뉴: " + arr.getMenuName());
                Log.d("SaladActivity", "선택된 메뉴: " + arr.getQrMenuName());
            }
        });

        recyclerView.setAdapter(adapter);

        //선택 완료
        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr.getMenuName() == null || arr.getMenuName().isEmpty()){
                    Toast myToast = Toast.makeText(getApplicationContext(),"먼저 샐러드를 선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Log.d("SaladActivity","최종적으로 선택된 아이템 : " + arr.getMenuName()+" "+ arr.getMenuImage());
                    Intent intent = new Intent(getApplicationContext(), SideActivity.class); // 아침메뉴
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
        // 샐러드 이름, 이미지 초기화
        imageList = Arrays.asList(
                R.drawable.resize_bltsalad2,
                R.drawable.resize_chickenbaconavocadosalad,
                R.drawable.resize_chickenslicesalad,
                R.drawable.resize_chickenteriyakisalad,
                R.drawable.resize_eggmayosalad,
                R.drawable.resize_fullforksalad,
                R.drawable.resize_greenavocadoeggslicesalad,
                R.drawable.resize_greenricottachickensalad,
                R.drawable.resize_greenrotisseriechickenavocadosalad,
                R.drawable.resize_hamsalad,
                R.drawable.resize_italianbmtsalad,
                R.drawable.resize_kbbqsalad,
                R.drawable.resize_minirotisseriechickensalad,
                R.drawable.resize_roastchickensalad,
                R.drawable.resize_rotisseriesalad,
                R.drawable.resize_shrimpsalad,
                R.drawable.resize_spicyitaliansalad,
                R.drawable.resize_spicyshrimpsalad,
                R.drawable.resize_steakcheesesalad,
                R.drawable.resize_subwayclubsalad,
                R.drawable.resize_tunasalad,
                R.drawable.resize_veggiesalad
        );

        saladName = Arrays.asList(
                "비엘티샐러드",
                "치킨아보카도샐러드",
                "치킨슬라이스샐러드",
                "치킨데리야끼샐러드",
                "에그마요샐러드",
                "폴포크샐러드",
                "그린아보카도에그슬라이스샐러드",
                "그린리코타치킨샐러드",
                "그린로티세리치킨아보카도샐러드",
                "햄샐러드",
                "이탈리안비엠티샐러드",
                "KBBQ샐러드",
                "미니로티세리치킨샐러드",
                "로스트치킨샐러드",
                "로티세리샐러드",
                "쉬림프샐러드",
                "스파이시이탈리안샐러드",
                "스파이시쉬림프샐러드",
                "스테이크치즈샐러드",
                "서브웨이클럽샐러드",
                "튜나샐러드",
                "베지샐러드"
        );
        qrSaladName = Arrays.asList(
                "BLTSALAD",
                "CHICKENAVOCADOSALAD",
                "CHICKENSLICESALAD",
                "CHICKENTERIYAKISALAD",
                "EGGMAYOSALAD",
                "PORKSALAD",
                "GREENAVOCADOEGGSLICESALAD",
                "GREENRICOTTACHICKENSALAD",
                "GREENROTISSERIECHICKENAVOCAOSALAD",
                "HAMSALAD",
                "ITALIANBMTSALAD",
                "KBBQSALAD",
                "MINIROTISSERIECHICKENSALAD",
                "ROASTCHICKENSALAD",
                "ROTISSERIESALAD",
                "SHRIMPSALAD",
                "SPICYITALIANSALAD",
                "SPICYSHRIMPSALAD",
                "STEAKCHEESESALAD",
                "SUBWAYCLUBSALAD",
                "TUNASALAD",
                "VEGGIESALAD"
        );

        // 사이드 이름 추가
    }
}
