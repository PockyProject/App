package com.example.pocky.presentation.screen.main.frgment.mypage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.domain.repository.favor.FavorRepository;
import com.example.pocky.domain.repository.orderList.Order;
import com.example.pocky.domain.repository.orderList.OrderRepository;

import java.util.List;

public class MypageViewModel extends AndroidViewModel {

    FavorRepository favorRepository;
    OrderRepository orderRepository;
    LiveData<List<Favor>> Favors;
    LiveData<List<Order>> Order;

    public MypageViewModel(@NonNull Application application) {
        super(application);
        favorRepository = new FavorRepository(application); // 즐겨찾기 레포 초기화
        orderRepository = new OrderRepository(application); // 주문내역 레포 초기화

        Favors =  favorRepository.getAllFavors(); // 즐겨찾기 데이터 불러와서 리스트에 담기
        Order = orderRepository.getAllFavors(); // 주문내역 데이터 불러와서 리스트에 담기

    }

    public  LiveData<List<Favor>> getFavorList() {return Favors;} // 즐겨찾기 데이터 가져다 쓰는 함수
    public LiveData<List<Order>> getOrderList() {return Order;} // 주문내역 데이터 가져다 쓰는 함수

}
