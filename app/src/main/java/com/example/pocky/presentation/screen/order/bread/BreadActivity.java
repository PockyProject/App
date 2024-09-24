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
import com.example.pocky.presentation.screen.order.common.SouceActivity;

import java.util.Arrays;
import java.util.List;

public class BreadActivity extends AppCompatActivity {
    private static List<Integer> imageList;
    private static List<String> isSelected;
    private static List<String> breadName;
    private static List<String> qrBreadName; // 메뉴 객체에 들어갈 리스트
    private ActivityBreadBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityBreadBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Menu arr = MenuSingleton.getInstance(); // 메뉴 객체 싱글톤 가져오기

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Integer> imageList = Arrays.asList(
                R.drawable.resize_white,
                R.drawable.resize_wheet,
                R.drawable.resize_parmesan,
                R.drawable.resize_honeyoat,
                R.drawable.resize_hathi,
                R.drawable.resize_flat
        );
        //어댑터 설정
        BreadAdapter adapter = new BreadAdapter(new BreadAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String menuName,String qrName) {
                Log.d("BreakfastActivity", "선택된 아이템: " + imageResId);

                //클릭된 메뉴 이미지, 메뉴 이미지 싱글톤 객체에 담기
                arr.setQrBreadName(qrName);
                arr.setBreadName(menuName);

            }
        },imageList,isSelected,breadName,qrBreadName);

        recyclerView.setAdapter(adapter);

        binding.backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.setBreadName("");
                finish();
            }
        });

        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arr.getBreadName().isEmpty() || arr.getBreadName() == ""){
                    Toast myToast = Toast.makeText(getApplicationContext(),"먼저 사이드를 선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Log.d("BreakFastActivity","최종적으로 선택된 아이템 : " + arr.getMenuName()+" "+ arr.getMenuImage());
                    Intent intent = new Intent(getApplicationContext(), SouceActivity.class); // 아침메뉴
                    startActivity(intent);
                }
            }
        });
    }
}
