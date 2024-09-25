package com.example.pocky.domain.model.db;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface DBApiService {
    @POST("user/userpost")
    Call<Void> sendUserData(@Body DBData DBData);
}
