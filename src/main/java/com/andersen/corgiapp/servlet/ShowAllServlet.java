package com.andersen.corgiapp.servlet;

import com.andersen.corgiapp.repository.UserRepository;
import com.andersen.corgiapp.repository.UserRepositoryImpl;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShowAllServlet", value = "/users")
public class ShowAllServlet extends HttpServlet {

    private static final String USERS_SHOW_PATH = "/WEB-INF/jsp/showUsers.jsp";
    private final UserService userService;

    public ShowAllServlet() {
        UserRepository userRepository = new UserRepositoryImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", userService.findAll());
        request.getRequestDispatcher(USERS_SHOW_PATH).forward(request, response);
    }
}
