package github.girish3.hackernews;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import github.girish3.hackernews.adapters.CommentAdapter;
import github.girish3.hackernews.adapters.TopicAdapter;
import github.girish3.hackernews.data.Comment;
import github.girish3.hackernews.presenters.CommentViewPresenter;
import github.girish3.hackernews.views.CommentView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment implements CommentView {


    private static final String COMMENT_IDS = "COMMENT_IDS";
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    private ArrayList<String> mIds;
    private CommentViewPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private CommentAdapter mAdapter;
    private HomeActivity mParentActivity;

    public CommentFragment() {
        // Required empty public constructor
    }

    public static CommentFragment newInstance(ArrayList<String> commentIds) {
        CommentFragment fragment = new CommentFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(COMMENT_IDS, commentIds);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mParentActivity = (HomeActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle != null) {
            mIds = bundle.getStringArrayList(COMMENT_IDS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        ButterKnife.bind(this, view);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mPresenter = new CommentViewPresenter(this, mIds);
        mPresenter.onStart();

        return view;
    }

    @Override
    public void showComments(List<Comment> commentList) {
        mAdapter = new CommentAdapter(getContext(), commentList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void showLoading() {
        mParentActivity.showProgressBar();
    }

    @Override
    public void hideLoading() {
        mParentActivity.hideProgressBar();
    }

    @Override
    public void showError() {
        hideLoading();
        mParentActivity.showToast(mParentActivity.getString(R.string.fetch_error));
    }

    @Override
    public void hideError() {

    }
}
