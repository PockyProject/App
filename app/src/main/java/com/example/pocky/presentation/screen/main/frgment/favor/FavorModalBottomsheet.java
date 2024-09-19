package com.example.pocky.presentation.screen.main.frgment.favor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import com.example.pocky.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import java.io.FileOutputStream;

public class FavorModalBottomsheet  extends BottomSheetDialogFragment {
    static String TAG = "ModalBottomSheet";
    private Bitmap qrBitmap;

    // 생성자 또는 setter를 통해 QR 코드를 위한 데이터를 전달받는 방식
    public void setQrBitmap(Bitmap bitmap) {
        this.qrBitmap = bitmap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_bottom_sheet_content,container,false);
        ConstraintLayout bottomSheet = view.findViewById(R.id.bottomSheet);
        ImageView imageViewQrCode = view.findViewById(R.id.imageQrCode);
        Button button = view.findViewById(R.id.shareBtn);


        // QR 코드가 생성된 경우, 이미지 뷰에 설정
        if (qrBitmap != null) {
            imageViewQrCode.setImageBitmap(qrBitmap);
        }


        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setDraggable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback(){
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if(i == BottomSheetBehavior.STATE_DRAGGING){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                //없어도 됌
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ModalBottomSheet","클릭됌");
                if (qrBitmap != null) {
                    shareQrCode(qrBitmap);  // QR 코드 공유 메서드 호출
                } else {
                    Log.e(TAG, "QR 코드가 없습니다.");
                }
            }
        });

        return view;
    }


    // QR 코드를 공유하는 메서드
    private void shareQrCode(Bitmap bitmap) {
        try {
            // QR 코드를 임시 파일로 저장
            File cachePath = new File(requireContext().getCacheDir(), "images");
            cachePath.mkdirs();  // 디렉토리 생성
            File qrFile = new File(cachePath, "qr_code.png");
            FileOutputStream stream = new FileOutputStream(qrFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);  // 파일로 저장
            stream.close();

            // URI 생성
            Uri contentUri = FileProvider.getUriForFile(requireContext(), "com.android.application.fileprovider", qrFile);

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
            File cachePath = new File(requireContext().getCacheDir(), "images");
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



