package github.girish3.hackernews.retrofit_services;

import java.util.List;

import github.girish3.hackernews.Constants;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Girish on 29/05/16.
 */
public interface TopicListService {

    @GET("v0/topstories.json")
    Call<List<String>> listTopicIds();
}
