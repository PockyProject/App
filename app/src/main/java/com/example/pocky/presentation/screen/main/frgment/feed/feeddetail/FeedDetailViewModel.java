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

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
                        //Log.d(TAG,"데이터 요청 성공 : " + response.body().get(0).getContent());
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

    public void postComment(CommentData data) {
        CommentApiService api = RetrofitService.getInstance().getRetrofit().create(CommentApiService.class);
        // ExecutorService 생성 (스레드 풀)
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 네트워크 요청 비동기 처린
        executor.execute(() -> {
            api.postCommentData(data).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"데이터 등록 성공 : " + response.code());
                    }else{
                        Log.d(TAG,"데이터 등록 실패 : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d(TAG,"서버와 연결 실패  : " + t.getMessage());
                }
            });
        });
    }

    public void deleteComment(String commentUid){
        CommentApiService api = RetrofitService.getInstance().getRetrofit().create(CommentApiService.class);
        // ExecutorService 생성 (스레드 풀)
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 네트워크 요청 비동기 처린
        executor.execute(() -> {
            api.deleteCommentData(commentUid).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if(response.isSuccessful()){
                        Log.d(TAG,"데이터 삭제 성공 : " + response.code());
                    }else{
                        Log.d(TAG,"데이터 삭제 실패 : " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d(TAG,"서버와 연결 실패  : " + t.getMessage());
                }
            });
        });
    }

    // 현재 시간 구하는 함수
    public Timestamp calcCurrentTime(){

        // 현재 날짜와 시간 구하기
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 정확한 형식으로 변환 (yyyy-MM-dd HH:mm:ss)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        // 변환된 문자열을 Timestamp로 변환
        Timestamp timestamp = Timestamp.valueOf(formattedDateTime);

        // 로그 출력
        Log.d("AddFeedViewModel", "피드 등록 날짜 및 시간 : " + currentDateTime);
        Log.d("AddFeedViewModel", "피드 등록 날짜 및 시간 : " + timestamp);

        return timestamp;
    }
}
