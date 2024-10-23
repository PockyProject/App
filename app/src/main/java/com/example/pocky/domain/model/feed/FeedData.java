package com.example.pocky.domain.model.feed;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.sql.Timestamp;

public class FeedData implements Serializable {

    @SerializedName("feedUid")
    private String feedUid; // 기본키

    @SerializedName("userUid")
    private String userUid;

    @SerializedName("qrImage")
    private String qrImage;

    @SerializedName("menuImage")
    private int menuImage;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("likeCount")
    private int likeCount;
    @SerializedName("createdat")
    private Timestamp createdat;
    @SerializedName("deletedat")
    private Timestamp deletedat;
    @SerializedName("updatedat")
    private Timestamp updatedat;

    public int getMenuImage() {
        return menuImage;
    }

    public String getFeedUid() {
        return feedUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCount() {
        return likeCount;
    }
    public String getQrImage() {
        return qrImage;
    }


    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Timestamp getWritedDate() {
        return createdat;
    }

    public Timestamp getDeleteAt() {
        return deletedat;
    }
    public Timestamp getUpdateAt() {
        return updatedat;
    }


    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }

    public FeedData(String feedUid, String userUid, String title, String content,
                    int likeCount, Timestamp writedDate, Timestamp deleteAt,
                    Timestamp updateAt,String qrImage,
                    int menuImage) {
        this.feedUid = feedUid;
        this.userUid = userUid;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.createdat = writedDate;
        this.deletedat = deleteAt;
        this.updatedat = updateAt;
        this.qrImage = qrImage;
        this.menuImage = menuImage;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // 동일한 객체일 경우
        if (obj == null || getClass() != obj.getClass()) return false; // 클래스 타입이 다르면 다름

        FeedData feedData = (FeedData) obj; // 형변환

        // 피드 UID를 비교하여 맞는지 확인
        return feedUid.equals(feedData.getFeedUid());
    }


}
