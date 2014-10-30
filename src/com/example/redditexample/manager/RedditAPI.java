package com.example.redditexample.manager;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class RedditAPI {

    private static RequestQueue mRequestQueue;
    private static RedditAPI mInstance;

    private RedditAPI() {
    }

    public static RedditAPI getInstance(Context c) {
        if (mInstance == null) {
            mInstance = new RedditAPI();
            mRequestQueue = Volley.newRequestQueue(c);
        }
        return mInstance;
    }

    public void addRequest(Request r) {
        mRequestQueue.add(r);
    }

    public void cancelRequest(String tag) {
        mRequestQueue.cancelAll(tag);
    }

}
