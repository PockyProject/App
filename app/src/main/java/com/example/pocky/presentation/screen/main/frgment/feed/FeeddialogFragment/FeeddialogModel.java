package com.example.pocky.presentation.screen.main.frgment.feed.FeeddialogFragment;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.pocky.domain.model.Comment.CommentApiService;
import com.example.pocky.domain.model.Comment.CommentData;
import com.example.pocky.domain.model.RetrofitService;

import org.w3c.dom.Comment;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeeddialogModel extends AndroidViewModel {

    private final static String TAG = "FeeddiaLog";
    private MutableLiveData<List<CommentData>> commentArr;

    public MutableLiveData<List<CommentData>> getCommentData() {
        if (commentArr == null) {
            commentArr = new MutableLiveData<>();
        }
        return commentArr;

}
    public void CommentupdateArr(List<CommentData> arr) {

        commentArr.setValue(arr);
    }

    public FeeddialogModel(@NonNull Application application) {
        super(application);
    }

    public void getComment() {
        CommentApiService api = RetrofitService.getInstance().getRetrofit().create(CommentApiService.class);
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            api.getCommentData().enqueue(new Callback<List<CommentData>>() {
                @Override
                public void onResponse(Call<List<CommentData>> call, Response<List<CommentData>> response) {
                    if (response.isSuccessful() ) {
                        Log.d(TAG, "데이터 요청 성공 : " + response.body());
                        Log.d(TAG, "데이터 요청 성공 : " + response.body().isEmpty());
                        CommentupdateArr(response.body());
                    } else {
                        Log.d(TAG, "데이터 요청 실패 : " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<List<CommentData>> call, Throwable t) {
                    Log.d(TAG, "DB와 연결 실패 : " + t.getMessage());
                }
            });
        });

    }
}
