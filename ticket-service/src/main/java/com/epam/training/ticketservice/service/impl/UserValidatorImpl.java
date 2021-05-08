package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.domain.account.Account;
import com.epam.training.ticketservice.exception.AlreadyLoggedInException;
import com.epam.training.ticketservice.exception.UnsuccessfulAuthenticationException;
import com.epam.training.ticketservice.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserValidatorImpl implements UserValidator {

    private String sessionToken;
    private Account currentUser;

    @Autowired
    public UserValidatorImpl(Account currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String authenticateAdmin(String username, String password) throws UnsuccessfulAuthenticationException,
            AlreadyLoggedInException {

        checkIfUserAlreadyLoggedIn();

        if (!"admin".equals(username) || !"admin".equals(password)) {
            throw new UnsuccessfulAuthenticationException();
        }
        currentUser.username = "admin";

        sessionToken = generateToken();
        return sessionToken;
    }

    @Override
    public void authorizeAdmin(String token) {

    }

    public void checkIfUserAlreadyLoggedIn() throws AlreadyLoggedInException {
        if (sessionToken != null) {
            throw new AlreadyLoggedInException();
        }
    }

    public void clearSessionToken() {
        sessionToken = null;
    }

    private static String generateToken() {
        return "generate token here";
    }
}
