package com.example.pocky.domain.model.recommend;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecommendService {

    @GET("/chat")
    Call<RecommendDTO> getCommentData(@Query("prompt") String query);
}
