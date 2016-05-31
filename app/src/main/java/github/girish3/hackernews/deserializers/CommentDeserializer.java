package github.girish3.hackernews.deserializers;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Arrays;

import github.girish3.hackernews.data.Comment;

/**
 * Created by Girish on 29/05/16.
 */
public class CommentDeserializer implements JsonDeserializer<Comment> {

    @Override
    public Comment deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject object = json.getAsJsonObject();


        String id = object.get("id").getAsString();
        String parent = object.get("parent").getAsString();
        long time = object.get("time").getAsLong();
        String author = null, c;
        String[] commentIds = null;
        if (!object.has("deleted")) {
            author = object.get("by").getAsString();
            c = object.get("text").getAsString();
            if (object.has("kids")) {
                JsonArray jsonIdArray = object.get("kids").getAsJsonArray();
                commentIds = new String[jsonIdArray.size()];
                for (int i = 0; i < jsonIdArray.size(); i++) {
                    JsonElement jsonId = jsonIdArray.get(i);
                    commentIds[i] = jsonId.getAsString();
                }
            }
        } else {
            c = "deleted";
        }


        Comment comment = new Comment();
        comment.setId(id);
        comment.setParentId(parent);
        comment.setAuthor(author);
        comment.setComment(c);
        comment.setTime(time);
        if (commentIds != null) comment.setSubCommentIds(Arrays.asList(commentIds));

        return comment;
    }
}
