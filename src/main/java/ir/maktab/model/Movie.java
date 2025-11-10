package ir.maktab.model;

import ir.maktab.model.enums.Genre;
import ir.maktab.model.base.BaseEntity;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Movie extends BaseEntity <Long>{
    private String title;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private Double duration;
    private String description;

    @Column(columnDefinition = "TEXT")
    private String pic;

    @OneToMany(mappedBy = "movie")
    private List<Comment> comments;

    @OneToMany(mappedBy = "movie")
    private List<Rate> rates;

    public Movie(){}
    public Movie(String title, Genre genre, Double duration, String pic, String description) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.pic = pic;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Double getDuration() {
        return duration;
    }

    public void setDuration(Double duration) {
        this.duration = duration;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Rate> getRates() {
        return rates;
    }

    public void setRates(List<Rate> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre=" + genre +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", pic='" + pic + '\'' +
                ", comments=" + comments +
                ", rates=" + rates +
                '}';
    }
}
