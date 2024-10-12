package com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.addFeedActivity;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class AddFeedViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;

    // 생성자를 통해 Application 객체를 전달받음
    public AddFeedViewModelFactory(Application application) {
        this.mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        // ViewModel 클래스가 MypageViewModel 확인 후 인스턴스 생성
        if (modelClass.isAssignableFrom(AddFeedViewModel.class)) {
            return (T) new AddFeedViewModel(mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}