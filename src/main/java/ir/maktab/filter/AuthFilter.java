package ir.maktab.filter;

import jakarta.servlet.http.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/**
 * AuthFilter is a servlet filter that intercepts all HTTP requests (/*)
 * to enforce authentication before accessing protected resources.
 * <p>
 * It allows requests to login, register pages, index page, and static resources
 * (CSS, JS) to pass through without authentication.
 * <p>
 * For all other requests, it checks if a session exists and if the user
 * attribute is present. If the user is not authenticated, it redirects
 * the request to the login page. Otherwise, the request continues through
 * the filter chain.
 *
 */
@WebFilter("/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI();
        HttpSession session = req.getSession(false);

        if (path.endsWith("login") || path.endsWith("register") || path.endsWith("login.jsp")
                || path.endsWith("register.jsp") || path.endsWith("index.jsp") || path.endsWith(".js")
                || path.endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("user") == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
        } else {
            chain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
