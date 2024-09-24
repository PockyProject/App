package com.example.pocky.presentation.screen.order.common.confirmation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class ConfirmationViewModel extends AndroidViewModel {
    public ConfirmationViewModel(@NonNull Application application) {
        super(application);
    }

    public String convertArr(List<String> arr){
        String temp = "";

        for (int i = 0; i < arr.size(); i++) {

            if(i == arr.size() -1){
                temp+=arr.get(i);
            }else{
                temp +=arr.get(i) + ",";
            }
        }

        return temp;
    }
}
