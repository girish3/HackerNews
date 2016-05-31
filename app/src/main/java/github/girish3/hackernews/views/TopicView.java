package github.girish3.hackernews.views;

import java.util.List;

import github.girish3.hackernews.data.Topic;

/**
 * Created by Girish on 28/05/16.
 */
public interface TopicView {

    void showTopics(List<Topic> topicList);

    void showLoading();

    void hideLoading();

    void showError();

    void hideError();

}
