package pl.kozubek.reviewgame.entity;

public class Game {
    private Long id;
    private String imageURL;
    private String title;
    private String description;
    private String author;
    private double mark;

    public Game() {
    }

    public Game(Long id, String imageURL, String title, String description, String author, double mark) {
        this.id = id;
        this.imageURL = imageURL;
        this.title = title;
        this.description = description;
        this.author = author;
        this.mark = mark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", imageURL='" + imageURL + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", mark=" + mark +
                '}';
    }
}
