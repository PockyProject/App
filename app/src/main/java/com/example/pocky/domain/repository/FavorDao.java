package com.example.pocky.domain.repository;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavorDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Favor favor);  // 데이터 삽입

    @Delete
    void delete(Favor favor);  // 데이터 삭제

    @Query("SELECT * FROM Favor")
    LiveData<List<Favor>> getAllFavors();  // 모든 데이터 조회
}