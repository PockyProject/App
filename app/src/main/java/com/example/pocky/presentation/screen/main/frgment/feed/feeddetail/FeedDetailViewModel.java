package com.example.pocky.presentation.screen.main.frgment.feed.feeddetail;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class FeedDetailViewModel extends AndroidViewModel {


    public FeedDetailViewModel(@NonNull Application application) {
        super(application);
    }

    public Bitmap decodeBase64ToBitmap(String base64String) {
        try {
            // Base64.NO_WRAP을 사용하여 줄바꿈 문자 문제 방지
            byte[] decodedString = Base64.decode(base64String, Base64.NO_WRAP);
            return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("DecodeError", "Base64 문자열이 잘못되었습니다: " + base64String);
            return null;
        }
    }
}
