package com.example.pocky.presentation.screen.order.common.finalorder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class FinalOrderViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;

    // 생성자를 통해 Application 객체를 전달받음
    public FinalOrderViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel > T create(@NonNull Class<T> modelClass) {

        // ViewModel 클래스가 FinalOrderViewModel 확인 후 인스턴스 생성
        if (modelClass.isAssignableFrom(FinalOrderViewModel.class)) {
            return (T) new FinalOrderViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
