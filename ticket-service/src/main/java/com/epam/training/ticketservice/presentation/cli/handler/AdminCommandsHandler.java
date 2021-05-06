package com.epam.training.ticketservice.presentation.cli.handler;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AdminCommandsHandler {

    @ShellMethod(value = "Sign in as admin user.", key = "sign in privileged")
    public String signInPrivileged(String username, String password) {
        return "you are the boss " + username + " " + password;
    }

    @ShellMethod(value = "Sign out.", key = "sign out")
    public String signOutPrivileged() {
        return "Signed out successfully.";
    }
}
