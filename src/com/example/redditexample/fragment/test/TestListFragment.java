package com.example.redditexample.fragment.test;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.redditexample.R;
import com.example.redditexample.adapter.RedditPostAdapter;
import com.example.redditexample.manager.DataLoaderManager;
import com.example.redditexample.model.RedditPost;

public class TestListFragment extends Fragment {

    private ListView mList;
    private RedditPostAdapter adapter;

    private String dataArgument;

    private TestListFragment() {
    }

    public static TestListFragment newInstance() {

        TestListFragment testListFrag = new TestListFragment();
        Bundle b = new Bundle();
        b.putString("test", "hi");
        testListFrag.setArguments(b);
        return testListFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        dataArgument = getArguments().getString("test");

        getActivity().getActionBar().setTitle(dataArgument);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mList = (ListView) inflater.inflate(R.layout.fragment_test_list, container, false);

        mList.setAdapter(adapter);

        mList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                RedditPost post = (RedditPost) parent.getItemAtPosition(position);
                Toast.makeText(parent.getContext(), post.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        loadData();

        return mList;
    }

    private void loadData() {
        List<RedditPost> mockData = DataLoaderManager.getMockData();

        adapter = new RedditPostAdapter(getActivity(), mockData);
        mList.setAdapter(adapter);
    }

}
