package com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.addFeedActivity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.feed.FeedApiService;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.domain.repository.favor.Favor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFeedViewModel extends AndroidViewModel {

    private static String TAG = "AddFeedViewModel";
    private static MutableLiveData _titleText;
    private static LiveData titleText;

    public AddFeedViewModel(@NonNull Application application) {
        super(application);
    }

    //메뉴 인스턴스 가져와서 피드 모델로 변형
    public FeedData menuToFeed(Menu menu,String title, String content){
        FeedData data = new FeedData(
                UUID.randomUUID().toString(),
                UserInfo.getInstance().getUserId(),
                title,
                content,
                0,
                calcCurrentTime(),
                calcCurrentTime(),
                calcCurrentTime()
                //menu.getMenuImage()
        );

        return data;
    }

    //즐겨찾기 인스턴스 가져와서 피드 모델로 변경
    public FeedData favorToFeed(Favor favor,String title, String content){
        FeedData data = new FeedData(
                UUID.randomUUID().toString(),
                UserInfo.getInstance().getUserId(),
                title,
                content,
                0,
                calcCurrentTime(),
                calcCurrentTime(),
                calcCurrentTime()
        );

        return data;
    }

    // 변경된 모델 DB에 저장
    public void storedFeedDB(FeedData data){

        // API 인터페이스 연결
        FeedApiService api = RetrofitService.getInstance().getRetrofit().create(FeedApiService.class);
        Log.d(TAG,"피드 데이터 : "+ data.getFeedUid());
        Log.d(TAG,"피드 데이터 : "+ data.getUserUid());
        Log.d(TAG,"피드 데이터 : "+ data.getTitle());
        Log.d(TAG,"피드 데이터 : "+ data.getContent());
        Log.d(TAG,"피드 데이터 : "+ data.getDeleteAt());
        Log.d(TAG,"피드 데이터 : "+ data.getLikeCount());
        Log.d(TAG,"피드 데이터 : "+ data.getWritedDate());
        Log.d(TAG,"피드 데이터 : "+ data.getUpdateAt());
        api.postFeedData(data).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG,"피드 등록 성공 : "+ response.isSuccessful());
                Log.d(TAG,"피드 등록 성공 : "+ response.code());
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG,"피드 등록 실패 : "+ t.getCause());
                Log.d(TAG,"피드 등록 실패 : "+ t.getMessage());

            }
        });


    }

    // 저장한 데이터 로컬 캐시 처리

    // qr코드 변경 로직


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
