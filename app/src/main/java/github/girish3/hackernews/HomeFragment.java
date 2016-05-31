package github.girish3.hackernews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.girish3.hackernews.adapters.TopicAdapter;
import github.girish3.hackernews.data.Topic;
import github.girish3.hackernews.presenters.TopicViewPresenter;
import github.girish3.hackernews.presenters.ViewPresenter;
import github.girish3.hackernews.views.TopicView;

public class HomeFragment extends Fragment implements TopicView, TopicAdapter.OnItemClickListener {

    RecyclerView.LayoutManager mLayoutManager;
    TopicAdapter mAdapter;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout mRefreshLayout;

    private ViewPresenter mPresenter;
    private HomeActivity mParentActivity;
    private List<Topic> mTopicList;

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

        mParentActivity = (HomeActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        if (mPresenter == null || mTopicList == null) {
            mPresenter = new TopicViewPresenter(this);
            mPresenter.onStart();
        } else {
            mAdapter = new TopicAdapter(getContext(), mTopicList, this);
            mRecyclerView.setAdapter(mAdapter);
        }

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onStart();
            }
        });

        return view;
    }

    @Override
    public void showTopics(List<Topic> topicList) {
        mTopicList = topicList;
        mAdapter = new TopicAdapter(getContext(), topicList, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
        mRefreshLayout.setRefreshing(true);
        mParentActivity.showProgressBar();
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.setRefreshing(false);
        mParentActivity.hideProgressBar();
    }

    @Override
    public void showError() {
        mParentActivity.showToast(mParentActivity.getString(R.string.fetch_error));
    }

    @Override
    public void hideError() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onStop();
    }

    @Override
    public void onItemClick(Topic topic) {
        List<String> ids = topic.getCommentIds();
        if (ids == null) {
            mParentActivity.showToast(getString(R.string.no_comments));
            return;
        }
        Fragment fragment = CommentFragment.newInstance(new ArrayList<>(ids));
        mParentActivity.addFragment(fragment);
    }
}
