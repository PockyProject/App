package com.example.pocky.presentation.screen.order.common.finalorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pocky.databinding.ActivityFinalorderBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.main.MainActivity;

public class FinalOrderActivity extends AppCompatActivity {
    ActivityFinalorderBinding binding;
    private FinalOrderViewModel viewModel ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityFinalorderBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Menu menu = MenuSingleton.getInstance();

        //뷰모델 초기화
        FinalOrderViewModelFactory factory = new FinalOrderViewModelFactory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(FinalOrderViewModel.class);

        //qr코드 출력
        binding.qrImageView.setImageBitmap(viewModel.generateQrCode(menu));


        binding.captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // qr 코드 캡처


            }
        });

        binding.gotoHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 홈으로 버튼
                MenuSingleton.removeInstance(); // 메뉴 정보 담은 싱글톤 인스턴스 삭제
                Intent intent = new Intent(getApplicationContext(), MainActivity.class); // 최종 메뉴 확인 화면 이동
                startActivity(intent);
            }
        });

        binding.favorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 즐겨찾기 추가 버튼
                viewModel.insertFavor(menu);
            }
        });
    }
}
