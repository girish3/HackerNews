package github.girish3.hackernews.providers;


import github.girish3.hackernews.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Girish on 28/05/16.
 */
public class RetrofitProvider {

    private static final Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonProvider.getInstance()))
            .baseUrl(Constants.HOST)
            .build();

    private RetrofitProvider() {
    }

    public static Retrofit getInstance() {
        return retrofit;
    }
}
