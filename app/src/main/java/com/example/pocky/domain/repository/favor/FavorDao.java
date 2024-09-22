package com.example.pocky.domain.repository.favor;

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

    @Insert
    void insertAll(Favor... favors);

    @Delete
    void delete(Favor favor);  // 데이터 삭제

    @Query("SELECT * FROM Favor")
    LiveData<List<Favor>> getAllFavors();  // 모든 데이터 조회

    @Query("SELECT COUNT(*) FROM Favor")
    int getFavorCount();  // 현재 저장된 Favor의 개수 확인
}