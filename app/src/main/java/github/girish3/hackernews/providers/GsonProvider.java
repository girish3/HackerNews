package github.girish3.hackernews.providers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import github.girish3.hackernews.data.Comment;
import github.girish3.hackernews.data.Topic;
import github.girish3.hackernews.deserializers.CommentDeserializer;
import github.girish3.hackernews.deserializers.TopicDeserializer;

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
            gsonBuilder.registerTypeAdapter(Comment.class, new CommentDeserializer());
            gson = gsonBuilder.create();
        }

        return gson;
    }
}
