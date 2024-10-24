package com.example.pocky.presentation.screen.main.frgment.feed.feeddetail;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.comment.CommentApiService;
import com.example.pocky.domain.model.comment.CommentData;
import com.example.pocky.domain.model.user.UserInfo;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedDetailViewModel extends AndroidViewModel {
    private static final String TAG = "FeedDetailViewModel";

    private MutableLiveData<List<CommentData>> Comment;

    public MutableLiveData<List<CommentData>> getData() {
        if (Comment == null) {
            Comment = new MutableLiveData<>();
        }
        return Comment;
    }


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

    public void getComment(String feedUid) {
        CommentApiService api = RetrofitService.getInstance().getRetrofit().create(CommentApiService.class);
        String userId = UserInfo.getInstance().getUserId();
        // ExecutorService 생성 (스레드 풀)
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 네트워크 요청 비동기 처린
        executor.execute(() -> {
            api.getCommentData(feedUid).enqueue(new Callback<List<CommentData>>() {
                @Override
                public void onResponse(Call<List<CommentData>> call, Response<List<CommentData>> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"데이터 요청 성공 : " + response.body());
                        Log.d(TAG,"데이터 요청 성공 : " + response.body().get(0).getContent());
                        Comment.setValue(response.body());
                    }else{
                        Log.d(TAG,"데이터 요청 실패 : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<CommentData>> call, Throwable t) {
                    Log.d(TAG,"서버와 연결 실패  : " + t.getMessage());
                }
            });
        });
    }
}
