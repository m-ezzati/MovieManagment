package ir.maktab.servlet.movie;

import ir.maktab.model.Movie;
import ir.maktab.model.User;
import ir.maktab.service.MovieService;
import ir.maktab.service.UserService;
import ir.maktab.service.impl.MovieServiceImpl;
import ir.maktab.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/deleteMovie")
public class DeleteMovieServlet extends HttpServlet {
    private final MovieService movieService = new MovieServiceImpl();
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Long movieId = Long.parseLong(req.getParameter("movieId"));

        Optional<Movie> movie = movieService.findById(movieId);

        if (movie.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
            return;
        }

        List<User> users = userService.findAll();

        for (User user : users) {
            user.getWatchList().remove(movie.get());
            userService.removeMovieFromWatchlist(user.getId(), movieId);
        }

        movieService.deleteById(movieId);

        resp.sendRedirect("allMovies");

    }
}
