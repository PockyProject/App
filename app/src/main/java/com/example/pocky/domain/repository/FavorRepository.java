package com.example.pocky.domain.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FavorRepository {

    private FavorDao favorDao;
    private LiveData<List<Favor>> Favors;

    public FavorRepository(Application application) {
        FavorDatabase db = FavorDatabase.getInstance(application);
        favorDao = db.favorDao();
        Favors = favorDao.getAllFavors();
    }

    public LiveData<List<Favor>> getAllFavors() {
        return Favors;
    }

    public void insert(Favor favor) {
        new InsertFavorAsyncTask(favorDao).execute(favor);
    }

    public void delete(Favor favor) {
        new DeleteFavorAsyncTask(favorDao).execute(favor);
    }

    // 비동기 처리
    private static class InsertFavorAsyncTask extends AsyncTask<Favor, Void, Void> {
        private FavorDao favorDao;

        private InsertFavorAsyncTask(FavorDao favorDao) {
            this.favorDao = favorDao;
        }

        @Override
        protected Void doInBackground(Favor... favors) {
            favorDao.insert(favors[0]);
            return null;
        }
    }

    private static class DeleteFavorAsyncTask extends AsyncTask<Favor, Void, Void> {
        private FavorDao favorDao;

        private DeleteFavorAsyncTask(FavorDao favorDao) {
            this.favorDao = favorDao;
        }

        @Override
        protected Void doInBackground(Favor... favors) {
            favorDao.delete(favors[0]);
            return null;
        }
    }
}


