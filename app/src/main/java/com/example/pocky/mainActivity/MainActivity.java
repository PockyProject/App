package com.example.pocky.mainActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.pocky.data.user.KakaoUserInfo;
import com.example.pocky.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{
    Button Classic_Burger, CheeseBurger, PoundBurger, DoubleCheeseBurger;
    Button suggest1, suggest2, suggest3;

    //바인딩 변수 설정
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        //바인딩 설정
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //유저 정보 담는 변수
        KakaoUserInfo userInfo = (KakaoUserInfo) intent.getSerializableExtra("userInfo");

        if (userInfo != null) {
            try{
                Log.e("MainActivity","userNickname : " + userInfo.getNickname());
            }catch (NullPointerException e){
                Log.e("MainActivity","유저 정보 객체 호출 실패");
            }
        } else {
            Log.e("MainActivity","userInfo is null");
        }
        binding.usernameText.setText(userInfo.getNickname()+"님!");


       Glide.with(this)
                .load(userInfo.getProfilePhotoUrl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(binding.profileImageView);







//        Classic_Burger = (Button) findViewById(R.id.Classic_Burger);
//        CheeseBurger = (Button) findViewById(R.id.Cheese_Burger);
//        PoundBurger = (Button) findViewById(R.id.Pound_Burger);
//        DoubleCheeseBurger = (Button) findViewById(R.id.Double_Cheese_Burger);
//
//        suggest1 = (Button) findViewById(R.id.Suggest_1);
//        suggest2 = (Button) findViewById(R.id.Suggest_2);
//        suggest3 = (Button) findViewById(R.id.Suggest_3);
//
//        View.OnClickListener burger = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int id = v.getId();//추천메뉴
//                if (id == R.id.Suggest_1) {
//                    Toast.makeText(MainActivity.this, "치즈 버거", Toast.LENGTH_SHORT).show();
//                    Intent intent_Suggest_1 = new Intent(MainActivity.this, ComboActivity.class);
//                    intent_Suggest_1.putExtra("put_burger", "Cheese_Burger");
//                    startActivity(intent_Suggest_1);
//                    finish();
//                } else if (id == R.id.Suggest_2) {
//                    Toast.makeText(MainActivity.this, "더블 치즈 버거", Toast.LENGTH_SHORT).show();
//                    Intent intent_Suggest_2 = new Intent(MainActivity.this, ComboActivity.class);
//                    intent_Suggest_2.putExtra("put_burger", "Double_Cheese_Burger");
//                    startActivity(intent_Suggest_2);
//                    finish();
//                } else if (id == R.id.Suggest_3) {
//                    Toast.makeText(MainActivity.this, "클래식 버거", Toast.LENGTH_LONG).show();
//                    Intent intent_Suggest_3 = new Intent(MainActivity.this, ComboActivity.class);
//                    intent_Suggest_3.putExtra("put_burger", "Classic_Burger");
//                    startActivity(intent_Suggest_3);
//                    finish();
//
//                    //메인메뉴
//                } else if (id == R.id.Cheese_Burger) {
//                    Toast.makeText(MainActivity.this, "치즈 버거", Toast.LENGTH_SHORT).show();
//                    Intent intent_Cheese = new Intent(MainActivity.this, ComboActivity.class);
//                    intent_Cheese.putExtra("put_burger", "Cheese_Burger");
//                    startActivity(intent_Cheese);
//                    finish();
//                } else if (id == R.id.Double_Cheese_Burger) {
//                    Toast.makeText(MainActivity.this, "더블 치즈 버거", Toast.LENGTH_SHORT).show();
//                    Intent intent_Double_Cheese = new Intent(MainActivity.this, ComboActivity.class);
//                    intent_Double_Cheese.putExtra("put_burger", "Double_Cheese_Burger");
//                    startActivity(intent_Double_Cheese);
//                    finish();
//                } else if (id == R.id.Classic_Burger) {
//                    Toast.makeText(MainActivity.this, "클래식 버거", Toast.LENGTH_LONG).show();
//                    Intent intent_Classic = new Intent(MainActivity.this, ComboActivity.class);
//                    intent_Classic.putExtra("put_burger", "Classic_Burger");
//                    startActivity(intent_Classic);
//                    finish();
//                } else if (id == R.id.Pound_Burger) {
//                    Toast.makeText(MainActivity.this, "파운드 버거", Toast.LENGTH_SHORT).show();
//                    Intent intent_Pound = new Intent(MainActivity.this, ComboActivity.class);
//                    intent_Pound.putExtra("put_burger", "Pound_Burger");
//                    startActivity(intent_Pound);
//                    finish();
//                }
//            }
//        };
//
//        Classic_Burger.setOnClickListener(burger);
//        CheeseBurger.setOnClickListener(burger);
//        PoundBurger.setOnClickListener(burger);
//        DoubleCheeseBurger.setOnClickListener(burger);
//        suggest1.setOnClickListener(burger);
//        suggest2.setOnClickListener(burger);
//        suggest3.setOnClickListener(burger);
    }

}
