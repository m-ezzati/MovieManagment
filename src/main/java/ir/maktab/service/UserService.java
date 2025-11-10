package ir.maktab.service;

import ir.maktab.model.User;
import ir.maktab.service.base.BaseService;

import java.util.Optional;

/**
 * Service interface for {@link User} entities
 * Providing additional services such as login, updateProfile, addMovieToWatchlist,
 * removeMovieFromWatchlist, registerUser, findUserByUsername
 */
public interface UserService extends BaseService<User, Long> {
    User login(String username, String password);
    Optional<User> updateProfile(String username, String email, String profileImage, String oldPassword, String newPassword);
    void addMovieToWatchlist(Long userId, Long movieId);
    User removeMovieFromWatchlist(Long userId, Long movieId);
    User registerUser(String username, String email, String password);
    User findUserByUsername(String username);
}
