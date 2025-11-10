package ir.maktab.model;

import ir.maktab.exception.IllegalRatingException;
import ir.maktab.model.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

/**
 * Represent a rate made by a user on a movie
 * Contains the value of rate, the user who rated and the related movie
 */
@Entity
public class Rate extends BaseEntity<Long> {

    /**
     * The rating value given by user.
     * Must be between 1 and 5
     */
    private int value;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Movie movie;

    public Rate() {
    }

    public Rate(int value, User user, Movie movie) {
        this.value = value;
        this.user = user;
        this.movie = movie;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 1 || value > 5) {
            throw new IllegalRatingException();
        }
        this.value = value;
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
