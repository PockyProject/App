package com.example.pocky.presentation.screen.order.common.souce;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivitySouceBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.topping.ToppingActivity;

import java.util.Arrays;
import java.util.List;

public class SouceActivity extends AppCompatActivity {
    private static List<Integer> imageList;
    private static List<String> souceName;
    private static List<String> qrSouceName; // 메뉴 객체에 들어갈 리스트
    private ActivitySouceBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySouceBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


        init();

        Menu arr = MenuSingleton.getInstance();

        RecyclerView recyclerView = findViewById(R.id.souce_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        SouceAdapter adapter = new SouceAdapter(imageList,souceName,qrSouceName, new SouceAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int imageResId, String souceName, String qrSouceName) {
                //클릭된 메뉴 이미지, 메뉴 이미지 싱글톤 객체에 담기

                arr.setSauceName(souceName);
                arr.setQrSauceName(qrSouceName);
                arr.setMenuImage(imageResId);

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
                if(arr.getSauceName() == null || arr.getSauceName().isEmpty()){
                    Toast myToast = Toast.makeText(getApplicationContext(),"먼저 소스를  선택해주세요", Toast.LENGTH_SHORT);
                    myToast.show();
                }else{
                    Log.d("BreakFastActivity","최종적으로 선택된 아이템 : " +
                            arr.getSauceName().get(0)+" "+
                            arr.getQrSauceName().get(0));
                    Intent intent = new Intent(getApplicationContext(), ToppingActivity.class); // 아침메뉴
                    startActivity(intent);
                }
            }
        });

        //뒤로가기 버튼
        binding.backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr.deleteQrSauceName();
                arr.deleteSauceName();
                finish();
            }
        });
    }

    void init(){
        // 소스 이름, 이미지 초기화

        imageList = Arrays.asList(
                R.drawable.resize_bbq,
                R.drawable.resize_honeymustard,
                R.drawable.resize_hotchili,
                R.drawable.resize_italiandressing,
                R.drawable.resize_mayo,
                R.drawable.resize_mustard,
                R.drawable.resize_oliveoli,
                R.drawable.resize_pepper,
                R.drawable.resize_ranch,
                R.drawable.resize_redwinevinegar,
                R.drawable.resize_salt,
                R.drawable.resize_smokebbq,
                R.drawable.resize_southwest,
                R.drawable.resize_soy,
                R.drawable.resize_sweetchili,
                R.drawable.resize_sweetonion,
                R.drawable.resize_tartar,
                R.drawable.resize_thousandisland,
                R.drawable.resize_wasabimayo
        );


        souceName = Arrays.asList(
                "비비큐",
                "허니머스타드",
                "핫칠리",
                "이탈리안드레싱",
                "마요네즈",
                "머스타드",
                "올리브오일", //
                "후추", //
                "랜치",
                "레드와인",
                "소금",
                "스모크비비큐",
                "사우스웨스트",
                "소이",
                "스위트칠리",
                "스위트어니언",
                "타르타르",
                "사우전드아일랜드",
                "와사비마요"
        );
        qrSouceName=Arrays.asList(
                "BBQ",
                "HONEYMUSTARD",
                "HOTCHILI",
                "ITALIANDRESSING",
                "MAYONNAISE",
                "MUSTARD",
                "OLIVEOIL",
                "PAPER",
                "RANCH",
                "REDWINE",
                "SALT",
                "SMOKEDBBQ",
                "SOUTHWEST",
                "SOY",
                "SWEETCHILI",
                "SWEETONION",
                "TARTARE",
                "THOUSANDISLAND",
                "WASABIMAYO"
        );

        // 사이드 이름 추가
    }


}
