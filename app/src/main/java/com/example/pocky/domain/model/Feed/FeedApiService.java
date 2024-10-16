package com.example.pocky.domain.model.feed;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface FeedApiService {

    @GET("/get/feed/{userid}")
    Call<List<FeedData>> getFeedData(@Path("userid") String  userid);

    @POST("/save/feed")
    Call<Void> postFeedData(@Body FeedData feedData);
}
