package ir.maktab.service;

import ir.maktab.model.Movie;
import ir.maktab.service.base.BaseService;

import java.util.List;

/**
 * Service interface for {@link Movie} entities.
 * Providing additional functionality such as getAverageRates and searchMovieByGenreAndTitle
 */
public interface MovieService extends BaseService<Movie, Long>  {
    Double getAverageRating(Long movieId);
    List<Movie> searchByGenreAndTitle(String genre, String title);

}
