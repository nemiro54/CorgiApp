package com.andersen.corgiapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andersen.corgiapp.exception.EntityNotFoundException;
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
            userService.delete(id);
            response.sendRedirect(request.getContextPath() + USER_LIST_PATH);
        }
        catch (EntityNotFoundException e) {
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
