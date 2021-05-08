package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.exception.AlreadyLoggedInException;
import com.epam.training.ticketservice.exception.UnsuccessfulAuthenticationException;
import com.epam.training.ticketservice.service.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserValidatorImpl implements UserValidator {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserValidatorImpl.class);

    private String sessionToken;

    @Override
    public String authenticateAdmin(String username, String password) throws UnsuccessfulAuthenticationException, AlreadyLoggedInException {
        checkIfUserAlreadyLoggedIn();

        if (!"admin".equals(username) || !"admin".equals(password)) {
            throw new UnsuccessfulAuthenticationException();
        }

        sessionToken = generateToken();
        return sessionToken;
    }

    @Override
    public void authorizeAdmin(String token) {

    }

    private void checkIfUserAlreadyLoggedIn() throws AlreadyLoggedInException {
        if (sessionToken != null)
            throw new AlreadyLoggedInException();
    }

    public void clearSessionToken() {
        sessionToken = null;
    }

    private static String generateToken() {
        return "generate token here";
    }
}
