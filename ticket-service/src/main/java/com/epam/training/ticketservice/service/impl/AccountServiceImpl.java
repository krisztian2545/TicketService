package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.exception.UnsuccessfulAuthenticationException;
import com.epam.training.ticketservice.service.AccountService;
import com.epam.training.ticketservice.service.UserValidator;
import com.epam.training.ticketservice.service.response.ResponseFactory;
import com.epam.training.ticketservice.service.response.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private UserValidator userValidator;

    @Autowired
    public AccountServiceImpl(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Override
    public SignInResponse signInAsAdmin(String username, String password) {
        String token;
        try {
            token = userValidator.authenticateAdmin(username, password);
        } catch (UnsuccessfulAuthenticationException e) {
            return ResponseFactory.unSuccessfulSignInResponse("Wrong username or password!");
        }

        return ResponseFactory.successfulSignInResponse(token);
    }

    @Override
    public String signInAsUser(String username, String password) {
        return null;
    }

    @Override
    public String signOut() {
        return null;
    }
}
