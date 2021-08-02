package dev.paulmatthews.hashingsalting.middleware;

import dev.paulmatthews.hashingsalting.controllers.HomeController;
import dev.paulmatthews.hashingsalting.models.User;
import dev.paulmatthews.hashingsalting.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class AuthenticationFilter implements HandlerInterceptor {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HomeController homeController;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        if (isWhiteListed(request.getRequestURI())) {
            return true;
        }

        HttpSession session = request.getSession();
        User user = homeController.getUserFromSession(session);

        if(user == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }

    private static final List<String> whiteList = Arrays.asList("/login", "/register", "/logout");

    private static boolean isWhiteListed(String path) {
        for (String pathRoot : whiteList) {
            if (path.startsWith(pathRoot)) {
                return true;
            }
        }
        return false;
    }
}
