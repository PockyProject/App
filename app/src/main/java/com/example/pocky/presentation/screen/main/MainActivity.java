package com.example.pocky.presentation.screen.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivityMainBinding;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorFragment;
import com.example.pocky.presentation.screen.main.frgment.main.MainFrgment;
import com.example.pocky.presentation.screen.main.frgment.main.MainViewModel;
import com.example.pocky.presentation.screen.main.frgment.more.MoreFragment;
import com.example.pocky.presentation.screen.main.frgment.orderList.OrderListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity{
    //바인딩 변수 설정

    private static ActivityMainBinding binding;
    private static Toolbar toolbar;
    private static MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //바인딩 설정
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(savedInstanceState == null){
            loadFragment(new MainFrgment());
        }

        viewModel = new ViewModelProvider(this)
                .get(MainViewModel.class);


        toolbar = binding.appbarLayoutItem;
        //액티비티에 toolbar 정보 전달
        setSupportActionBar(toolbar);



        //하단바 초기화
        binding.bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.bottomHomeBtn){
                    loadFragment(new MainFrgment());
                    return true;
                }else if(menuItem.getItemId() == R.id.bottomFavorBtn){
                    // 즐겨찾기 버튼 클릭 시
                    loadFragment(new FavorFragment());
                    return true;
                }else if(menuItem.getItemId() == R.id.bottomOrderListBtn){
                    //주문내역 버튼 클릭 시
                    loadFragment(new OrderListFragment());
                    return true;
                }else if(menuItem.getItemId() == R.id.bottomMoreBtn){
                    loadFragment(new MoreFragment());
                    return true;
                }
                return false;
            }
        });

        if(savedInstanceState == null){
            loadFragment(new MainFrgment());
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //top_app_bar menu 추가
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_top_app_bar,menu);
        return true;
    }

    private void loadFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(binding.fragmentContentView.getId(),fragment)
                .commit();
    }

    // 프래그먼트에서 호출할 수 있도록 바텀 네비게이션 아이콘 색깔 변경 메서드 정의
    public void setSelectedIconColor(int menuItemId) {
        binding.bottomBar.setSelectedItemId(menuItemId);
    }

}
