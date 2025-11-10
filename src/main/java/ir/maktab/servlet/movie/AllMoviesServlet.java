package ir.maktab.servlet.movie;

import ir.maktab.model.Movie;
import ir.maktab.model.User;
import ir.maktab.service.MovieService;
import ir.maktab.service.impl.MovieServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/allMovies")
public class AllMoviesServlet extends HttpServlet {
    private static final String LOGIN_PATH = "/login";
    private static final String MOVIE_LIST_PAGE = "/pages/moviesList.jsp";

    private final MovieService movieService = new MovieServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("user");
        if (loginUser == null) {
            resp.sendRedirect(req.getContextPath() + LOGIN_PATH);
            return;
        }

        List<Movie> movies = movieService.findAll();

        req.setAttribute("movies", movies);
        req.setAttribute("username", loginUser.getUsername());
        req.setAttribute("role", loginUser.getRole());
        req.setAttribute("all", true);


        req.getRequestDispatcher(MOVIE_LIST_PAGE).forward(req, resp);

    }
}
