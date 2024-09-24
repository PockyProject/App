package com.example.pocky.domain.repository;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.domain.repository.favor.FavorDao;
import com.example.pocky.domain.repository.orderList.Order;
import com.example.pocky.domain.repository.orderList.OrderDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@androidx.room.Database(entities = {Favor.class, Order.class}, version = 1) @TypeConverters(Converter.class)
public abstract class Database extends RoomDatabase {

    private static Database instance;

    public abstract FavorDao favorDao();
    public abstract OrderDao orderDao();

    private static final int NUMBER_OF_THREADS = 4;



    // 백그라운드 스레드 데이터 작업을 위한 서비스 설정
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // Room 데이터베이스를 싱글톤으로 관리
    public static synchronized Database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            Database.class, "Database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
