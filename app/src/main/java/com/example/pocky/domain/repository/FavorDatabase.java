package com.example.pocky.domain.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Favor.class}, version = 1)
public abstract class FavorDatabase extends RoomDatabase {

    private static FavorDatabase instance;

    public abstract FavorDao favorDao();
    private static final int NUMBER_OF_THREADS = 4;

    // 백그라운드 스레드 데이터 작업을 위한 서비스 설정
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Room 데이터베이스를 싱글톤으로 관리
    public static synchronized FavorDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            FavorDatabase.class, "Favor")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
