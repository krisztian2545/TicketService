package com.epam.training.ticketservice.domain.account.impl;

import com.epam.training.ticketservice.domain.account.Account;
import com.epam.training.ticketservice.domain.account.Admin;
import com.epam.training.ticketservice.domain.account.Privilege;

public class AdminImpl extends Account implements Admin {
    public AdminImpl(String username) {
        super(username, Privilege.Admin);
    }
}
