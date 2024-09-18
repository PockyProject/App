package com.example.pocky.presentation.screen.main.frgment.orderList;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.pocky.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class OrderModalBottomSheet extends BottomSheetDialogFragment {
    static String TAG = "ModalBottomSheet";
    private Bitmap qrBitmap;

    // 생성자 또는 setter를 통해 QR 코드를 위한 데이터를 전달받는 방식
    public void setQrBitmap(Bitmap bitmap) {
        this.qrBitmap = bitmap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_bottom_sheet_content, container, false);
        ConstraintLayout bottomSheet = view.findViewById(R.id.bottomSheet);
        ImageView imageViewQrCode = view.findViewById(R.id.imageQrCode);


        // QR 코드가 생성된 경우, 이미지 뷰에 설정
        if (qrBitmap != null) {
            imageViewQrCode.setImageBitmap(qrBitmap);
        }


        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setDraggable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);


        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if (i == BottomSheetBehavior.STATE_DRAGGING) {
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                //없어도 됌
            }
        });
        return view;
    }
}