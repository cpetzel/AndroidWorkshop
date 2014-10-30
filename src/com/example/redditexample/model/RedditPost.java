package com.example.redditexample.model;

/**
 * This is the POJO that is used to represent a reddit post. 
 * 
 * There is a lot more data in the JSON...
 * @author craig
 *
 */
public class RedditPost {

    private String title, thumbnailUrl, url;

    public RedditPost(String title, String thumbnailUrl, String url) {
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
