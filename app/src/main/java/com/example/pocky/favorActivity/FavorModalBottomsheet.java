package com.example.pocky.favorActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.pocky.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class FavorModalBottomsheet  extends BottomSheetDialogFragment {
    static String TAG = "ModalBottomSheet";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_bottom_sheet_content,container,false);
        ConstraintLayout bottomSheet = view.findViewById(R.id.bottomSheet);

        BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        bottomSheetBehavior.setDraggable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        // Generate QR code
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap("Hello, world!", BarcodeFormat.QR_CODE, 400, 400);

            // Set the QR code to the ImageView
            AppCompatImageView imageViewQrcode = view.findViewById(R.id.imageQrCode);
            if (imageViewQrcode != null) {
                imageViewQrcode.setImageBitmap(bitmap);
            } else {
                throw new NullPointerException("ImageView for QR Code is null");
            }
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }


        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback(){
            @Override
            public void onStateChanged(@NonNull View view, int i) {
                if(i == BottomSheetBehavior.STATE_DRAGGING){
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {

            }
        });
        return view;
    }




}


