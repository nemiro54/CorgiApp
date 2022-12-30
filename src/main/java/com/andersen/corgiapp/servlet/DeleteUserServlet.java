package com.andersen.corgiapp.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.exception.ModelNotFoundException;
import com.andersen.corgiapp.repository.UserRepository;
import com.andersen.corgiapp.repository.UserRepositoryImpl;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.service.UserServiceImpl;

@WebServlet(name = "DeleteUserServlet", value = "/users/delete")
public class DeleteUserServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DeleteUserServlet.class);

    private static final String ID_PARAMETER = "id";

    private static final String ERROR_ATTRIBUTE_NAME = "errorMessage";

    private static final String USER_LIST_PATH = "/users";

    private final UserService userService;

    public DeleteUserServlet() {
        UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long id = Long.parseLong(request.getParameter(ID_PARAMETER));
            User user = userService.find(id);
            userService.delete(user);
            response.sendRedirect(request.getContextPath() + USER_LIST_PATH);
        }
        catch (ModelNotFoundException e) {
            log.warn("Can't delete user cause: ", e);
            request.setAttribute(ERROR_ATTRIBUTE_NAME, e.getMessage());
            request.getRequestDispatcher(USER_LIST_PATH).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
