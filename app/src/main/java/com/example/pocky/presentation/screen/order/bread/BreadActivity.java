package com.example.pocky.presentation.screen.order.bread;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivityBreadBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.souce.SouceActivity;

import java.util.Arrays;
import java.util.List;

public class BreadActivity extends AppCompatActivity {
    private static List<Integer> imageList;
    private static List<String> breadName;
    private static List<String> qrBreadName; // 메뉴 객체에 들어갈 리스트
    private ActivityBreadBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBreadBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        init();

        Menu arr = MenuSingleton.getInstance();

        RecyclerView recyclerView = findViewById(R.id.bread_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        BreadAdapter adapter = new BreadAdapter(imageList,breadName,qrBreadName, new BreadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String breadName, String qrBreadName) {
                //클릭된 메뉴 이미지, 메뉴 이미지 싱글톤 객체에 담기
                arr.setBreadName(breadName);
                arr.setQrBreadName(qrBreadName);

                Log.d("SideActivity", "선택된 아이템: " + imageResId);
                Log.d("SideActivity", "선택된 메뉴: " + arr.getBreadName());
                Log.d("SideActivity", "선택된 메뉴: " + arr.getQrBreadName());
            }
        });

        recyclerView.setAdapter(adapter);

        //선택 완료
        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr.getBreadName() == null || arr.getBreadName().isEmpty()){
                    Toast myToast = Toast.makeText(getApplicationContext(),"먼저 빵을  선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Log.d("BreakFastActivity","최종적으로 선택된 아이템 : " + arr.getBreadName()+" "+ arr.getQrBreadName());
                    Intent intent = new Intent(getApplicationContext(), SouceActivity.class); // 아침메뉴
                    startActivity(intent);
                }

            }
        });

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.setBreadName("");
                arr.setQrBreadName("");
                finish();
            }
        });
    }

    void init(){
        // 샌드위치 이름, 이미지 초기화
        imageList = Arrays.asList(
                R.drawable.resize_white,
                R.drawable.resize_wheet,
                R.drawable.resize_parmesan,
                R.drawable.resize_honeyoat,
                R.drawable.resize_hathi,
                R.drawable.resize_flat
        );

        breadName = Arrays.asList(
                "화이트",
                "휘트",
                "파마산",
                "허니오트",
                "하티",
                "플랫브래드"
        );
        qrBreadName=Arrays.asList(
                "WHITE",
                "WHEAT",
                "PARMESAN",
                "HONEYOAT",
                "HEARTY",
                "FLATBREAD"
        );
    }
}

