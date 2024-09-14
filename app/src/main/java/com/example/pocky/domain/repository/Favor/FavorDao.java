package com.example.pocky.domain.repository.Favor;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface FavorDao { // 즐겨찾기 테이블 접근 인터페이스

    @Insert
    void insert(Favor favor);

    @Update
    void update(Favor favor);

    @Delete
    void delete(Favor favor);

    @Query("SELECT * FROM Favor")
    List<Favor> getAllFavors();

    @Query("SELECT * FROM Favor WHERE id = :id")
    Favor getFavorById(int id);
}