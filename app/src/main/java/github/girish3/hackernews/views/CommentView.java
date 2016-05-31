package github.girish3.hackernews.views;

import java.util.List;

import github.girish3.hackernews.data.Comment;

/**
 * Created by Girish on 30/05/16.
 */
public interface CommentView {

    void showComments(List<Comment> commentList);

    void showLoading();

    void hideLoading();

    void showError();

    void hideError();
}
