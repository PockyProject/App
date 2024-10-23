package com.example.pocky.domain.model.Comment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CommentApiService {
    @GET("/get/comment/{feedUid}")
    Call<List<CommentData>> getCommentData();

    @POST("/save/comment/{feeduid}")
    Call<Void> postCommentData(@Body CommentData CommentData);
}
