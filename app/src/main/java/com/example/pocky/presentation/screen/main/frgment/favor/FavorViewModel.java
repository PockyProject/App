package com.example.pocky.presentation.screen.main.frgment.favor;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pocky.R;
import com.example.pocky.domain.model.Favor.Favor;

import java.util.ArrayList;
import java.util.List;

public class FavorViewModel extends ViewModel {
    private final MutableLiveData<List<Favor>> favorList = new MutableLiveData<>();

    public FavorViewModel() {
        //TODO Room 데이터 Read, Create 작업 만들기
        List<Favor> sampleData = new ArrayList<>();
        sampleData.add(new Favor(R.drawable.resize_foldfork,
                "메뉴이름", 1,
                "빵 : 플렛브래드", "소스 : 소스1소스2소스2","토핑 : 토핑토핑토핑토핑토핑토핑",
                "사이드: 더블 초코칩쿠키","음료 : 사이다"));
        sampleData.add(new Favor(R.drawable.resize_foldfork,
                "메뉴이름", 1,
                "빵", "소스","토핑","사이드","음료"));
        sampleData.add(new Favor(R.drawable.resize_foldfork,
                "메뉴이름", 1,
                "빵", "소스","토핑","사이드","음료"));
        favorList.setValue(sampleData);
    }

    public LiveData<List<Favor>> getFavorList() {
        return favorList;
    }


}
