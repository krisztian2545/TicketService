package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.domain.account.Privilege;
import com.epam.training.ticketservice.exception.AlreadyLoggedInException;
import com.epam.training.ticketservice.service.CurrentUser;
import com.epam.training.ticketservice.service.UserValidator;
import com.epam.training.ticketservice.service.response.BasicCommandResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

class AccountServiceImplTest {

    private UserValidator userValidator;
    private CurrentUser currentUser;
    private AccountServiceImpl underTest;


    @BeforeEach
    void setUp() {
        userValidator = Mockito.mock(UserValidatorImpl.class);
        currentUser = Mockito.mock(CurrentUser.class);

        underTest = new AccountServiceImpl(userValidator, currentUser);
    }

    @Test
    void signInAsAdmin() {
    }

    @Test
    void signInAsUser() {
    }

    @Test
    void signOut() {
    }

    @Test
    void testDescribeAccountReturnsErrorMessageInBasicCommandResponseWhenNotSignedIn() {
        BasicCommandResponse result = underTest.describeAccount();

        assertFalse(result.isSuccessful());
    }

    @Test
    void testDescribeAccountReturnsSuccessfulBasicCommandResponseWhenSignedInAsAdmin() throws AlreadyLoggedInException {
        given(currentUser.getPrivilege()).willReturn(Privilege.Admin);
        given(currentUser.getUsername()).willReturn("admin");
        doThrow(AlreadyLoggedInException.class).when((UserValidatorImpl) userValidator).checkIfUserAlreadyLoggedIn();

        BasicCommandResponse result = underTest.describeAccount();

        assertTrue(result.isSuccessful() && result.getMessage().equals("Signed in with privileged account 'admin'"));
    }

    @Test
    void testDescribeAccountReturnsSuccessfulBasicCommandResponseWhenSignedInAsUser() throws AlreadyLoggedInException {
        given(currentUser.getPrivilege()).willReturn(Privilege.User);
        given(currentUser.getUsername()).willReturn("user");
        doThrow(AlreadyLoggedInException.class).when((UserValidatorImpl) userValidator).checkIfUserAlreadyLoggedIn();

        BasicCommandResponse result = underTest.describeAccount();

        assertTrue(result.isSuccessful() && result.getMessage().equals("Signed in with account 'user'"));
    }
}