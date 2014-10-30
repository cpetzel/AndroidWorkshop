package com.example.redditexample.model;
/**
 * POJO to hold onto information about Reddit's subreddits
 * @author craig
 *
 */
public class RedditEndpoint {

    private String mUrl, mName;

    public RedditEndpoint(String url, String name) {
        this.mUrl = url;
        this.mName = name;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

}
