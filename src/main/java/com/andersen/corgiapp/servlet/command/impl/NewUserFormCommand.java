package com.andersen.corgiapp.servlet.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andersen.corgiapp.servlet.command.Command;

public class NewUserFormCommand implements Command {

    private static final String USER_EDIT_PATH = "/WEB-INF/jsp/addUser.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(USER_EDIT_PATH).forward(request, response);
    }
}
