package modals;

public class Comment {
    private Long id;
    private Post post;
    private User owner;
    private String description;

    public Comment(Long id, Post post, User owner, String description) {
        this.id = id;
        this.post = post;
        this.owner = owner;
        this.description = description;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Post getpost() {
        return post;
    }

    public void setpost(Post post_id) {
        post = post_id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
