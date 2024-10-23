package com.example.pocky.presentation.screen.main.frgment.feed.FeeddialogFragment;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.pocky.presentation.screen.main.frgment.feed.FeedViewModel;

public class FeeddialogModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;

    public FeeddialogModelFactory(Application application){this.mApplication = application;}

    @NonNull
    @Override
    public<T extends ViewModel> T create(@NonNull Class<T> modelClass){
        if (modelClass.isAssignableFrom(FeedViewModel.class)){
            return (T) new FeeddialogModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
