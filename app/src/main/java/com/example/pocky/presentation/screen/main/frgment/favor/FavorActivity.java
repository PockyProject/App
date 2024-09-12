package com.example.pocky.presentation.screen.main.frgment.favor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.pocky.R;
import com.example.pocky.databinding.ActivityFavorBinding;
import com.example.pocky.presentation.screen.main.MainActivity;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorModalBottomsheet;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class FavorActivity extends AppCompatActivity {
    ActivityFavorBinding binding;
    private static Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavorBinding.inflate(getLayoutInflater());
        toolbar = binding.appbarLayoutItem;
        FavorModalBottomsheet bottomsheet = new FavorModalBottomsheet();
        setContentView(binding.getRoot());
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        binding.orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomsheet.show(getSupportFragmentManager(),FavorModalBottomsheet.TAG);
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap("Hello, world!", BarcodeFormat.QR_CODE, 300, 300);
                    ImageView imageViewQrcode = findViewById(R.id.imageQrCode);
                    imageViewQrcode.setImageBitmap(bitmap);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });

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

}
