package ir.maktab.repository;

import ir.maktab.model.User;
import ir.maktab.repository.base.BaseRepository;

/**
 * Repository interface for {@link User} entities,
 * providing additional operations such as finding a user by username
 * and managing the user's watchlist.
 */
public interface UserRepository extends BaseRepository<User, Long> {
    User findByUsername(String username);
    User deleteMovieFromWatchlist(Long userId, Long movieId);
}
