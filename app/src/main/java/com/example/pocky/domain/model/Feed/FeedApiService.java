package com.example.pocky.domain.model.feed;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FeedApiService {
    @POST("/save/feed")
    Call<Void> postFeedData(@Body FeedData feedData);
}
