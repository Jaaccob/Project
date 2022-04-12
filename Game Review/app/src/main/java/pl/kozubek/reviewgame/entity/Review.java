package pl.kozubek.reviewgame.entity;

import java.util.Date;

public class Review {
    private Long id;
    private Long idUser;
    private String nick;
    private double mark;
    private String date;
    private String description;
    private String reviewDescription;
    private double reviewMark;

    public Review() {
    }

    public Review(Long id, Long idUser, String nick, double mark, String date, String description, String reviewDescription, double reviewMark) {
        this.id = id;
        this.idUser = idUser;
        this.nick = nick;
        this.mark = mark;
        this.date = date;
        this.description = description;
        this.reviewDescription = reviewDescription;
        this.reviewMark = reviewMark;
    }

    public double getReviewMark() {
        return reviewMark;
    }

    public void setReviewMark(double reviewMark) {
        this.reviewMark = reviewMark;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", nick='" + nick + '\'' +
                ", mark=" + mark +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", reviewDescription='" + reviewDescription + '\'' +
                ", reviewMark=" + reviewMark +
                '}';
    }
}
