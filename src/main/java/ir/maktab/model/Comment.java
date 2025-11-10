package ir.maktab.model;

import ir.maktab.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
/**
 * Represent a comment made by a user on a movie
 * Contains the text of comment, the user who created it, the related movie
 */
@Entity
public class Comment extends BaseEntity<Long> {

    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Movie movie;

    public Comment(){}

    public Comment(String comment, User user, Movie movie){
        this.comment = comment;
        this.user = user;
        this.movie = movie;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
