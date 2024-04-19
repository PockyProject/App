package com.example.pocky.data.favor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface FavorInterface {

    @GET("/favor/getfavor")
    Call<List<FavorDTO>> getFavor(@Query("id") String id);

    @POST("/favor/postfavor")
    @Headers("Content-type: application/json")
    Call<FavorDTO> setFavorInfo(@Body FavorDTO favordto);
}
