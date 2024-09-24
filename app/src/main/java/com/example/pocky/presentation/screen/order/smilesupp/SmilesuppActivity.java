package com.example.pocky.presentation.screen.order.smilesupp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivitySmilesuppBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.drink.DrinkActivity;

import java.util.Arrays;
import java.util.List;

public class SmilesuppActivity extends AppCompatActivity {

    private static List<Integer> imageList;
    private static List<String> smileSuppName;
    private ActivitySmilesuppBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySmilesuppBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        init();

        Menu arr = MenuSingleton.getInstance();

        RecyclerView recyclerView = findViewById(R.id.smilesup_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SmilSuppAdapter adapter = new SmilSuppAdapter(imageList,smileSuppName, new SmilSuppAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String menuName) {
                //클릭된 메뉴 이미지, 메뉴 이미지 싱글톤 객체에 담기
                arr.setMenuName(menuName);
                arr.setMenuImage(imageResId);

                Log.d("SideActivity", "선택된 아이템: " + imageResId);
                Log.d("SideActivity", "선택된 메뉴: " + arr.getMenuName());
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
        );


        smileSuppName = Arrays.asList(
                "베이컨치즈웨지포테이토",
                "치즈웨지포테이토",
                "치킨베이컨미니랩",
                "감자칩",
                "초코칩",
                "콘수프",
                "더블초코칩",
                "해쉬브라운",
                "우유",
                "머쉬룸스프",
                "오트밀라진쿠키",
                "라즈베리치즈쿠키",
                "웨지포테이토",
                "화이트마카다미안쿠키"
        );
        // 사이드 이름 추가
    }
}

