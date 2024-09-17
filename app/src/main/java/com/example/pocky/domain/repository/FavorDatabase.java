package com.example.pocky.domain.repository;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Favor.class}, version = 1)
public abstract class FavorDatabase extends RoomDatabase {

    private static FavorDatabase instance;

    public abstract FavorDao favorDao();

    // Room 데이터베이스를 싱글톤으로 관리
    public static synchronized FavorDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            FavorDatabase.class, "favor_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
