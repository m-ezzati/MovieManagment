package ir.maktab.service;

import ir.maktab.model.Rate;
import ir.maktab.model.User;
import ir.maktab.service.base.BaseService;

/**
 * Service interface for {@link Rate} entities
 * Providing additional services such as getUserRateMovie
 */
public interface RateService extends BaseService<Rate, Long> {
    int getUserRateMovie(Long userId, Long movieId);
}
