package ir.maktab.servlet.movie;

import ir.maktab.model.Movie;
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

@WebServlet("/movieDetail")
public class MovieDetailServlet extends HttpServlet {
    private static final String MOVIE_DETAIL_PAGE = "/pages/movieDetail.jsp";
    private static final String LOGIN_PATH = "/login";

    private final MovieService movieService = new MovieServiceImpl();
    private final RateService rateService = new RateServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MOVIE_DETAIL_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        Long movieId = Long.parseLong(req.getParameter("movieId"));

        Optional<Movie> movie = movieService.findById(movieId);

        if (movie.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
            return;
        }

        User user = (User) session.getAttribute("user");
        if (user == null) {
            resp.sendRedirect(LOGIN_PATH);
        }

        int rate = rateService.getUserRateMovie(user.getId(), movie.get().getId());

        Double averageRating = movieService.getAverageRating(movie.get().getId());

        req.setAttribute("movie", movie.get());
        req.setAttribute("userRate", rate);
        req.setAttribute("averageRating", Math.round(averageRating));

        req.getRequestDispatcher(MOVIE_DETAIL_PAGE).forward(req, resp);

    }
}
