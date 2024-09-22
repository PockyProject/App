package com.example.pocky.presentation.screen.main.frgment.favor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FavorViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;

    // 생성자를 통해 Application 객체를 전달받음
    public FavorViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        // ViewModel 클래스가 FavorViewModel인지 확인 후 인스턴스 생성
        if (modelClass.isAssignableFrom(FavorViewModel.class)) {
            return (T) new FavorViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}

