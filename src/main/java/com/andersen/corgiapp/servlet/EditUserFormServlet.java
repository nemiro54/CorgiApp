package com.andersen.corgiapp.servlet;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.exception.EntityNotFoundException;
import com.andersen.corgiapp.repository.UserRepository;
import com.andersen.corgiapp.repository.UserRepositoryImpl;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditUserFormServlet", value = "/users/edit")
public class EditUserFormServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(EditUserFormServlet.class);

    public static final String USER_EDIT_PATH = "/WEB-INF/jsp/editUser.jsp";
    private static final String USER_LIST_PATH = "/users";

    private static final String USER_PARAMETER = "user";
    private static final String ID_PARAMETER = "id";

    private static final String ERROR_ATTRIBUTE_NAME = "errorMessage";

    private final UserService userService;

    public EditUserFormServlet() {
        UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long userId = Long.parseLong(request.getParameter(ID_PARAMETER));
            User user = userService.find(userId);

            request.setAttribute(USER_PARAMETER, user);
            request.getRequestDispatcher(USER_EDIT_PATH).forward(request, response);
        } catch (EntityNotFoundException e) {
            log.warn("Can't update user cause: ", e);
            request.setAttribute(ERROR_ATTRIBUTE_NAME, e.getMessage());
            request.getRequestDispatcher(USER_LIST_PATH).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
