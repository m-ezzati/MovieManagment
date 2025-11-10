package ir.maktab.servlet.user;

import ir.maktab.service.UserService;
import ir.maktab.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    final private UserService service = new UserServiceImpl();

    private static final String LOGIN_PAGE = "pages/login.jsp";
    private static final String REGISTER_PAGE = "pages/register.jsp";
    private static final String ERROR_PAGE = "pages/errorPage.jsp";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        try {
            service.registerUser(username,email, password);
        } catch (RuntimeException e) {
            req.setAttribute("javax.servlet.error.exception", e.getMessage());
            req.getRequestDispatcher(ERROR_PAGE)
                    .forward(req, resp);
            return;
        }

        resp.sendRedirect(LOGIN_PAGE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(REGISTER_PAGE);
    }
}
