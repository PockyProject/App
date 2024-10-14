package com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.addFeedActivity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.feed.FeedApiService;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.domain.repository.favor.Favor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFeedViewModel extends AndroidViewModel {

    private static String TAG = "AddFeedViewModel";

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

        api.postFeedData(data).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.d(TAG,"피드 등록 성공 : "+ response.message());
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG,"피드 등록 실패 : "+ t.getCause());
            }
        });


    }

    // 저장한 데이터 로컬 캐시 처리

    // qr코드 변경 로직


    // 현재 시간 구하는 함수
    public Timestamp calcCurrentTime(){
        LocalDateTime currentDateTime = LocalDateTime.now(); // 컴퓨터의 현재 날짜
        Timestamp timestamp = Timestamp.valueOf(String.valueOf(currentDateTime)); // LocalDateTime을 Timestamp로 변환
        Log.d("AddFeedViewModel","피드 등록 날짜 및 시간 : " + currentDateTime);
        Log.d("AddFeedViewModel","피드 등록 날짜 및 시간 : " + timestamp);
        return timestamp;
    }
}
