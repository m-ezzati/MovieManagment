package ir.maktab.repository.impl;

import ir.maktab.model.Rate;
import ir.maktab.repository.RateRepository;
import ir.maktab.repository.base.impl.BaseRepositoryImpl;
import ir.maktab.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * Implementation of {@link RateRepository}
 * Using JPA, provides operations such as findUserRatingForMovie that retrieving a user's rate
 * for specific movie
 */
public class RateRepositoryImpl extends BaseRepositoryImpl<Rate, Long> implements RateRepository {
    public RateRepositoryImpl(Class<Rate> entityClass) {
        super(entityClass);
    }

    /**
     * Retrieving the rating a specific user given to a specific movie
     * @param userId the unique id of user
     * @param movieId the unique id of movie
     * @return an Integer containing the user rate value(1-5), or 0 if not rated
     */
    @Override
    public int findUserRatingForMovie(Long userId, Long movieId) {
        final EntityManager em = JpaUtil.getEntityManager();

        try {
            final TypedQuery<Integer> query = em.createQuery(
                    "SELECT r.value FROM Rate r WHERE r.user.id = :userId AND r.movie.id = :movieId",
                    Integer.class
            );

            query.setParameter("userId", userId);
            query.setParameter("movieId", movieId);

            return query.getResultList().
                    stream().
                    findFirst().
                    orElse(0);

        } finally {
            em.close();
        }
    }
}
