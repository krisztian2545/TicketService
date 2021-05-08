package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.exception.AlreadyLoggedInException;
import com.epam.training.ticketservice.exception.UnsuccessfulAuthenticationException;

public interface UserValidator {

    String authenticateAdmin(String username, String password) throws UnsuccessfulAuthenticationException,
            AlreadyLoggedInException;

    void authorizeAdmin(String token);
}
