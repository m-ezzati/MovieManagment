package ir.maktab.service.impl;

import ir.maktab.exception.EntityNotFoundException;
import ir.maktab.model.Movie;
import ir.maktab.model.Rate;
import ir.maktab.repository.MovieRepository;
import ir.maktab.repository.impl.MovieRepositoryImpl;
import ir.maktab.service.MovieService;
import ir.maktab.service.base.impl.BaseServiceImpl;

import java.util.List;

/**
 * Implementation of {@link MovieService} for managing movie.
 * Implements additional functionality  such as getAverageRating  and searchByGenreAndTitle
 */
public class MovieServiceImpl extends BaseServiceImpl<Movie, Long> implements MovieService {

    final MovieRepository movieRepository = new MovieRepositoryImpl(Movie.class);

    public MovieServiceImpl() {
        super(new MovieRepositoryImpl(Movie.class));
    }

    /**
     * Finds a {@link Movie} by the given movie ID and calculates the average rating.
     *
     * @param movieId movie's id
     * @return average of movie rating, if there is no rating return 0
     */
    @Override
    public Double getAverageRating(Long movieId) {
        Movie movie = findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException(movieId));

        return averageRating(movie);
    }

    /**
     * Searches for {@link Movie} entities based on the provided genre and title.
     *
     * @param genre the movie genre to search for (can be {@code null} or empty)
     * @param title the movie title or part of it to search for (can be {@code null} or empty)
     * @return a list of {@link Movie} objects that match the given criteria
     */
    @Override
    public List<Movie> searchByGenreAndTitle(String genre, String title) {
        if ((genre == null || genre.trim().isEmpty()) && (title == null || title.trim().isEmpty())) {
            throw new IllegalArgumentException("One of title or genre must have value!");
        }
        return movieRepository.findMoviesByGenreAndTitle(genre, title);
    }

    /**
     * Calculates the average rating for the given {@link Movie}, rounded to two decimal places.
     *
     * @param movie the movie whose ratings should be averaged
     * @return the average rating, or {@code 0.0} if the movie has no ratings
     */
    private Double averageRating(Movie movie) {
        double avg = movie.getRates()
                .stream()
                .mapToInt(Rate::getValue)
                .average()
                .orElse(0.0);

        return Math.round(avg * 100.0) / 100.0;
    }
}
