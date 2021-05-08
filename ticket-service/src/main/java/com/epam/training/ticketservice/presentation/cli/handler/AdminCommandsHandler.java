package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.clientservice.cli.AdminCommandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AdminCommandsHandler {

    private AdminCommandsService adminCommandsService;

    @Autowired
    public AdminCommandsHandler(AdminCommandsService adminCommandsService) {
        this.adminCommandsService = adminCommandsService;
    }

    @ShellMethod(value = "Sign in as admin user.", key = "sign in privileged")
    public String signInPrivileged(String username, String password) {
        return adminCommandsService.signIn(username, password);
    }

    @ShellMethod(value = "Sign out.", key = "sign out")
    public String signOutPrivileged() {
        return adminCommandsService.signOut();
    }

    @ShellMethod(value = "Describe account", key = "describe account")
    public String describeAccount() {
        return adminCommandsService.describeAccount();
    }
}
