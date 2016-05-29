package github.girish3.hackernews.retrofit_services;

import java.util.List;

import github.girish3.hackernews.data.Topic;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Girish on 29/05/16.
 */
public interface TopicService {

    @GET("v0/item/{id}.json")
    Call<Topic> getTopic(@Path("id") String id);
}
