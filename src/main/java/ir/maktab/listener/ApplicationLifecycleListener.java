package ir.maktab.listener;

import ir.maktab.model.enums.Role;
import ir.maktab.model.User;
import ir.maktab.service.UserService;
import ir.maktab.service.impl.UserServiceImpl;

import ir.maktab.util.JpaUtil;
import ir.maktab.util.PasswordEncoder;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * ApplicationLifecycleListener ServletContextListener that handles
 * initialization and cleanup tasks for the web application.
 * <p>
 * When the application starts, it checks if an admin user exists.
 * If not, it creates a default admin user with username "admin" and password "admin".
 *
 */
@WebListener
public class ApplicationLifecycleListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        UserService userService = new UserServiceImpl();
        if(userService.findUserByUsername("admin")==null){
            User admin = new User("admin","admin@s.ir", PasswordEncoder.encode("admin"));
            admin.setRole(Role.ROLE_ADMIN);

            userService.create(admin);
            System.out.println("admin created successfully");
        }
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        JpaUtil.closeFactory();
    }
}
