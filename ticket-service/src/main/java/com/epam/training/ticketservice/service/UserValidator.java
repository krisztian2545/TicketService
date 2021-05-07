package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.exception.UnsuccessfulAuthenticationException;

public interface UserValidator {

    String authenticateAdmin(String username, String password) throws UnsuccessfulAuthenticationException;

    void authorizeAdmin(String token);
}
