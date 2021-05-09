package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.domain.account.Account;
import com.epam.training.ticketservice.domain.account.Privilege;
import com.epam.training.ticketservice.domain.account.impl.AdminImpl;
import com.epam.training.ticketservice.service.CurrentUser;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserImpl implements CurrentUser {

    private Account currentUser;

    @Override
    public void setCurrentUser(String username, Privilege privilege) {
        switch (privilege) {
            case Admin:
                currentUser = new AdminImpl(username, privilege);
                break;
            case User:
        }
    }

    @Override
    public Account getCurrentUser() {
        return currentUser;
    }

    @Override
    public String getUsername() {
        return currentUser.getUsername();
    }

    @Override
    public Privilege getPrivilege() {
        return currentUser.getPrivilege();
    }


}
