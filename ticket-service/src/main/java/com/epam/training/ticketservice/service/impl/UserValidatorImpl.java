package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.exception.UnsuccessfulAuthenticationException;
import com.epam.training.ticketservice.service.UserValidator;
import org.springframework.stereotype.Component;

@Component
public class UserValidatorImpl implements UserValidator {

    private String sessionToken = "";

    @Override
    public String authenticateAdmin(String username, String password) throws UnsuccessfulAuthenticationException {
        // auth ..
//        throw new UnsuccessfulAuthenticationException();

        sessionToken = generateToken();
        return sessionToken;
    }

    @Override
    public void authorizeAdmin(String token) {

    }

    private static String generateToken() {
        return "generate token here";
    }
}
