package github.girish3.hackernews.presenters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import github.girish3.hackernews.data.Comment;
import github.girish3.hackernews.providers.RetrofitProvider;
import github.girish3.hackernews.retrofit_services.CommentService;
import github.girish3.hackernews.views.CommentView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Girish on 30/05/16.
 */
public class CommentViewPresenter implements ViewPresenter {

    private final CommentView mView;
    private final Retrofit mRetrofit;
    private final ArrayList<String> mIds;
    private Comment[] comments;
    private int commentSoFar = 0;
    private int subCommentSize;
    private int subCommentSoFar;

    public CommentViewPresenter(CommentView view, ArrayList<String> ids) {
        mView = view;
        mRetrofit = RetrofitProvider.getInstance();
        mIds = ids;
        comments = new Comment[mIds.size()];
    }

    @Override
    public void onStart() {
        commentSoFar = 0;
        subCommentSize = 0;
        mView.showLoading();
        CommentService service = mRetrofit.create(CommentService.class);

        for (int i = 0; i < mIds.size(); i++) {
            Call<Comment> commentCall = service.getItem(mIds.get(i));
            commentCall.enqueue(new Callback<Comment>() {
                @Override
                public void onResponse(Call<Comment> call, Response<Comment> response) {
                    Comment comment = response.body();
                    if (comment.getSubCommentIds() != null)
                        subCommentSize += (comment.getSubCommentIds().size());
                    int index = mIds.indexOf(comment.getId());
                    comments[index] = comment;

                    commentSoFar++;

                    if (commentSoFar >= mIds.size()) {
                        getSubComments();
                    }
                }

                @Override
                public void onFailure(Call<Comment> call, Throwable t) {
                    mView.showError();
                }
            });
        }


    }

    private void getSubComments() {

        CommentService service = mRetrofit.create(CommentService.class);

        if (subCommentSize == 0) {
            formatAndDisplay();
            return;
        }

        for (int i = 0; i < comments.length; i++) {
            Comment comment = comments[i];
            List<String> ids = comment.getSubCommentIds();
            if (ids != null) {
                for (int j = 0; j < ids.size(); j++) {
                    Call<Comment> commentCall = service.getItem(ids.get(j));
                    commentCall.enqueue(new Callback<Comment>() {
                        @Override
                        public void onResponse(Call<Comment> call, Response<Comment> response) {
                            Comment subComment = response.body();
                            String parentId = subComment.getParentId();
                            int index = Arrays.asList(mIds).get(0).indexOf(parentId);
                            comments[index].getSubComments().add(subComment);
                            subCommentSoFar++;

                            if (subCommentSoFar >= subCommentSize) {
                                formatAndDisplay();
                            }
                        }

                        @Override
                        public void onFailure(Call<Comment> call, Throwable t) {
                            mView.showError();
                        }
                    });
                }
            }
        }

    }

    private void formatAndDisplay() {
        List<Comment> coms = new ArrayList<>();
        Comment comment, subComment;

        for (int i = 0; i < comments.length; i++) {
            comment = comments[i];
            comment.setType(Comment.COMMENT_TYPE);
            coms.add(comment);
            if (comment.getSubComments() != null) {
                List<Comment> subComments = comment.getSubComments();
                for (int j = 0; j < subComments.size(); j++) {
                    subComment = subComments.get(j);
                    subComment.setType(Comment.SUB_COMMENT_TYPE);
                    coms.add(subComment);
                }
            }
        }

        mView.hideLoading();
        mView.showComments(coms);
    }

    @Override
    public void onStop() {

    }
}
