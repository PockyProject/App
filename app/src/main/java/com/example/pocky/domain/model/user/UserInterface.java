package com.example.pocky.domain.model.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserInterface {
    @GET("/users/id")
    Call<List<UserDTO>> getId(@Query("name") String name ,@Query("Nickname") String Nickname);

    @POST("/users/userpost")
    @Headers("Content-type: application/json")
    Call <UserDTO> setUserInfo(@Body UserDTO userdto);

}
