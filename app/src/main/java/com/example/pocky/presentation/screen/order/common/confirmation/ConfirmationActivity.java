package com.example.pocky.presentation.screen.order.common.confirmation;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pocky.databinding.ActivityConfirmationBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.order.common.finalorder.FinalOrderActivity;

public class ConfirmationActivity extends AppCompatActivity {

    ActivityConfirmationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfirmationBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        Menu arr = MenuSingleton.getInstance();

        init(arr);

        binding.confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FinalOrderActivity.class); // QR 출력 화면 이동
                startActivity(intent);
            }
        });

        binding.backspaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    void init(Menu arr){
        Log.d("ConfirmationAcitivty","메뉴 이미지 :" + arr.getMenuImage());
        Log.d("ConfirmationAcitivty","메뉴 이미지 :" + arr.getMenuName());
        Log.d("ConfirmationAcitivty","메뉴 이미지 :" + arr.getBreadName());
        Log.d("ConfirmationAcitivty","메뉴 이미지 :" + arr.getSauceName());
        Log.d("ConfirmationAcitivty","메뉴 이미지 :" + arr.getToppingName());
        Log.d("ConfirmationAcitivty","메뉴 이미지 :" + arr.getSideName());
        Log.d("ConfirmationAcitivty","메뉴 이미지 :" + arr.getRequid());

        // arr null 체크
        if (arr.getMenuImage() != 0) { // 메뉴 이미지
            binding.confirmImageView.setImageResource(arr.getMenuImage());
            binding.confirmImageView.setVisibility(View.VISIBLE);
        } else {
            binding.confirmImageView.setVisibility(View.GONE);
        }

        if (arr.getMenuName() != null && !arr.getMenuName().isEmpty()) { // 메뉴 이름
            binding.confirmMenuNameTextView.setText("메뉴이름 : "+ arr.getMenuName());
            binding.confirmMenuNameTextView.setVisibility(View.VISIBLE);
        } else {
            binding.confirmMenuNameTextView.setVisibility(View.GONE);
        }

        if (arr.getBreadName() != null && !arr.getBreadName().isEmpty()) { // 빵
            binding.confirmBreadTextView.setText("빵 종류: " + arr.getBreadName());
            binding.confirmBreadTextView.setVisibility(View.VISIBLE);
        } else {
            binding.confirmBreadTextView.setVisibility(View.GONE);
        }

        if (arr.getSauceName() != null && !arr.getSauceName().isEmpty()) { // 소스
            binding.confirmSauceTextView.setText("선택한 소스 : " + arr.getSauceName());
            binding.confirmSauceTextView.setVisibility(View.VISIBLE);
        } else {
            binding.confirmSauceTextView.setVisibility(View.GONE);
        }

        if (arr.getToppingName() != null && !arr.getToppingName().isEmpty()) { // 토핑
            binding.confirmToppingTextView.setText("선택한 토핑 : " + arr.getToppingName());
            binding.confirmToppingTextView.setVisibility(View.VISIBLE);
        } else {
            binding.confirmToppingTextView.setVisibility(View.GONE);
        }

        if (arr.getSideName() != null && !arr.getSideName().isEmpty()) { // 사이드
            binding.confirmSideTextView.setText("선택한 사이드 : " + arr.getSideName());
            binding.confirmSideTextView.setVisibility(View.VISIBLE);
        } else {
            binding.confirmSideTextView.setVisibility(View.GONE);
        }

        if (arr.getRequid() != null && arr.getRequid()) { // 음료
            binding.confirmRequidTextView.setVisibility(View.VISIBLE);
            binding.confirmRequidTextView.setText("음료 여부 : 예");
        } else {
            binding.confirmRequidTextView.setText("음료 여부 : 아나오");
        }

    }
}