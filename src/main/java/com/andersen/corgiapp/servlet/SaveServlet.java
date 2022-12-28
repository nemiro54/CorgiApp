package com.andersen.corgiapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.repository.UserRepository;
import com.andersen.corgiapp.repository.UserRepositoryImpl;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.service.UserServiceImpl;

@WebServlet(name = "SaveServlet", value = "/users/save")
public class SaveServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(SaveServlet.class);

    private static final String USER_LIST_PATH = "/users";
    private static final String NEW_USER_FORM_PATH = "/users/new";

    private static final String NAME_PARAMETER = "name";
    private static final String SURNAME_PARAMETER = "surname";
    private static final String AGE_PARAMETER = "age";

    private static final String ERROR_ATTRIBUTE_NAME = "errorMessage";

    private final UserService userService;

    public SaveServlet() {
        UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = new User();
            user.setName(request.getParameter(NAME_PARAMETER));
            user.setSurname(request.getParameter(SURNAME_PARAMETER));
            user.setAge(Integer.parseInt(request.getParameter(AGE_PARAMETER)));

            userService.save(user);

            response.sendRedirect(USER_LIST_PATH);
        }
        catch (RuntimeException e) {
            log.warn("Can't create user cause: ", e);
            request.setAttribute(ERROR_ATTRIBUTE_NAME, e.getMessage());
            request.getRequestDispatcher(NEW_USER_FORM_PATH).forward(request, response);
        }
    }
}
