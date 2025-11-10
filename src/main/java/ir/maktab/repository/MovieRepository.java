package ir.maktab.repository;

import ir.maktab.model.Movie;
import ir.maktab.repository.base.BaseRepository;

import java.util.List;

/**
 * Repository interface for {@link Movie} entities,
 * providing additional operations such as search movie by Genre and Title
 */
public interface MovieRepository extends BaseRepository<Movie, Long> {
    List<Movie> findMoviesByGenreAndTitle(String genre, String title);
}
