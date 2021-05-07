package com.epam.training.ticketservice.clientservice.cli.impl;

import com.epam.training.ticketservice.clientservice.Client;
import com.epam.training.ticketservice.clientservice.cli.AdminCommandsService;
import com.epam.training.ticketservice.service.AccountService;
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
        accountService.signInAsAdmin(username, password);
        client.setSessionToken("session");
        return null;
    }

    @Override
    public String signOut() {
        client.setSessionToken(null);
        return null;
    }
}
