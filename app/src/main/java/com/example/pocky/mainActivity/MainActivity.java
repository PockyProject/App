package com.example.pocky.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.pocky.R;
import com.example.pocky.databinding.ActivityMainBinding;
import com.example.pocky.favorActivity.FavorActivity;
import com.example.pocky.model.user.UserInfo;
import com.example.pocky.moreActivity.MoreActivity;
import com.example.pocky.shoppingCartActivity.ShoppingCartActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;


public class MainActivity extends AppCompatActivity{
    //바인딩 변수 설정
    private static ActivityMainBinding binding;
    private static Toolbar toolbar;
    private static BottomNavigationView bottombar;
    private static MainViewModel viewModel;
    private static MainRecyclerViewAdapter recyclerViewAdapter;
    private static RecyclerView recyclerView;
    private String userName;
    //private MainMenuDTO menuArr;
    private List<String> menuName;
    private List<Integer> menuImg;



    private static final int ORDER_LIST_ICON_ID = R.id.orderListIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this)
                .get(MainViewModel.class);
        menuName = viewModel.initMenuName();
        menuImg = viewModel.initMenuImg();

        //바인딩 설정
        binding = ActivityMainBinding.inflate(getLayoutInflater());


        recyclerView = binding.selectMenuRecyclerView;
        recyclerViewAdapter = new MainRecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter.setArr(menuName,menuImg);


        userName = viewModel.initUserData();
        //액티비티에 xml 정보 전달
        setContentView(binding.getRoot());
        //유저 데이터 초기화
        binding.usernameText.setText(userName);
        //액티비티에 toolbar 정보 전달
        setSupportActionBar(toolbar);
        //상단바 초기화
        initTopBar(binding);
        //하단바 초기화
        initBottomBar(binding);
        //프로필사진 초기화
        initGlide(binding);

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
                    //item.setIcon()
                    return true;
                }else if(item.getItemId() == R.id.bottomMoreBtn){
                    openMoreActivity();
                    return true;
                }else if(item.getItemId() == R.id.bottomHomeBtn){

                }
                return false;
            }
        });
    }

    private void initRecyclerView(){

    }




}
