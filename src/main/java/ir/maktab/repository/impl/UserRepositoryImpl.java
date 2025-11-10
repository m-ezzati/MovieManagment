package ir.maktab.repository.impl;

import ir.maktab.model.Movie;
import ir.maktab.model.User;
import ir.maktab.repository.UserRepository;
import ir.maktab.repository.base.impl.BaseRepositoryImpl;
import ir.maktab.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Implementation of {@link UserRepository} for managing {@link User} entities
 * using JPA. Provides operations for finding users by username and
 * modifying their watchlist.
 */
public class UserRepositoryImpl extends BaseRepositoryImpl<User, Long> implements UserRepository {
    public UserRepositoryImpl(Class<User> entityClass) {
        super(entityClass);
    }

    @Override
    public User findByUsername(String username) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);

            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);

        } finally {
            em.close();
        }
    }

    @Override
    public User deleteMovieFromWatchlist(Long userId, Long movieId) {

        EntityManager em = JpaUtil.getEntityManager();

        try {
            JpaUtil.beginTransaction(em);

            User user = em.find(User.class, userId);
            Movie movie = em.find(Movie.class, movieId);

            if (user == null) {
                throw new IllegalArgumentException("User not found with id: " + userId);
            }
            if (movie == null) {
                throw new IllegalArgumentException("Movie not found with id: " + movieId);
            }

            user.getWatchList().remove(movie);
            em.merge(user);

            JpaUtil.commitTransaction(em);

            return user;

        } catch (RuntimeException e) {
            JpaUtil.rollbackTransaction(em);
            throw  e;
        } finally {
            em.close();
        }
    }
}
