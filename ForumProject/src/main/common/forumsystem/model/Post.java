package common.forumsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private String title;
    private String content;
    private User author;
    private List<String> replies;

    public Post (String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.replies = new ArrayList<>();
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public List<String> getReplies() {
        return replies;
    }

    public void addReplies(String reply) {
        replies.add(reply);
    }
}
