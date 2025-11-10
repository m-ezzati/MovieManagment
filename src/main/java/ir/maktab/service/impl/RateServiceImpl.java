package ir.maktab.service.impl;

import ir.maktab.model.Rate;
import ir.maktab.repository.RateRepository;
import ir.maktab.repository.impl.RateRepositoryImpl;
import ir.maktab.service.RateService;
import ir.maktab.service.base.impl.BaseServiceImpl;

/**
 * Implementation of {@link RateService} for managing rate.
 * Implements additional functionality such as getUserRateMovie
 */
public class RateServiceImpl extends BaseServiceImpl<Rate, Long> implements RateService {
    private final RateRepository rateRepository;

    public RateServiceImpl() {
        super(new RateRepositoryImpl(Rate.class));
        this.rateRepository = new RateRepositoryImpl(Rate.class);
    }

    /**
     * Find and return a specific user rating to a specific movie.
     * @param userId user's id (must not be null)
     * @param movieId movie's id (must not be null)
     * @return value of user rating for movie
     */
    @Override
    public int getUserRateMovie(Long userId, Long movieId) {
        if (userId == null || movieId == null) {
            throw new IllegalArgumentException("UserId and MovieId must not be null");
        }
        if (userId <= 0 || movieId <= 0) {
            throw new IllegalArgumentException("UserId and MovieId must be positive");
        }
        return rateRepository.findUserRatingForMovie(userId, movieId);
    }
}
