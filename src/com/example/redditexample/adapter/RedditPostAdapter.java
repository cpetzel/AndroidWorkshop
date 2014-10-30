package com.example.redditexample.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.redditexample.R;
import com.example.redditexample.model.RedditPost;
import com.squareup.picasso.Picasso;

/**
 * This adapter class is in charge of mapping the data objects to the list views. As the user
 * scrolls and more cells become avaialbe on the screen, this adapter is in charge of creating a
 * view and filling int he UI data
 * 
 * The adapter class also contains the collection of data objects, in this case, a list of
 * RedditPosts
 * 
 * @author craig
 * 
 */
public class RedditPostAdapter extends ArrayAdapter<RedditPost> {

    // This is the resource of the layout that we want to use.
    private int mResource = R.layout.row_reddit_post;;
    private final List<RedditPost> mPosts;

    public RedditPostAdapter(Context c, List<RedditPost> posts) {
        super(c, R.layout.row_reddit_post, posts);
        mPosts = posts;
    }

    /**
     * This method is in charge of actually creating (or reusing) the view that goes into the list
     * 
     * if the system already has inflated rows to fill the screen, Android will reuse those Views
     * and give them to you in this method (convertView). This means you don't have to inflate a new
     * view every time
     * 
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        // If we not inflated enough views to fill the screen yet, then create a new one
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            rowView = inflater.inflate(mResource, parent, false);
        }

        // Get the RedditPost for this position from our collection
        final RedditPost post = mPosts.get(position);
        if (post != null) {
            TextView textView = (TextView) rowView.findViewById(R.id.row_reddit_post_title);
            textView.setText(post.getTitle());

            ImageView imageView = (ImageView) rowView.findViewById(R.id.row_reddit_post_image);

            String thumbUrl = post.getThumbnailUrl();

            if (thumbUrl == null || thumbUrl.isEmpty()) { return rowView; }

            Picasso.with(parent.getContext()).load(post.getThumbnailUrl()).into(imageView);
        }

        return rowView;
    }
}
