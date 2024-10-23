package com.example.pocky.presentation.screen.main.frgment.feed;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.feed.FeedApiService;
import com.example.pocky.domain.model.feed.FeedData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedViewModel extends AndroidViewModel {

    private final static String TAG = "FeedFragments";
    private MutableLiveData<List<FeedData>> feedArr;

    public MutableLiveData<List<FeedData>> getFeed() {
        if (feedArr == null) {
            feedArr = new MutableLiveData<>();
        }
        return feedArr;
    }

    public void FeedupdateArr(List<FeedData> arr) {
        feedArr.setValue(arr);
    }

    public FeedViewModel(@NonNull Application application) {
        super(application);
    }

    public void getAllFeed() {
        FeedApiService api = RetrofitService.getInstance().getRetrofit().create(FeedApiService.class);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            api.getAllFeedData().enqueue(new Callback<List<FeedData>>() {
                @Override
                public void onResponse(Call<List<FeedData>> call, Response<List<FeedData>> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "데이터 요청 성공 : " + response.body());
                        Log.d(TAG, "데이터 요청 성공 : " + response.body().isEmpty());
                        FeedupdateArr(response.body());
                    } else {
                        Log.d(TAG, "데이터 요청 실패 : " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<FeedData>> call, Throwable t) {
                    Log.d(TAG, "DB와 연결 실패 : " + t.getMessage());
                }
            });
        });

    }
}