package com.example.redditexample.network.request;

import org.json.JSONObject;

import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;

public class GetSubredditRequest extends JsonObjectRequest {

    public GetSubredditRequest(String url, JSONObject jsonRequest, Listener<JSONObject> listener,
        ErrorListener errorListener) {
        super(Request.Method.GET, url, jsonRequest, listener, errorListener);
    }

}
