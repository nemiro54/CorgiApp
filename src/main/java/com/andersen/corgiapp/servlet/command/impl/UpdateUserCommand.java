package com.andersen.corgiapp.servlet.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.servlet.command.Command;

public class UpdateUserCommand implements Command {

    public static final String USER_EDIT_PATH = "/WEB-INF/jsp/editUser.jsp";
    private static final Logger log = LoggerFactory.getLogger(UpdateUserCommand.class);
    private static final String USER_DETAILS_PATH = "%s/users/details?id=%d";

    private static final String USER_PARAMETER = "user";
    private static final String ID_PARAMETER = "id";
    private static final String NAME_PARAMETER = "name";
    private static final String SURNAME_PARAMETER = "surname";
    private static final String AGE_PARAMETER = "age";

    private static final String ERROR_ATTRIBUTE_NAME = "errorMessage";

    private final UserService userService;

    public UpdateUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = new User();
            user.setId(Long.parseLong(request.getParameter(ID_PARAMETER)));
            user.setName(request.getParameter(NAME_PARAMETER));
            user.setSurname(request.getParameter(SURNAME_PARAMETER));
            user.setAge(Integer.parseInt(request.getParameter(AGE_PARAMETER)));

            userService.update(user);

            response.sendRedirect(String.format(USER_DETAILS_PATH, request.getContextPath(), user.getId()));
        }
        catch (RuntimeException e) {
            log.warn("Can't update user cause: ", e);

            long userId = Long.parseLong(request.getParameter(ID_PARAMETER));
            User user = userService.find(userId);

            request.setAttribute(USER_PARAMETER, user);
            request.setAttribute(ERROR_ATTRIBUTE_NAME, e.getMessage());
            request.getRequestDispatcher(USER_EDIT_PATH).forward(request, response);
        }
    }
}
