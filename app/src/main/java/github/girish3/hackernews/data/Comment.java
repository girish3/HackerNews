package github.girish3.hackernews.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Girish on 29/05/16.
 */
public class Comment {

    public static final int COMMENT_TYPE = 0;
    public static final int SUB_COMMENT_TYPE = 1;
    private String id;
    private int type;
    private String parentId;
    private String author;
    private String comment;
    private long time;
    private List<String> subCommentIds;
    private List<Comment> subComments;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public List<String> getSubCommentIds() {
        return subCommentIds;
    }

    public void setSubCommentIds(List<String> subCommentIds) {
        this.subCommentIds = subCommentIds;
        this.subComments = new ArrayList<>();
    }

    public List<Comment> getSubComments() {
        return subComments;
    }

    public void setSubComments(List<Comment> subComments) {
        this.subComments = subComments;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
