package github.girish3.hackernews.presenters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import github.girish3.hackernews.Constants;
import github.girish3.hackernews.data.Topic;
import github.girish3.hackernews.provider.RetrofitProvider;
import github.girish3.hackernews.retrofit_services.TopicListService;
import github.girish3.hackernews.retrofit_services.TopicService;
import github.girish3.hackernews.views.TopicView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Girish on 28/05/16.
 */
public class TopicViewPresenter implements Callback<Topic>, ViewPresenter {

    private final Retrofit mRetrofit;
    private TopicView mView;
    private List<String> mIds;
    private Topic[] mTopics = new Topic[Constants.TOPIC_LIST_SIZE];
    private int sizeSoFar = 0;

    public TopicViewPresenter(TopicView view) {
        mView = view;
        mRetrofit = RetrofitProvider.getInstance();
    }

    @Override
    public void onStart() {
        TopicListService service = mRetrofit.create(TopicListService.class);
        Call<List<String>> idsCall = service.listTopicIds();
        idsCall.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                mIds = response.body();

                // getting each of the topics individually from ids
                TopicService service = mRetrofit.create(TopicService.class);

                for (int i = 0; i < Constants.TOPIC_LIST_SIZE; i++) {
                    Call<Topic> topicCall = service.getTopic(mIds.get(i));
                    topicCall.enqueue(TopicViewPresenter.this);
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                int b = 6;
            }
        });
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResponse(Call<Topic> call, Response<Topic> response) {
        Topic topic = response.body();
        int index = mIds.indexOf(topic.getId());
        mTopics[index] = topic;
        sizeSoFar++;

        if (sizeSoFar >= Constants.TOPIC_LIST_SIZE) {
            mView.showTopics(Arrays.asList(mTopics));
        }
    }

    @Override
    public void onFailure(Call<Topic> call, Throwable t) {
        mView.showError("there was an error");
    }
}
