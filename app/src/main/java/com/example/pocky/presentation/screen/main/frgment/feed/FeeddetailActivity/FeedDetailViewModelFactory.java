package com.example.pocky.presentation.screen.main.frgment.feed.FeeddetailActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FeedDetailViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;

    public FeedDetailViewModelFactory(Application application) {this.mApplication = application;}

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass){

        if (modelClass.isAssignableFrom(FeedDetailViewModel.class)){
            return (T) new FeedDetailViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}