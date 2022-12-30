package com.andersen.corgiapp.servlet;

import com.andersen.corgiapp.entity.User;
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

@WebServlet(name = "EditUserServlet", value = "/users/edit")
public class EditUserServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(EditUserServlet.class);

    public static final String USER_EDIT_PATH = "/WEB-INF/jsp/editUser.jsp";
    private static final String USER_LIST_PATH = "/users";

    private static final String ID_PARAMETER = "id";
    private static final String NAME_PARAMETER = "name";
    private static final String SURNAME_PARAMETER = "surname";
    private static final String AGE_PARAMETER = "age";

    private static final String ERROR_ATTRIBUTE_NAME = "errorMessage";

    private final UserService userService;

    public EditUserServlet() {
        UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(USER_EDIT_PATH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long userId = Long.parseLong(request.getParameter(ID_PARAMETER));
            User user = userService.find(userId);

            user.setName(request.getParameter(NAME_PARAMETER));
            user.setSurname(request.getParameter(SURNAME_PARAMETER));
            user.setAge(Integer.parseInt(request.getParameter(AGE_PARAMETER)));

            userService.update(user);

            response.sendRedirect(USER_LIST_PATH);
        }
        catch (RuntimeException e) {
            log.warn("Can't update user cause: ", e);
            request.setAttribute(ERROR_ATTRIBUTE_NAME, e.getMessage());
            request.getRequestDispatcher(USER_EDIT_PATH).forward(request, response);
        }
    }
}
