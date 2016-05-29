package github.girish3.hackernews.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import github.girish3.hackernews.Constants;
import github.girish3.hackernews.data.Topic;
import github.girish3.hackernews.deserializer.TopicDeserializer;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Girish on 28/05/16.
 */
public class GsonProvider {

    private static Gson gson = null;

    private GsonProvider() {
    }

    public static Gson getInstance() {

        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(Topic.class, new TopicDeserializer());
            gson = gsonBuilder.create();
        }

        return gson;
    }
}
