package com.example.pocky.domain.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pocky.domain.repository.Favor.Favor;
import com.example.pocky.domain.repository.Favor.FavorDao;

//룸 설정
//싱글톤 구현

@Database(entities = {Favor.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract FavorDao favorDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "favor_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}