package com.epam.training.ticketservice.domain.account.impl;

import com.epam.training.ticketservice.domain.account.Account;
import com.epam.training.ticketservice.domain.account.Privilege;

public class User extends Account {
    public User(String username) {
        super(username, Privilege.User);
    }
}
