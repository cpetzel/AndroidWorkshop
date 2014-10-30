package com.example.redditexample.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.redditexample.R;
import com.example.redditexample.fragment.test.TestFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This content view is just an empty FrameLayout container for fragments to go in.
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.main_container, new TestFragment(), "main").commit();

    }

}
