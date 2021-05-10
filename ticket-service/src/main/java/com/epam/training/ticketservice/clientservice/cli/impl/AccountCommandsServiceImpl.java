package com.epam.training.ticketservice.clientservice.cli.impl;

import com.epam.training.ticketservice.clientservice.Client;
import com.epam.training.ticketservice.clientservice.cli.AccountCommandsService;
import com.epam.training.ticketservice.service.AccountService;
import com.epam.training.ticketservice.service.response.BasicCommandResponse;
import com.epam.training.ticketservice.service.response.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandsServiceImpl implements AccountCommandsService {

    private Client client;
    private AccountService accountService;

    @Autowired
    public AccountCommandsServiceImpl(Client client, AccountService accountService) {
        this.client = client;
        this.accountService = accountService;
    }

    @Override
    public String signIn(String username, String password) {
        BasicCommandResponse response = accountService.signInAsAdmin(username, password);

        if (!response.isSuccessful()) {
            return response.getMessage();
        }

        client.setSessionToken(((SignInResponse) response).getToken());
        return "Successfully logged in.";
    }

    @Override
    public String signOut() {
        BasicCommandResponse response = accountService.signOut();

        if (!response.isSuccessful()) {
            return response.getMessage();
        }

        client.setSessionToken(null);
        return "signed out successfully";
    }

    @Override
    public String describeAccount() {
        BasicCommandResponse response = accountService.describeAccount();
        return response.getMessage();
    }
}
