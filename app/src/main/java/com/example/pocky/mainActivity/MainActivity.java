package com.example.pocky.mainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.pocky.ComboActivity;
import com.example.pocky.R;
import com.example.pocky.favorActivity.FavorActivity;
import com.example.pocky.loginActivity.LoginActivity;
import com.example.pocky.model.user.KakaoUserInfo;
import com.example.pocky.databinding.ActivityMainBinding;
import com.example.pocky.model.user.UserInfo;
import com.example.pocky.moreActivity.MoreActivity;
import com.example.pocky.shoppingCartActivity.ShoppingCartActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity{
    private static ImageButton Classic_Burger, CheeseBurger, PoundBurger, DoubleCheeseBurger;
    private static ImageButton suggest1, suggest2, suggest3;

    //바인딩 변수 설정
    private static ActivityMainBinding binding;
    private static Toolbar toolbar;
    private static BottomNavigationView bottombar;

    private static final int ORDER_LIST_ICON_ID = R.id.orderListIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //바인딩 설정
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //액티비티에 xml 정보 전달
        setContentView(binding.getRoot());
        //유저 데이터 초기화
        initUserData();
        //액티비티에 toolbar 정보 전달
        setSupportActionBar(toolbar);
        //상단바 초기화
        initTopBar(binding);
        //하단바 초기화
        initBottomBar(binding);
        //프로필사진 초기화
        initGlide(binding);
        //메뉴 버튼 클릭 이벤트 로직
        View.OnClickListener burger = init();

        //메뉴 버튼 리스너 등록
        Classic_Burger.setOnClickListener(burger);
        CheeseBurger.setOnClickListener(burger);
        PoundBurger.setOnClickListener(burger);
        DoubleCheeseBurger.setOnClickListener(burger);
        suggest1.setOnClickListener(burger);
        suggest2.setOnClickListener(burger);
        suggest3.setOnClickListener(burger);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //top_app_bar menu 추가
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_top_app_bar,menu);
        return true;
    }
    private void openShoppingCartActivity() {
        Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);
        startActivity(intent);
        finish();
    }

    private void openFavorActivity() {
        Intent intent = new Intent(getApplicationContext(), FavorActivity.class);
        startActivity(intent);
        finish();
    }

    private void openMoreActivity() {
        Intent intent = new Intent(getApplicationContext(), MoreActivity.class);
        startActivity(intent);
        finish();
    }


    private View.OnClickListener init(){

        Classic_Burger = (ImageButton) findViewById(R.id.Classic_Burger);
        CheeseBurger = (ImageButton) findViewById(R.id.Cheese_Burger);
        PoundBurger = (ImageButton) findViewById(R.id.Pound_Burger);
        DoubleCheeseBurger = (ImageButton) findViewById(R.id.Double_Cheese_Burger);
        suggest1 = (ImageButton) findViewById(R.id.Suggest_1);
        suggest2 = (ImageButton) findViewById(R.id.Suggest_2);
        suggest3 = (ImageButton) findViewById(R.id.Suggest_3);
        View.OnClickListener burger = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();//추천메뉴
                if (id == R.id.Suggest_1) {
                    Toast.makeText(MainActivity.this, "치즈 버거", Toast.LENGTH_SHORT).show();
                    Intent intent_Suggest_1 = new Intent(MainActivity.this, ComboActivity.class);
                    intent_Suggest_1.putExtra("put_burger", "Cheese_Burger");
                    startActivity(intent_Suggest_1);
                    finish();
                } else if (id == R.id.Suggest_2) {
                    Toast.makeText(MainActivity.this, "더블 치즈 버거", Toast.LENGTH_SHORT).show();
                    Intent intent_Suggest_2 = new Intent(MainActivity.this, ComboActivity.class);
                    intent_Suggest_2.putExtra("put_burger", "Double_Cheese_Burger");
                    startActivity(intent_Suggest_2);
                    finish();
                } else if (id == R.id.Suggest_3) {
                    Toast.makeText(MainActivity.this, "클래식 버거", Toast.LENGTH_LONG).show();
                    Intent intent_Suggest_3 = new Intent(MainActivity.this, ComboActivity.class);
                    intent_Suggest_3.putExtra("put_burger", "Classic_Burger");
                    startActivity(intent_Suggest_3);
                    finish();

                    //메인메뉴
                } else if (id == R.id.Cheese_Burger) {
                    Toast.makeText(MainActivity.this, "치즈 버거", Toast.LENGTH_SHORT).show();
                    Intent intent_Cheese = new Intent(MainActivity.this, ComboActivity.class);
                    intent_Cheese.putExtra("put_burger", "Cheese_Burger");
                    startActivity(intent_Cheese);
                    finish();
                } else if (id == R.id.Double_Cheese_Burger) {
                    Toast.makeText(MainActivity.this, "더블 치즈 버거", Toast.LENGTH_SHORT).show();
                    Intent intent_Double_Cheese = new Intent(MainActivity.this, ComboActivity.class);
                    intent_Double_Cheese.putExtra("put_burger", "Double_Cheese_Burger");
                    startActivity(intent_Double_Cheese);
                    finish();
                } else if (id == R.id.Classic_Burger) {
                    Toast.makeText(MainActivity.this, "클래식 버거", Toast.LENGTH_LONG).show();
                    Intent intent_Classic = new Intent(MainActivity.this, ComboActivity.class);
                    intent_Classic.putExtra("put_burger", "Classic_Burger");
                    startActivity(intent_Classic);
                    finish();
                } else if (id == R.id.Pound_Burger) {
                    Toast.makeText(MainActivity.this, "파운드 버거", Toast.LENGTH_SHORT).show();
                    Intent intent_Pound = new Intent(MainActivity.this, ComboActivity.class);
                    intent_Pound.putExtra("put_burger", "Pound_Burger");
                    startActivity(intent_Pound);
                    finish();
                }
            }
        };
        return burger;
    }

    private void initGlide(ActivityMainBinding binding){
        Glide.with(this)
                .load(UserInfo.getInstance().getProfileURl())
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(20)))
                .into(binding.profileImageView);

    }
    private void initTopBar(ActivityMainBinding binding){
        //상단바 객체 초기화
        toolbar = binding.appbarLayoutItem;

        //상단바 이벤트 리스너
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == ORDER_LIST_ICON_ID) {
                    openShoppingCartActivity();
                    return true;
                }
                return false;
            }
        });
    }

    private void initBottomBar(ActivityMainBinding binding){
        bottombar = binding.bottomBar;
        bottombar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.bottomFavorBtn){
                    openFavorActivity();
                    return true;
                }else if(item.getItemId() == R.id.bottomMoreBtn){
                    openMoreActivity();
                    return true;
                }
                return false;
            }
        });
    }

    private void initUserData(){
        if (UserInfo.getInstance() != null) {
            try{
                Log.e("MainActivity","userNickname : " + UserInfo.getInstance().getNickname());
                binding.usernameText.setText(UserInfo.getInstance().getNickname()+"님!");
            }catch (NullPointerException e){
                Log.e("MainActivity","유저 정보 객체 호출 실패");
            }
        } else {
            Log.e("MainActivity","userInfo is null");
        }

    }
}
