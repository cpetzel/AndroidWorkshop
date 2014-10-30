package com.example.redditexample.fragment;

import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.redditexample.R;
import com.example.redditexample.manager.DataLoaderManager;
import com.example.redditexample.model.RedditEndpoint;

/**
 * This fragment is used to let the user pick with subreddit he would like to show
 * 
 * @author craig
 * 
 */
public class MainFragment extends Fragment {

    ViewGroup root;

    LinearLayout buttonContainer;

    Button customSubredditGo;
    EditText subredditInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);
        buttonContainer = (LinearLayout) root.findViewById(R.id.fragment_main_ll);
        customSubredditGo = (Button) root.findViewById(R.id.fragment_main_button_go);
        subredditInput = (EditText) root.findViewById(R.id.fragment_main_input);

        customSubredditGo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String subredditName = subredditInput.getText().toString();
                loadFeed(new RedditEndpoint("http://www.reddit.com/r/" + subredditName + "/.json", ""));
            }
        });

        addButtonsToLayout(inflater.getContext());

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        getActivity().getActionBar().setTitle("Reddit Example");
    }

    /**
     * This adds Buttons to the LinearLayout, as defined in the DataLoaderManager as endpoints
     * 
     * @param c
     */
    private void addButtonsToLayout(Context c) {

        List<RedditEndpoint> endpoints = DataLoaderManager.getEndpoints();

        for (final RedditEndpoint re : endpoints) {

            Button b = new Button(c);
            b.setText(re.getName());
            b.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    loadFeed(re);
                }
            });
            buttonContainer.addView(b);
        }
    }

    private void loadFeed(RedditEndpoint endpoint) {
        RedditSubredditListFragment newFrag = RedditSubredditListFragment.newInstance(endpoint);
        getFragmentManager().beginTransaction().replace(R.id.main_container, newFrag, endpoint.getName()).addToBackStack("main")
            .commit();
    }

}
