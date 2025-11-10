package ir.maktab.servlet.user;

import ir.maktab.model.Movie;
import ir.maktab.model.User;
import ir.maktab.service.UserService;
import ir.maktab.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet("/dashboard")
@MultipartConfig
public class UserDashboardServlet extends HttpServlet {

    private static final String LOGIN_PAGE = "pages/login.jsp";
    private static final String USER_DASHBOARD_PAGE = "/pages/userDashboard.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        User user = getLoggedInUser(session);

        if (user == null) {
            resp.sendRedirect(LOGIN_PAGE);
            return;
        }

        populateUserAttributes(req, user);

        req.getRequestDispatcher(USER_DASHBOARD_PAGE).forward(req, resp);
    }

    private User getLoggedInUser(HttpSession session) {
        return session == null
                ? null
                : (User) session.getAttribute("user");
    }

    private void populateUserAttributes(HttpServletRequest req, User user){
        req.setAttribute("username", user.getUsername());
        req.setAttribute("role", user.getRole());

        List<Movie> watchList = user.getWatchList() == null
                ? new ArrayList<>()
                : new ArrayList<>(user.getWatchList());

        req.setAttribute("watchList", watchList);
    }
}
