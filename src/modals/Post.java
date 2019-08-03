package modals;

import java.util.ArrayList;

public class Post {
    private Long id;
    private User postedBy;
    private String title;
    private String text;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Post(Long id, User postedBy, String title, String text, String date) {
        this.id = id;
        this.postedBy = postedBy;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
