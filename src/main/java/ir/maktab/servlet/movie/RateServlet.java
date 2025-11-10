package ir.maktab.servlet.movie;

import ir.maktab.exception.EntityNotFoundException;
import ir.maktab.model.Movie;
import ir.maktab.model.Rate;
import ir.maktab.model.User;
import ir.maktab.service.MovieService;
import ir.maktab.service.RateService;
import ir.maktab.service.impl.MovieServiceImpl;
import ir.maktab.service.impl.RateServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/rateMovie")
public class RateServlet extends HttpServlet {

    private final static String MOVIE_DETAIL_PATH = "/movieDetail";
    private static final String LOGIN_PATH = "/login";

    private final MovieService movieService = new MovieServiceImpl();
    private final RateService rateService = new RateServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(LOGIN_PATH);
        }

        Long movieId = Long.valueOf(req.getParameter("movieId"));
        int rating = Integer.parseInt(req.getParameter("rating"));

        Optional<Movie> movie = movieService.findById(movieId);

        if (movie.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
            return;
        }

        Rate rate = new Rate(rating, user, movie.get());

        rateService.create(rate);

        req.setAttribute("movieId", movie.get().getId());

        req.getRequestDispatcher(MOVIE_DETAIL_PATH).forward(req, resp);

    }
}
