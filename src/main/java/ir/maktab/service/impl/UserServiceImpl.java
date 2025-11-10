package ir.maktab.service.impl;

import ir.maktab.exception.AuthenticationException;
import ir.maktab.exception.DuplicateUsernameException;
import ir.maktab.exception.EntityNotFoundException;
import ir.maktab.model.Movie;
import ir.maktab.model.User;
import ir.maktab.repository.UserRepository;
import ir.maktab.repository.impl.UserRepositoryImpl;
import ir.maktab.service.UserService;
import ir.maktab.service.base.impl.BaseServiceImpl;
import ir.maktab.util.PasswordEncoder;

import java.util.Optional;

/**
 * Implementation of {@link UserService} for managing user.
 * Implements additional functionality  such as login, updateProfile, addMovieToWatchlist,
 * removeMovieFromWatchlist, registerUser, findUserByUsername
 */
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {
    public UserServiceImpl() {
        super(new UserRepositoryImpl(User.class));
    }

    private final UserRepository repository = new UserRepositoryImpl(User.class);
    private final MovieServiceImpl movieService = new MovieServiceImpl();

    /**
     * Register a new user with the given username, email and password.
     * <p>
     * This method validates that parameters are not null
     * checks the username is not duplicate and encodes password,
     * create a new user instance and persist it using the repository.
     *
     * @param username the desired username for new user (must not be null)
     * @param email    the user's email (must not be null)
     * @param password the user's password (must not be null)
     * @return a new created {@link User} entity.
     */
    @Override
    public User registerUser(String username, String email, String password) {
        if (username == null || password == null || email == null) {
            throw new AuthenticationException("Invalid username or password");
        }
        if (repository.findByUsername(username) != null) {
            throw new DuplicateUsernameException(username);
        }
        String hashPassword = PasswordEncoder.encode(password);

        User user = new User(username, email, hashPassword);

        return repository.add(user);
    }

    /**
     * This method checks the parameters are not null.
     * Then retrieves the {@link User} by username and verifies the given password
     * by the stored hash password. If the credentials are valid the matching user
     * is returned, otherwise null is returned.
     *
     * @param username the user's username (must not be null)
     * @param password the user's password (must not be null)
     * @return the {@link User} if authentication success, otherwise null
     */
    @Override
    public User login(String username, String password) {

        if (username == null || password == null) {
            throw new AuthenticationException("Username and Password can not be empty!");
        }
        User foundUser = findUserByUsername(username);

        if (!verifyPassword(password, foundUser.getPassword())) {
            throw new AuthenticationException("Invalid credentials.");
        }

        return foundUser;
    }

    /**
     * This method edit user information, first find the {@link User} by given username,
     * then set new email, new img if they are not null.
     * then if the password is not null, verify the old password and if it verified
     * encode new password and set and update the {@link User}
     *
     * @param username     user's username (must not be null)
     * @param email        user's email
     * @param profileImage the new profile image (if want to change)
     * @param oldPassword  old user's password (if want to change)
     * @param newPassword  the new user's password
     * @return the updated {@link User} entities.
     */
    @Override
    public Optional<User> updateProfile(String username, String email, String profileImage, String oldPassword, String newPassword) {
        if (username == null || email == null) {
            throw new AuthenticationException("Invalid Username or Email!");
        }
        User foundUser = findUserByUsername(username);

        foundUser.setEmail(email);

        if (profileImage != null && !profileImage.isEmpty()) {
            foundUser.setImg(profileImage);
        }

        if (newPassword != null && !newPassword.isEmpty()) {
            if (verifyPassword(oldPassword, foundUser.getPassword())) {
                foundUser.setPassword(PasswordEncoder.encode(newPassword));
            } else {
                throw new AuthenticationException("Password is not correct!");
            }
        }
        return repository.update(foundUser.getId(), foundUser);

    }


    /**
     * This method adds a desired movie to specific user's watchlist
     *
     * @param userId  id of specific user
     * @param movieId id of specific movie
     */
    @Override
    public void addMovieToWatchlist(Long userId, Long movieId) {

        User user = findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));

        Movie movie = movieService.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException(movieId));

        user.addToWatchList(movie);

        update(user.getId(), user)
                .orElseThrow(() -> new EntityNotFoundException(user.getId()));
    }

    /**
     * This method removes a specific movie from a specific user's watchlist
     *
     * @param userId  id of specific user
     * @param movieId id of specific movie
     * @return the {@link User} after update.
     */
    @Override
    public User removeMovieFromWatchlist(Long userId, Long movieId) {
        User user = findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(userId));

        Movie movie = movieService.findById(movieId)
                .orElseThrow(() -> new EntityNotFoundException(movieId));

        if (user != null && movie != null) {
            return repository.deleteMovieFromWatchlist(userId, movieId);
        }
        return null;
    }


    /**
     * Find {@link User} by given username
     * @param username the user's username
     * @return the found user
     */
    @Override
    public User findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    /**
     * Verify given password by the hashed password in the database
     * @param password the given password.
     * @param dbPassword the hashed password in the database.
     * @return true if verified, otherwise false
     */
    private boolean verifyPassword(String password, String dbPassword) {
        return PasswordEncoder.verify(password, dbPassword);
    }
}
