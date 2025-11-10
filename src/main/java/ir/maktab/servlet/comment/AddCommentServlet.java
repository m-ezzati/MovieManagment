package ir.maktab.servlet.comment;

import ir.maktab.model.Comment;
import ir.maktab.model.Movie;
import ir.maktab.model.User;
import ir.maktab.service.CommentService;
import ir.maktab.service.MovieService;
import ir.maktab.service.impl.CommentServiceImpl;
import ir.maktab.service.impl.MovieServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/addComment")
public class AddCommentServlet extends HttpServlet {

    private final static String MOVIE_DETAIL_PATH = "/movieDetail";
    private static final String LOGIN_PATH = "/login";

    private final CommentService commentService = new CommentServiceImpl();
    private final MovieService movieService = new MovieServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String commentMsg = req.getParameter("comment");
        Long movieId = Long.valueOf(req.getParameter("movieId"));

        HttpSession session = req.getSession(false);

        User user = (User) session.getAttribute("user");

        if (user == null){
            resp.sendRedirect(LOGIN_PATH);
        }

        Optional<Movie> movie = movieService.findById(movieId);

        if (movie.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Movie doesn't exist");
            return;
        }

        if (commentMsg == null || commentMsg.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Comment cannot be empty");
            return;
        }

        Comment comment = new Comment(commentMsg, user, movie.get());

        try {
            commentService.create(comment);
        } catch (RuntimeException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Something went wrong");
            return;
        }

        req.setAttribute("movieId", movie.get().getId());

        req.getRequestDispatcher(MOVIE_DETAIL_PATH).forward(req, resp);

    }
}
