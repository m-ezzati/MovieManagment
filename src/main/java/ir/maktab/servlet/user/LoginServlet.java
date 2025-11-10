package ir.maktab.servlet.user;

import ir.maktab.exception.AuthenticationException;
import ir.maktab.model.User;
import ir.maktab.service.UserService;
import ir.maktab.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final UserService service = new UserServiceImpl();

    private static final String LOGIN_PAGE = "pages/login.jsp";
    private static final String DASHBOARD_PATH = "/dashboard";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (isInvalidInput(username, password)) {
            forwardWithError(req,resp, LOGIN_PAGE, "Invalid username or password!");
            return;
        }

        User foundUser = service.login(username, password);

        if (foundUser == null) {
            forwardWithError(req,resp, LOGIN_PAGE, "Invalid username or password!");
            return;
        }

        createUserSession(req, foundUser);

        resp.sendRedirect(req.getContextPath() + DASHBOARD_PATH);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(LOGIN_PAGE);
    }

    private boolean isInvalidInput(String username, String password) {
        return username == null || username.isBlank() || password == null || password.isBlank();
    }

    private void createUserSession(HttpServletRequest req, User user) {
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(30 * 60);
    }

    private void forwardWithError(HttpServletRequest req, HttpServletResponse resp, String page,
                                  String message) throws ServletException, IOException {
        req.setAttribute("errorMessage",message);
        req.getRequestDispatcher(page).forward(req, resp);
    }

}
