package com.example.pocky.presentation.screen.main.frgment.favor;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.favor.FavorDTO;
import com.example.pocky.domain.model.favor.FavorInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavorViewModel extends ViewModel {
    private static final FavorInterface favorList = RetrofitService.getInstance().getRetrofit().create(FavorInterface.class);
    private MutableLiveData<FavorDTO> FavorList;
    public MutableLiveData<FavorDTO> _FavorList() {
        if (FavorList == null) {
            FavorList = new MutableLiveData<FavorDTO>();
        }
        return FavorList;
    }

    public void getFavorList(){
        Call<List<FavorDTO>> list = favorList.getFavor("testid");
       list.enqueue(new Callback<List<FavorDTO>>() {
           @Override
           public void onResponse(Call<List<FavorDTO>> call, Response<List<FavorDTO>> response) {
               FavorList  = (MutableLiveData<FavorDTO>) response.body();
               Log.d("FavorViewModel","즐겨찾기 정보 호출 실패");
           }

           @Override
           public void onFailure(Call<List<FavorDTO>> call, Throwable t) {
                Log.d("FavorViewModel","즐겨찾기 정보 호출 실패");
           }
       });
    }




}
