package ir.maktab.servlet.user;

import ir.maktab.model.User;
import ir.maktab.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;

import java.io.IOException;

@WebServlet("/addToWatchlist")
public class AddToWatchlistServlet extends HttpServlet {
    private static final String MOVIES_LIST_PAGE = "/pages/moviesList.jsp";
    private static final String DASHBOARD_PATH = "/dashboard";
    private static final String LOGIN_PATH = "/login";

    private final UserServiceImpl userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(MOVIES_LIST_PAGE).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User loginUser = getLoggedInUser(session);

        if(loginUser == null){
            resp.sendRedirect(LOGIN_PATH);
            return;
        }
        Long userId = loginUser.getId();
        Long movieId = Long.parseLong(req.getParameter("movieId"));

        userService.addMovieToWatchlist(userId, movieId);

        resp.sendRedirect(req.getContextPath() + DASHBOARD_PATH);

    }

    private User getLoggedInUser(HttpSession session) {
        return session == null
                ? null
                : (User) session.getAttribute("user");
    }

}
