package com.example.pocky.domain.model.feed;

import java.util.List;


public class FeedDTO {
    private List<FeedData> feedData;

    public List<FeedData> getFeedData() {
        return feedData;
    }

    public void setFeedData(List<FeedData> feedData) {
        this.feedData = feedData;
    }
}
