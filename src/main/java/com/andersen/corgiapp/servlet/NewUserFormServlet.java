package com.andersen.corgiapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "NewUserFormServlet", value = "/users/new")
public class NewUserFormServlet extends HttpServlet {

    private static final String USER_EDIT_PATH = "/WEB-INF/jsp/addUser.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(USER_EDIT_PATH).forward(request, response);
    }
}
