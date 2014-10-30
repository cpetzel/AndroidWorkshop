package com.example.redditexample.fragment.test;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.redditexample.R;

/**
 * Fragment that shows how to pass data from one fragment to another
 * 
 * @author craig
 * 
 */
public class TestFragment extends Fragment {

    ViewGroup root;

    LinearLayout buttonContainer;

    private Button loadList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = (ViewGroup) inflater.inflate(R.layout.fragment_test, container, false);
        loadList = (Button) root.findViewById(R.id.fragment_test_load_button);

        loadList.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.main_container, TestListFragment.newInstance())
                    .addToBackStack("test").commit();
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        getActivity().getActionBar().setTitle("Reddit List Example");
    }
}
