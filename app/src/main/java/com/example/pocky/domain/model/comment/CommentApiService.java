package com.example.pocky.domain.model.comment;

import com.example.pocky.domain.model.feed.FeedData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommentApiService {
    @GET("/get/comment/{feedUid}")
    Call<List<CommentData>> getCommentData(@Path("feedUid") String feedUid);

    @POST("/save/comment/{feeduid}")
    Call<Void> postCommentData(@Body CommentData CommentData);

    @PUT("update/feed/{feedid}")
    Call<Void> postLikeCount(@Body FeedData count);


    @DELETE("/delete/comment/{commentuid}")
    Call<Void> deleteCommentData(@Path("commentuid") String commentUid);
}
