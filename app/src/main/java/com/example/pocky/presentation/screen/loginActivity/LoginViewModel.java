package com.example.pocky.presentation.screen.loginActivity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.user.UserAPIService;
import com.example.pocky.domain.model.user.UserData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private static String TAG = "LoginViewModel";


    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public void postUserInfo(UserData data) {
        UserAPIService api = RetrofitService.getInstance().getRetrofit().create(UserAPIService.class);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            api.sendUserData(data).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "등록 성공" + response.code());
                    } else {
                        Log.d(TAG, "등록 실패" + response.code());
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d(TAG, "연결 실패" + t.getCause());
                }
            });
        });
    }
}
