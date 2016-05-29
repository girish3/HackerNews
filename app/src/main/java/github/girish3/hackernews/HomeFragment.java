package github.girish3.hackernews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.girish3.hackernews.adapters.TopicAdapter;
import github.girish3.hackernews.data.Topic;
import github.girish3.hackernews.presenters.TopicViewPresenter;
import github.girish3.hackernews.presenters.ViewPresenter;
import github.girish3.hackernews.views.TopicView;

public class HomeFragment extends Fragment implements TopicView {

    RecyclerView.LayoutManager mLayoutManager;
    TopicAdapter mAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ViewPresenter mPresenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter = new TopicViewPresenter(this);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void showTopics(List<Topic> topicList) {
        mAdapter = new TopicAdapter(getContext(), topicList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void hideError() {

    }
}
