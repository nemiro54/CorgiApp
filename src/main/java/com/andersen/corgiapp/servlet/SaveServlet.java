package com.andersen.corgiapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andersen.corgiapp.entity.User;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.service.UserServiceImpl;

@WebServlet(name = "SaveServlet", value = "/users/save")
public class SaveServlet extends HttpServlet {

    private static final String NAME_PARAMETER = "name";
    private static final String SURNAME_PARAMETER = "surname";
    private static final String AGE_PARAMETER = "age";

    private final UserService userService;

    private static final String USER_LIST_PATH = "/users";

    public SaveServlet() {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setName(request.getParameter(NAME_PARAMETER));
        user.setSurname(request.getParameter(SURNAME_PARAMETER));
        user.setAge(Integer.parseInt(request.getParameter(AGE_PARAMETER)));

        userService.save(user);

        response.sendRedirect(USER_LIST_PATH);
    }
}
