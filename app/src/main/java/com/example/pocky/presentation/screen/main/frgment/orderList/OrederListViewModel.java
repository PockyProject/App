package com.example.pocky.presentation.screen.main.frgment.orderList;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pocky.domain.repository.orderList.Order;
import com.example.pocky.domain.repository.orderList.OrderRepository;

import java.util.List;

public class OrederListViewModel extends AndroidViewModel {
    private OrderRepository repository;

    private LiveData<List<Order>> Orders;

    public LiveData<List<Order>> Orders() {
        if (Orders == null) {
            Orders = new MutableLiveData<>();
        }
        return Orders;
    }

    public OrederListViewModel(@NonNull Application application) {
        super(application);
        repository = new OrderRepository(application);
        Orders = repository.getAllFavors();
    }


    public LiveData<List<Order>> getFavorList() {
        return Orders;
    }


    //Favor 단일 저장
    public void insertFavor(Order order) {
        repository.insert(order);
    }

    // Favor 여러개 저장
    public void insertAll(Order order) {
        repository.insertAll(order);
    }



    public void deleteFavor(Order order) {
        repository.delete(order);
    }
}
