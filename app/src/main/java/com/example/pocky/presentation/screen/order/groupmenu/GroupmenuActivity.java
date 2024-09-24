package com.example.pocky.presentation.screen.order.groupmenu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivityGruopmenuBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.drink.DrinkActivity;
import com.example.pocky.presentation.screen.order.sandwitch.SandwitchAdpater;

import java.util.Arrays;
import java.util.List;

public class GroupmenuActivity extends AppCompatActivity {

    private static List<Integer> imageList;
    private static List<String> groupMenu;
    private static List<String> qrGroupMenu; // 메뉴 객체에 들어갈 리스트
    private ActivityGruopmenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGruopmenuBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        init();

        Menu arr = MenuSingleton.getInstance();

        RecyclerView recyclerView = findViewById(R.id.group_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SandwitchAdpater adapter = new SandwitchAdpater(imageList,groupMenu,qrGroupMenu, new SandwitchAdpater.OnItemClickListener() {
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
                R.drawable.resize_bestpartyplatter,
                R.drawable.resize_cookiebox,
                R.drawable.resize_cookieplatter,
                R.drawable.resize_freshpartyplatter,
                R.drawable.resize_gientsub3feet,
                R.drawable.resize_gientsub6feet
        );

        groupMenu = Arrays.asList(
                "베스트파티플레터",
                "쿠키박스",
                "쿠키플레터",
                "프레시파티플레터",
                "자이언트3피트",
                "자이언트서브6피트"

        );
        qrGroupMenu = Arrays.asList(
                "BESTPARTYFLATTER",
                "COOKIEBOX",
                "COOKIEFLATTER",
                "FRESHPARTYFLATTER",
                "GIANT3FEET",
                "GIANTSUB6FEET"
        );

        // 사이드 이름 추가
    }
}
