package com.andersen.corgiapp.servlet.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.servlet.command.Command;

public class UserDetailsCommand implements Command {

    public static final String ID_PARAMETER = "id";
    public static final String USER_PARAMETER = "user";
    public static final String USER_SEARCH_PATH = "/WEB-INF/jsp/userSearch.jsp";
    public static final String USER_DETAILS_PATH = "/WEB-INF/jsp/userDetails.jsp";
    private static final String ERROR_ATTRIBUTE_NAME = "errorMessage";
    private final UserService userService;

    public UserDetailsCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long userId = Long.parseLong(request.getParameter(ID_PARAMETER));
            User user = userService.find(userId);

            request.setAttribute(USER_PARAMETER, user);

            request.getRequestDispatcher(USER_DETAILS_PATH).forward(request, response);
        }
        catch (RuntimeException e) {
            request.setAttribute(ERROR_ATTRIBUTE_NAME, e.getMessage());
            request.getRequestDispatcher(USER_SEARCH_PATH).forward(request, response);
        }
    }
}
