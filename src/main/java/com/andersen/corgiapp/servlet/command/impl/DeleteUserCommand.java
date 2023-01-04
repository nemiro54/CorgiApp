package com.andersen.corgiapp.servlet.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.andersen.corgiapp.exception.EntityNotFoundException;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.servlet.command.Command;

public class DeleteUserCommand implements Command {

    private static final Logger log = LoggerFactory.getLogger(DeleteUserCommand.class);

    private static final String ID_PARAMETER = "id";

    private static final String ERROR_ATTRIBUTE_NAME = "errorMessage";

    private static final String USER_LIST_PATH = "/users";

    private final UserService userService;

    public DeleteUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
