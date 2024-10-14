package com.example.pocky.domain.model.feed;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class FeedData {

    @SerializedName("feedUid")
    private String feedUid; // 기본키

    @SerializedName("userUid")
    private String userUid;

//    @SerializedName("profileImage")
//    private String profileImage;

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
    @SerializedName("writedDate")
    private Timestamp writedDate;
    @SerializedName("deleteAt")
    private Timestamp deleteAt;
    @SerializedName("updateAt")
    private Timestamp updateAt;


//    public String getProfileImage() {
//        return profileImage;
//    }
    public int getMenuImage() {
        return menuImage;
    }

    public String getFeedUid() {
        return feedUid;
    }

    public void setFeedUid(String feedUid) {
        this.feedUid = feedUid;
    }

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
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

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Timestamp getWritedDate() {
        return writedDate;
    }

    public void setWritedDate(Timestamp writedDate) {
        this.writedDate = writedDate;
    }

    public Timestamp getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Timestamp deleteAt) {
        this.deleteAt = deleteAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

//    public void setProfileImage(String profileImage) {
//        this.profileImage = profileImage;
//    }


    public void setMenuImage(int menuImage) {
        this.menuImage = menuImage;
    }

    public FeedData(String feedUid, String userUid, String title, String content, int likeCount, Timestamp writedDate, Timestamp deleteAt, Timestamp updateAt) {
        this.feedUid = feedUid;
        this.userUid = userUid;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.writedDate = writedDate;
        this.deleteAt = deleteAt;
        this.updateAt = updateAt;
//        this.menuImage = menuImage;
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
