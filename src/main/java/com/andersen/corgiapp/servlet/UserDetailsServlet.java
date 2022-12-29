package com.andersen.corgiapp.servlet;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.repository.UserRepository;
import com.andersen.corgiapp.repository.UserRepositoryImpl;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserDetailsServlet", value = "/users/details")
public class UserDetailsServlet extends HttpServlet {
    public static final String ID_PARAMETER = "id";
    public static final String USER_PARAMETER = "user";

    private static final String ERROR_ATTRIBUTE_NAME = "errorMessage";

    public static final String USER_SEARCH_PATH = "/WEB-INF/jsp/userSearch.jsp";
    public static final String USER_DETAILS_PATH = "/WEB-INF/jsp/userDetails.jsp";

    private final UserService userService;

    public UserDetailsServlet() {
        UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long userId = Long.parseLong(request.getParameter(ID_PARAMETER));
            User user = userService.find(userId);

            request.setAttribute(USER_PARAMETER, user);

            request.getRequestDispatcher(USER_DETAILS_PATH).forward(request, response);
        } catch (RuntimeException e) {
            request.setAttribute(ERROR_ATTRIBUTE_NAME, e.getMessage());
            request.getRequestDispatcher(USER_SEARCH_PATH).forward(request, response);
        }
    }
}
