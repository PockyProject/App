package com.example.pocky.presentation.screen.main.frgment.feed;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FeedViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;

    public FeedViewModelFactory(Application application) {this.mApplication = application;}

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){

        if (modelClass.isAssignableFrom(FeedViewModel.class)){
            return (T) new FeedViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
