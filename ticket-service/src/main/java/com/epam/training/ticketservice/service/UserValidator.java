package com.epam.training.ticketservice.domain.account;

public interface AccountSecurity {

    void authenticateAdmin(String username, String password);

    void authorizeAdmin(String token);
}
