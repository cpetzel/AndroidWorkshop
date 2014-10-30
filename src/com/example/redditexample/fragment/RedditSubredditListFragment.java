package com.example.redditexample.fragment;

import java.util.List;

import org.json.JSONObject;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.redditexample.R;
import com.example.redditexample.adapter.RedditPostAdapter;
import com.example.redditexample.manager.DataLoaderManager;
import com.example.redditexample.manager.RedditAPI;
import com.example.redditexample.model.RedditEndpoint;
import com.example.redditexample.model.RedditPost;

public class RedditSubredditListFragment extends Fragment {

    private View root;
    private ListView subredditList;
    private RedditPostAdapter adapter;
    private ProgressDialog pDialog;

    String url, name;

    private RedditSubredditListFragment() {
    }

    public static RedditSubredditListFragment newInstance(RedditEndpoint re) {

        RedditSubredditListFragment subListFrag = new RedditSubredditListFragment();
        Bundle b = new Bundle();
        b.putString("url", re.getUrl());
        b.putString("name", re.getName());
        subListFrag.setArguments(b);
        return subListFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        url = getArguments().getString("url");
        name = getArguments().getString("name");

        loadSubreddit();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_subreddit, container, false);
        subredditList = (ListView) root.findViewById(R.id.fragment_subreddit_list);

        subredditList.setAdapter(adapter);

        subredditList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RedditPost post = (RedditPost) parent.getItemAtPosition(position);
                showDetailFragment(post);
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        updateUI();
    }

    private void updateUI() {
        getActivity().getActionBar().setTitle(name);
    }

    private void loadSubreddit() {

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading the " + name + " ...");
        pDialog.show();

        JsonObjectRequest newRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonResult) {
                handleJSONResponse(jsonResult);
                pDialog.dismiss();
            }

        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
                Log.e("Android", "error loading feed!");
                pDialog.dismiss();
            }
        });

        Log.e(getClass().getSimpleName(), "trying to load feed... " + name);

        RedditAPI.getInstance(getActivity()).addRequest(newRequest);

    }

    private void handleJSONResponse(JSONObject response) {
        updateList(DataLoaderManager.parseRedditPosts(response));

        // if we are the front page, then dont set the title
        if (url.equalsIgnoreCase("http://www.reddit.com/.json")) { return; }
        name = DataLoaderManager.getNameOfSubreddit(response);

        getActivity().getActionBar().setTitle(name);

    }

    private void updateList(final List<RedditPost> posts) {

        adapter = new RedditPostAdapter(getActivity(), posts);

        this.subredditList.setAdapter(adapter);

    }

    private void showDetailFragment(RedditPost rp) {

        getFragmentManager().beginTransaction()
            .replace(R.id.main_container, RedditDetailFragment.newInstance(rp.getUrl()), "detail").addToBackStack("list")
            .commit();

    }
}
