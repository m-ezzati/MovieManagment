package ir.maktab.servlet.user;

import ir.maktab.model.User;
import ir.maktab.service.UserService;
import ir.maktab.service.impl.UserServiceImpl;
import ir.maktab.util.FileUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/edit_profile")
@MultipartConfig
public class EditProfileServlet extends HttpServlet {

    private final UserService userService = new UserServiceImpl();

    private static final String ERROR_PAGE = "pages/errorPage.jsp";
    private static final String EDIT_PROFILE_PAGE = "pages/editProfile.jsp";
    private static final String EDIT_PROFILE_PATH = "/edit_profile";
    private static final String LOGIN_PATH = "/login";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String imgBase64 = FileUtils.convertFileToBase64(req.getPart("file"));

        String currentPassword = req.getParameter("currentPassword");
        String newPassword = req.getParameter("newPassword");
        String confirmNewPassword = req.getParameter("confirmNewPassword");

        if (username == null || email == null) {
            resp.sendRedirect(EDIT_PROFILE_PAGE);
            return;
        }

        if (isNewPasswordEntered(newPassword)) {
            if (!isPasswordConfirmed(newPassword, confirmNewPassword)) {
                forwardWithError(req, resp, ERROR_PAGE, "The password and confirm password aren't equal!");
                return;
            }
        }

        try {
            Optional<User> updatedUser = userService.updateProfile(username, email, imgBase64, currentPassword, newPassword);
            updatedUser.ifPresent(user -> session.setAttribute("user", user));
        } catch (RuntimeException e) {
            forwardWithError(req, resp, ERROR_PAGE, e.getMessage());
            return;
        }
        resp.sendRedirect(req.getContextPath() + EDIT_PROFILE_PATH);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session.getAttribute("user") == null){
            resp.sendRedirect(req.getContextPath() + LOGIN_PATH);
        }
            User user = (User) session.getAttribute("user");

        populateUserAttributes(req, user);

        req.getRequestDispatcher(EDIT_PROFILE_PAGE).forward(req, resp);

    }

    private void forwardWithError(HttpServletRequest req, HttpServletResponse resp,
                                  String page, String message) throws ServletException, IOException {
        req.setAttribute("javax.servlet.error.exception", message);
        req.getRequestDispatcher(page).forward(req, resp);

    }

    private boolean isNewPasswordEntered(String newPassword) {
        return !newPassword.trim().isEmpty();
    }

    private boolean isPasswordConfirmed(String newPassword, String confirmedNewPassword) {
        return newPassword.equals(confirmedNewPassword);
    }

    private void populateUserAttributes(HttpServletRequest req, User user) {
        req.setAttribute("username", user.getUsername());
        req.setAttribute("email", user.getEmail());

        if (user.getImg() != null) {
            req.setAttribute("profilePicture", user.getImg());
        }
    }
}
