package github.girish3.hackernews.deserializers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Arrays;

import github.girish3.hackernews.data.Topic;

/**
 * Created by Girish on 28/05/16.
 */
public class TopicDeserializer implements JsonDeserializer<Topic> {

    @Override
    public Topic deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject object = json.getAsJsonObject();

        String id = object.get("id").getAsString();
        String author = object.get("by").getAsString();
        String headline = object.get("title").getAsString();
        long time = object.get("time").getAsLong();
        int points = object.get("score").getAsInt();
        int commentCount = 0;
        if (object.has("descendants")) commentCount = object.get("descendants").getAsInt();
        JsonArray jsonIdArray;
        String[] commentIds = null;

        if (commentCount > 0) {
            jsonIdArray = object.get("kids").getAsJsonArray();
            commentIds = new String[jsonIdArray.size()];
            for (int i = 0; i < jsonIdArray.size(); i++) {
                JsonElement jsonId = jsonIdArray.get(i);
                commentIds[i] = jsonId.getAsString();
            }
        }

        Topic topic = new Topic();
        topic.setId(id);
        topic.setAuthor(author);
        topic.setHeadline(headline);
        topic.setTime(time);
        topic.setPoints(points);
        topic.setCommentCount(commentCount);
        if (commentIds != null) topic.setCommentIds(Arrays.asList(commentIds));

        return topic;
    }
}
