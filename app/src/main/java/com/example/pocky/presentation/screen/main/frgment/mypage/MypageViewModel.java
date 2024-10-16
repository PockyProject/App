package com.example.pocky.presentation.screen.main.frgment.mypage;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.feed.FeedApiService;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.domain.repository.favor.FavorRepository;
import com.example.pocky.domain.repository.orderList.Order;
import com.example.pocky.domain.repository.orderList.OrderRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MypageViewModel extends AndroidViewModel {

    private static final String TAG = "MypageViewModel";

    FavorRepository favorRepository;
    OrderRepository orderRepository;
    LiveData<List<Favor>> Favors;
    LiveData<List<Order>> Order;

    // 라이브 데이터 객체 선언
    private MutableLiveData<List<FeedData>> feedArr;

    // 라이브 데이터 값 반환 메서드
    public MutableLiveData<List<FeedData>> getFeed() {
        if (feedArr == null) {
            feedArr = new MutableLiveData<>();
        }
        return feedArr;
    }

    public void updateFeedArr(List<FeedData> arr){
        feedArr.setValue(arr);
    }





    public MypageViewModel(@NonNull Application application) {
        super(application);
        favorRepository = new FavorRepository(application); // 즐겨찾기 레포 초기화
        orderRepository = new OrderRepository(application); // 주문내역 레포 초기화

        Favors =  favorRepository.getAllFavors(); // 즐겨찾기 데이터 불러와서 리스트에 담기
        Order = orderRepository.getAllFavors(); // 주문내역 데이터 불러와서 리스트에 담기

    }

    public  LiveData<List<Favor>> getFavorList() {return Favors;} // 즐겨찾기 데이터 가져다 쓰는 함수
    public LiveData<List<Order>> getOrderList() {return Order;} // 주문내역 데이터 가져다 쓰는 함수

    public void getMyFeed(){
        FeedApiService api = RetrofitService.getInstance().getRetrofit().create(FeedApiService.class);

        api.getFeedData("123").enqueue(new Callback<List<FeedData>>() {
            @Override
            public void onResponse(Call<List<FeedData>> call, Response<List<FeedData>> response) {
                if(response.isSuccessful()){
                    Log.d(TAG,"데이터 요청 성공 : " + response.body());
                    Log.d(TAG,"데이터 요청 성공 : " + response.body().isEmpty());
                    updateFeedArr(response.body());
                }else{
                    Log.d(TAG,"데이터 요청 실패 : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<FeedData>> call, Throwable t) {
                Log.d(TAG,"DB와 연결 실패 : " + t.getMessage());
            }
        });
    }

}
