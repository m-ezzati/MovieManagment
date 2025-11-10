package ir.maktab.repository;

import ir.maktab.model.Rate;
import ir.maktab.repository.base.BaseRepository;

/**
 * Represent interface for {@link Rate} entities.
 * Providing additional operation such as findUserRatingForMovie
 */
public interface RateRepository extends BaseRepository<Rate, Long> {
    int findUserRatingForMovie(Long userId, Long movieId);
}
