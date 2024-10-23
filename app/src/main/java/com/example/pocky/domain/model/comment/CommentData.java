package com.example.pocky.domain.model.Comment;

import com.example.pocky.domain.model.feed.FeedData;
import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class CommentData {

    @SerializedName("commentUid")
    private String commentUid; // 기본키

    @SerializedName("feedUid")
    private String feedUid;

    @SerializedName("writer")
    private String writer;

    @SerializedName("writerImage")
    private String writerImage;

    @SerializedName("content")
    private String content;

    @SerializedName("writedDate")
    private Timestamp writedDate;

    @SerializedName("likedCount")
    private int likedCount;

    @SerializedName("deleteAt")
    private Timestamp deleteAt;

    @SerializedName("updateAt")
    private Timestamp updateAt;


    public String getCommentUid() {
        return commentUid;
    }

    public String getFeedUid() {
        return feedUid;
    }

    public String getWriter() {
        return writer;
    }

    public String getWriterImage() {
        return writerImage;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getWritedDate(String writerImage) {
        return writedDate;
    }

    public int getLikedCount() {
        return likedCount;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public CommentData(String commentUid, String feedUid, String writer, String writerImage, String content, Timestamp writedDate, int likedCount, Timestamp deleteAt, Timestamp updateAt) {
        this.commentUid = commentUid;
        this.feedUid = feedUid;
        this.writer = writer;
        this.writerImage = writerImage;
        this.content = content;
        this.writedDate = writedDate;
        this.likedCount = likedCount;
        this.deleteAt = deleteAt;
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // 동일한 객체일 경우
        if (obj == null || getClass() != obj.getClass()) return false; // 클래스 타입이 다르면 다름

        CommentData commentData = (CommentData) obj; // 형변환

        // 피드 UID를 비교하여 맞는지 확인
        return feedUid.equals(commentData.getFeedUid());
    }
}
