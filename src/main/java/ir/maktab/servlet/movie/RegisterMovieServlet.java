package ir.maktab.servlet.movie;

import ir.maktab.model.enums.Genre;
import ir.maktab.model.Movie;
import ir.maktab.service.impl.MovieServiceImpl;
import ir.maktab.util.FileUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/movies")
@MultipartConfig
public class RegisterMovieServlet extends HttpServlet {

    private final static String MOVIE_REGISTER_PAGE= "pages/movie.jsp";

    private final MovieServiceImpl movieService = new MovieServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(MOVIE_REGISTER_PAGE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String title = req.getParameter("title");
        String description = req.getParameter("description");
        Double duration = Double.parseDouble(req.getParameter("duration"));
        Genre genre = Genre.valueOf(req.getParameter("genre"));
        String picBase64 = FileUtils.convertFileToBase64(req.getPart("picture"));

        if (title == null || title.isBlank() || description == null || description.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Title and description are required");
            return;
        }

        Movie movie = new Movie(title, genre, duration, picBase64,description);

        Movie movieAdded = movieService.create(movie);

        if (movieAdded != null) {
            resp.sendRedirect("allMovies");
        }
    }
}
