package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.domain.account.Account;
import com.epam.training.ticketservice.domain.account.Privilege;

public interface CurrentUser {
    void setCurrentUser(String username, Privilege privilege);

    Account getCurrentUser();

    String getUsername();

    Privilege getPrivilege();
}
