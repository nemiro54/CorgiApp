package com.andersen.corgiapp.servlet.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.servlet.command.Command;

public class ShowAllUsersCommand implements Command {

    private static final String USERS_SHOW_PATH = "/WEB-INF/jsp/showUsers.jsp";

    private final UserService userService;

    public ShowAllUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", userService.findAll());
        request.getRequestDispatcher(USERS_SHOW_PATH).forward(request, response);
    }
}
