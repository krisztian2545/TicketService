package com.epam.training.ticketservice.domain.account;

public abstract class Account {

    private String username;

    private Privilege privilege;

    public Account(String username, Privilege privilege) {
        this.username = username;
        this.privilege = privilege;
    }

    public String getUsername() {
        return username;
    }

    public Privilege getPrivilege() {
        return privilege;
    }
}