package github.girish3.hackernews.retrofit_services;

import github.girish3.hackernews.data.Comment;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Girish on 30/05/16.
 */
public interface CommentService {

    @GET("v0/item/{id}.json")
    Call<Comment> getItem(@Path("id") String id);
}
