package com.example.redditexample.manager;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.example.redditexample.model.RedditEndpoint;
import com.example.redditexample.model.RedditPost;

/**
 * This class handles most of the data loading and parsing, of stuff that we are dealing with.
 * 
 * @author craig
 * 
 */
public class DataLoaderManager {

    private static ArrayList<RedditEndpoint> mEndpoints = new ArrayList<RedditEndpoint>();

    private DataLoaderManager() {
    }

    static {
        initializeEndpoints();
    }

    /**
     * This is used for dynamically building the buttons used in the MainFragment
     */
    private static void initializeEndpoints() {

        RedditEndpoint frontPageEndpoint = new RedditEndpoint("http://www.reddit.com/.json", "Front Page");
        RedditEndpoint AndroidEndpoint = new RedditEndpoint("http://www.reddit.com/r/Android/.json", "Android");
        RedditEndpoint picsEndpoint = new RedditEndpoint("http://www.reddit.com/r/pics/.json", "Pics");

        mEndpoints.add(frontPageEndpoint);
        mEndpoints.add(AndroidEndpoint);
        mEndpoints.add(picsEndpoint);
    }

    public static List<RedditEndpoint> getEndpoints() {
        return mEndpoints;
    }

    public static List<RedditPost> parseRedditPosts(JSONObject rawJsonFromReddit) {
        List<RedditPost> results = new ArrayList<RedditPost>();

        JSONObject data;
        JSONArray children;
        try {
            data = rawJsonFromReddit.getJSONObject("data");

            if (data == null) return results;

            children = data.getJSONArray("children");
            if (children == null) return results;

            for (int i = 0; i < children.length(); i++) {

                JSONObject realData = children.getJSONObject(i).getJSONObject("data");

                String title = realData.getString("title");
                String thumbnailUrl = realData.getString("thumbnail");
                String postUrl = realData.getString("url");
                results.add(new RedditPost(title, thumbnailUrl, postUrl));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.d("DataLoaderManager", "loaded " + results.size() + " posts");
        return results;

    }

    public static String getNameOfSubreddit(JSONObject rawJsonFromReddit) {

        JSONObject data;
        JSONArray children;
        String subredditName = "";
        try {
            data = rawJsonFromReddit.getJSONObject("data");

            if (data == null) return subredditName;

            children = data.getJSONArray("children");
            if (children == null) return subredditName;

            JSONObject realData = children.getJSONObject(0).getJSONObject("data");

            subredditName = realData.getString("subreddit");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return subredditName;

    }

    public static List<RedditPost> getMockData() {
        List<RedditPost> mockDataList = new ArrayList<RedditPost>();

        for (int i = 0; i < 30; i++) {
            mockDataList.add(new RedditPost("Mock Title " + i, null, null));
        }

        return mockDataList;
    }
}
