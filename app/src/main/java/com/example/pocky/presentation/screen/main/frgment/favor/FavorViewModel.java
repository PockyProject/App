package com.example.pocky.presentation.screen.main.frgment.favor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.domain.repository.favor.FavorRepository;

import java.util.List;

public class FavorViewModel extends AndroidViewModel {
    private FavorRepository repository;

    private LiveData<List<Favor>> Favors;

    public LiveData<List<Favor>> Favors() {
        if (Favors == null) {
            Favors = new MutableLiveData<>();
        }
        return Favors;
    }

    public FavorViewModel(@NonNull Application application) {
        super(application);
        repository = new FavorRepository(application);
        Favors = repository.getAllFavors();
    }


    public LiveData<List<Favor>> getFavorList() {
        return Favors;
    }


    //Favor 단일 저장
    public void insertFavor(Favor favor) {
        repository.insert(favor);
    }

    // Favor 여러개 저장
    public void insertAll(Favor... favors) {
        repository.insertAll(favors);
    }



    public void deleteFavor(Favor favor) {
        repository.delete(favor);
    }


}

