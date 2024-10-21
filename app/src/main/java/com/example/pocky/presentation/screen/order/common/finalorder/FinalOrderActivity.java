package com.example.pocky.presentation.screen.order.common.finalorder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;

import com.example.pocky.databinding.ActivityFinalorderBinding;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.menu.MenuSingleton;
import com.example.pocky.presentation.screen.main.MainActivity;

import java.io.File;
import java.io.FileOutputStream;

public class FinalOrderActivity extends AppCompatActivity {
    ActivityFinalorderBinding binding;
    private FinalOrderViewModel viewModel;
    private static final String TAG = "FinalOrderActivity";
    private Bitmap qrImage = null;

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


        // 큐알 이미지 옵저빙으로 가져오기
        viewModel.getQrCodeBitmap().observe(this, bitmap -> {
            qrImage = bitmap;
        });


        binding.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 공유 기능 활성화
                shareQrCode(qrImage);
            }
        });

        binding.gotoHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 주문완료 & 홈으로 버튼
                viewModel.insertOrder(menu);
                MenuSingleton.removeInstance(); // 메뉴 정보 담은 싱글톤 인스턴스 삭제
                Intent intent = new Intent(getApplicationContext(), MainActivity.class); // 최종 메뉴 확인 화면 이동
                startActivity(intent);
            }
        });

        binding.favorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 즐겨찾기 추가 버튼
                viewModel.insertFavor(menu); // 룸 저장
                viewModel.storedDb(menu); // MySql 저장
            }
        });
    }

    // QR 코드를 공유하는 메서드
    private void shareQrCode(Bitmap bitmap) {
        try {
            // QR 코드를 임시 파일로 저장
            File cachePath = new File(this.getCacheDir(), "images");
            cachePath.mkdirs();  // 디렉토리 생성
            File qrFile = new File(cachePath, "qr_code.png");
            FileOutputStream stream = new FileOutputStream(qrFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);  // 파일로 저장
            stream.close();

            // URI 생성
            Uri contentUri = FileProvider.getUriForFile(this, "com.android.application.fileprovider", qrFile);

            // 공유 인텐트 생성
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/png");
            shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            // 공유 Intent 실행
            startActivityForResult(Intent.createChooser(shareIntent, "QR 코드 공유"), 100);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 공유 후 파일 삭제 메서드
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            // 공유 후 QR 코드 파일 삭제
            File cachePath = new File(this.getCacheDir(), "images");
            File qrFile = new File(cachePath, "qr_code.png");
            if (qrFile.exists()) {
                boolean deleted = qrFile.delete();
                if (deleted) {
                    Log.d(TAG, "파일이 성공적으로 삭제되었습니다.");
                } else {
                    Log.e(TAG, "파일 삭제에 실패했습니다.");
                }
            }
        }
    }
}
