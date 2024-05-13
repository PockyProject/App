package com.example.pocky.favorActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivityFavorBinding;
import com.example.pocky.databinding.ActivityMainBinding;
import com.example.pocky.mainActivity.MainActivity;

import java.util.Objects;

public class FavorActivity extends AppCompatActivity {
    ActivityFavorBinding binding;
    private static Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavorBinding.inflate(getLayoutInflater());
        toolbar = binding.appbarLayoutItem;
        setContentView(binding.getRoot());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //initTopBar(binding);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //    private void initTopBar(ActivityFavorBinding binding){
//        //상단바 객체 초기화
//        toolbar = binding.appbarLayoutItem;
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//    }
}
