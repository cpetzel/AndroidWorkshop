package com.example.redditexample.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.redditexample.R;

/**
 * This fragment is in charge of showing the details of a Reddit post.
 * 
 * Currently it just shows the content in a WebView
 * 
 * @author craig
 * 
 */
public class RedditDetailFragment extends Fragment {

    WebView root;

    private RedditDetailFragment() {
    }

    public static RedditDetailFragment newInstance(String url) {
        // Create a new instance of the fragment, and assign the arguments.
        RedditDetailFragment frag = new RedditDetailFragment();
        Bundle b = new Bundle();
        b.putString("url", url);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = (WebView) inflater.inflate(R.layout.fragment_reddit_post_detail_web, container, false);
        root.setWebViewClient(new NoRedirectWebClient());
        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        String url = getArguments().getString("url");
        root.loadUrl(url);
    }

    /**
     * When used, this class will always load the URL in the embedded web view, instead of sometimes
     * opening the link through the phone's browser
     * 
     * @author craig
     * 
     */
    private class NoRedirectWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
