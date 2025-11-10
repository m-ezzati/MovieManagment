package ir.maktab.servlet.movie;

import ir.maktab.model.Movie;
import ir.maktab.service.MovieService;
import ir.maktab.service.impl.MovieServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/searchMovies")
public class SearchServlet extends HttpServlet {

    private final static String MOVIE_LIST_PAGE= "/pages/moviesList.jsp";

    private final MovieService movieService = new MovieServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String genre = req.getParameter("genre");

        try {
            List<Movie> movies = movieService.searchByGenreAndTitle(genre, title);
            req.setAttribute("movies", movies);
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "No movies found matching the criteria");
        }

        req.getRequestDispatcher(MOVIE_LIST_PAGE).forward(req, resp);
    }
}
