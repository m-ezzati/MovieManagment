package ir.maktab.repository.impl;

import ir.maktab.model.Movie;
import ir.maktab.model.enums.Genre;
import ir.maktab.repository.MovieRepository;
import ir.maktab.repository.base.impl.BaseRepositoryImpl;
import ir.maktab.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link MovieRepository} for managing {@link Movie} entities
 * using JPA Criteria API. Provides dynamic search by genre and title.
 */
public class MovieRepositoryImpl extends BaseRepositoryImpl<Movie, Long> implements MovieRepository {

    public MovieRepositoryImpl(Class<Movie> entityClass) {
        super(entityClass);
    }

    /**
     * Searches for movies based on optional genre and title filters.
     *
     * <p>If both parameters are null or empty, all movies are returned.</p>
     *
     * @param genre the genre name may be null or empty
     * @param title the movie title (case-insensitive) may be null or empty
     * @return list of matching {@link Movie} entities, never {@code null}
     */
    @Override
    public List<Movie> findMoviesByGenreAndTitle(String genre, String title) {
        final EntityManager em = JpaUtil.getEntityManager();

        try {
            final CriteriaBuilder cb = em.getCriteriaBuilder();
            final CriteriaQuery<Movie> cq = cb.createQuery(Movie.class);
            final Root<Movie> movieRoot = cq.from(Movie.class);

            List<Predicate> predicates = new ArrayList<>();

            Predicate titlePredicate = buildTitlePredicate(cb, movieRoot, title);
            if (titlePredicate != null) {
                predicates.add(titlePredicate);
            }

            Predicate genrePredicate = buildGenrePredicate(cb, movieRoot, genre);
            if (genrePredicate != null) {
                predicates.add(genrePredicate);
            }

            cq.select(movieRoot).where(predicates.toArray(new Predicate[0]));

            return em.createQuery(cq).getResultList();

        } finally {
            em.close();
        }
    }

    /**
     * Builds a Predicate for title filtering (case-insensitive, partial match).
     */
    private Predicate buildTitlePredicate(CriteriaBuilder cb, Root<Movie> root, String title) {
        if (title == null || title.trim().isEmpty())
            return null;
        return cb.like(cb.lower(root.get("title")), "%" + title.trim().toLowerCase() + "%");
    }

    /**
     * Builds a Predicate for genre filtering.
     */
    private Predicate buildGenrePredicate(CriteriaBuilder cb, Root<Movie> root, String genre) {
        if (genre == null || genre.trim().isEmpty())
            return null;

        try {
            Genre genreEnum = Genre.valueOf(genre.trim().toUpperCase());
            return cb.equal(root.get("genre"), genreEnum);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
