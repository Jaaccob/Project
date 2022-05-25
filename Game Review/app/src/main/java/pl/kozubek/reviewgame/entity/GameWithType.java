package pl.kozubek.reviewgame.entity;

public class GameWithType {
    private Long id;
    private String imageURL;
    private String title;
    private String description;
    private String author;
    private String type;

    public GameWithType() {
    }

    public GameWithType(Long id, String imageURL, String title, String description, String author, String type) {
        this.id = id;
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;
        this.author = author;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GameWithType{" +
                "id=" + id +
                ", imageURL='" + imageURL + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
