package com.andersen.corgiapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.andersen.corgiapp.repository.UserRepository;
import com.andersen.corgiapp.repository.UserRepositoryImpl;
import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.service.UserServiceImpl;
import com.andersen.corgiapp.servlet.command.Command;
import com.andersen.corgiapp.servlet.command.CommandProvider;

@WebServlet(name = "UserServlet", value = "/users/*")
public class UserServlet extends HttpServlet {

    private final CommandProvider commandProvider;

    public UserServlet() {
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        this.commandProvider = new CommandProvider(userService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandName = request.getPathInfo();
        Command command = commandProvider.getCommand(commandName);
        command.execute(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
