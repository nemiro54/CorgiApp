package com.andersen.corgiapp.servlet.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.exception.ModelNotFoundException;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.servlet.command.Command;

public class EditUserFormCommand implements Command {

    private static final Logger log = LoggerFactory.getLogger(EditUserFormCommand.class);

    public static final String USER_EDIT_PATH = "/WEB-INF/jsp/editUser.jsp";
    private static final String USER_LIST_PATH = "/users";

    private static final String USER_PARAMETER = "user";
    private static final String ID_PARAMETER = "id";

    private static final String ERROR_ATTRIBUTE_NAME = "errorMessage";

    private final UserService userService;

    public EditUserFormCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long userId = Long.parseLong(request.getParameter(ID_PARAMETER));
            User user = userService.find(userId);

            request.setAttribute(USER_PARAMETER, user);
            request.getRequestDispatcher(USER_EDIT_PATH).forward(request, response);
        }
        catch (ModelNotFoundException e) {
            log.warn("Can't update user cause: ", e);
            request.setAttribute(ERROR_ATTRIBUTE_NAME, e.getMessage());
            request.getRequestDispatcher(USER_LIST_PATH).forward(request, response);
        }
    }
}
