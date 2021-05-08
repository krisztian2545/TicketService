package com.epam.training.ticketservice.clientservice.cli.impl;

import com.epam.training.ticketservice.clientservice.Client;
import com.epam.training.ticketservice.clientservice.cli.AdminCommandsService;
import com.epam.training.ticketservice.service.AccountService;
import com.epam.training.ticketservice.service.response.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminCommandsServiceImpl implements AdminCommandsService {

    private Client client;
    private AccountService accountService;

    @Autowired
    public AdminCommandsServiceImpl(Client client, AccountService accountService) {
        this.client = client;
        this.accountService = accountService;
    }

    @Override
    public String signIn(String username, String password) {
        SignInResponse response = accountService.signInAsAdmin(username, password);

        if (!response.isSuccessful()) {
            return response.getErrorMessage();
        }

        client.setSessionToken(response.getToken());
        return "Successfully logged in.";
    }

    @Override
    public String signOut() {
        String output = accountService.signOut();
        client.setSessionToken(null);
        return output;
    }

    @Override
    public String describeAccount() {
        return accountService.describeAccount();
    }
}
