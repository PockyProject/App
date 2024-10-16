package com.example.pocky.domain.model.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

interface UserAPIService {
    @POST("users/join")
    Call<Void> sendUserData(@Body UserData userData);

}
