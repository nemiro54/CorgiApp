package com.andersen.corgiapp.servlet.command;

import java.util.HashMap;
import java.util.Map;

import com.andersen.corgiapp.service.UserService;
import com.andersen.corgiapp.servlet.command.impl.DeleteUserCommand;
import com.andersen.corgiapp.servlet.command.impl.EditUserFormCommand;
import com.andersen.corgiapp.servlet.command.impl.NewUserFormCommand;
import com.andersen.corgiapp.servlet.command.impl.NotFoundCommand;
import com.andersen.corgiapp.servlet.command.impl.SaveUserCommand;
import com.andersen.corgiapp.servlet.command.impl.ShowAllUsersCommand;
import com.andersen.corgiapp.servlet.command.impl.UpdateUserCommand;
import com.andersen.corgiapp.servlet.command.impl.UserDetailsCommand;
import com.andersen.corgiapp.servlet.command.impl.UserSearchCommand;

public class CommandProvider {

    private static final String EMPTY_COMMAND = null;
    private static final String SHOW_ALL_USERS_COMMAND = "/";
    private static final String NEW_USER_FORM_COMMAND = "/new";
    private static final String SAVE_COMMAND = "/save";
    private static final String USER_DETAILS_COMMAND = "/details";
    private static final String USER_SEARCH_COMMAND = "/search";
    private static final String EDIT_USER_FORM_COMMAND = "/edit";
    private static final String UPDATE_USER_COMMAND = "/update";
    private static final String DELETE_USER_COMMAND = "/delete";

    private final Command notFoundCommand;

    private final Map<String, Command> commandMap;

    public CommandProvider(UserService userService) {
        this.commandMap = new HashMap<>();

        Command showAllUsersCommand = new ShowAllUsersCommand(userService);
        commandMap.put(EMPTY_COMMAND, showAllUsersCommand);
        commandMap.put(SHOW_ALL_USERS_COMMAND, showAllUsersCommand);
        commandMap.put(NEW_USER_FORM_COMMAND, new NewUserFormCommand());
        commandMap.put(SAVE_COMMAND, new SaveUserCommand(userService));
        commandMap.put(USER_DETAILS_COMMAND, new UserDetailsCommand(userService));
        commandMap.put(USER_SEARCH_COMMAND, new UserSearchCommand());
        commandMap.put(EDIT_USER_FORM_COMMAND, new EditUserFormCommand(userService));
        commandMap.put(UPDATE_USER_COMMAND, new UpdateUserCommand(userService));
        commandMap.put(DELETE_USER_COMMAND, new DeleteUserCommand(userService));

        notFoundCommand = new NotFoundCommand();
    }

    public Command getCommand(String commandName) {
        if (!commandMap.containsKey(commandName)) {
            return notFoundCommand;
        }

        return commandMap.get(commandName);
    }
}
