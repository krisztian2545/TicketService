package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.clientservice.cli.AccountCommandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class AccountCommandsHandler {

    private AccountCommandsService accountCommandsService;

    @Autowired
    public AccountCommandsHandler(AccountCommandsService accountCommandsService) {
        this.accountCommandsService = accountCommandsService;
    }

    @ShellMethod(value = "Sign in as admin user.", key = "sign in privileged")
    public String signInPrivileged(String username, String password) {
        return accountCommandsService.signIn(username, password);
    }

    @ShellMethod(value = "Sign out.", key = "sign out")
    public String signOutPrivileged() {
        return accountCommandsService.signOut();
    }

    @ShellMethod(value = "Describe account", key = "describe account")
    public String describeAccount() {
        return accountCommandsService.describeAccount();
    }
}
