package com.example.pocky.domain.repository.orderList;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Order order);  // 데이터 삽입

    @Insert
    void insertAll(Order... orders);

    @Delete
    void delete(Order order);  // 데이터 삭제

    @Query("SELECT * FROM `Order`")
    LiveData<List<Order>> getAllOrder();  // 모든 데이터 조회

    @Query("SELECT COUNT(*) FROM `Order`")
    int getOrderCount();  // 현재 저장된 Favor의 개수 확인
}
