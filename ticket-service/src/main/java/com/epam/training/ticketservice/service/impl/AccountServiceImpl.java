package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.domain.account.Account;
import com.epam.training.ticketservice.domain.account.Privilege;
import com.epam.training.ticketservice.exception.AlreadyLoggedInException;
import com.epam.training.ticketservice.exception.UnsuccessfulAuthenticationException;
import com.epam.training.ticketservice.service.AccountService;
import com.epam.training.ticketservice.service.CurrentUser;
import com.epam.training.ticketservice.service.UserValidator;
import com.epam.training.ticketservice.service.response.BasicResponse;
import com.epam.training.ticketservice.service.response.ResponseFactory;
import com.epam.training.ticketservice.service.response.SignInResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private UserValidator userValidator;
    private UserValidatorImpl specificUserValidatorImpl;
    private CurrentUser currentUser;

    @Autowired
    public AccountServiceImpl(UserValidator userValidator, CurrentUser user) {
        this.userValidator = userValidator;
        specificUserValidatorImpl = (UserValidatorImpl) userValidator;
        currentUser = user;
    }

    @Override
    public SignInResponse signInAsAdmin(String username, String password) {
        String token;
        try {
            token = userValidator.authenticateAdmin(username, password);
        } catch (AlreadyLoggedInException e) {
            return null;
        } catch (UnsuccessfulAuthenticationException e) {
            return ResponseFactory.unSuccessfulSignInResponse("Login failed due to incorrect credentials");
        }

        return ResponseFactory.successfulSignInResponse(token);
    }

    @Override
    public BasicResponse signInAsUser(String username, String password) { // TODO
        return null;
    }

    @Override
    public BasicResponse signOut() {
        specificUserValidatorImpl.clearSessionToken();
        return ResponseFactory.successResponse();
    }

    @Override
    public BasicResponse describeAccount() {
        try {
            specificUserValidatorImpl.checkIfUserAlreadyLoggedIn();
        } catch (AlreadyLoggedInException e) {
            switch (currentUser.getPrivilege()) {
                case Admin:
                    return ResponseFactory.successResponse(String.format("Signed in with privileged account '%s'", currentUser.getUsername()));
                case User:
                    return ResponseFactory.successResponse(String.format("Signed in with account '%s'", currentUser.getUsername()));
            }
        }
        return ResponseFactory.errorResponse("You are not signed in");
    }


}
