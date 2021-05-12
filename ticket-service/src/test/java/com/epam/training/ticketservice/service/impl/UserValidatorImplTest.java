package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.domain.account.Privilege;
import com.epam.training.ticketservice.exception.AccessDeniedException;
import com.epam.training.ticketservice.exception.AlreadyLoggedInException;
import com.epam.training.ticketservice.exception.UnsuccessfulAuthenticationException;
import com.epam.training.ticketservice.service.CurrentUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class UserValidatorImplTest {

    UserValidatorImpl underTest;
    CurrentUser currentUser;

    @BeforeEach
    void setUp() {
        currentUser = Mockito.mock(CurrentUser.class);
        underTest = new UserValidatorImpl(currentUser);
    }

    @Test
    void testAuthenticateAdminShouldThrowUnsuccessfulAuthenticationExceptionWithIncorrectCredentials() {
        assertThrows(UnsuccessfulAuthenticationException.class, () ->
                underTest.authenticateAdmin("not an admin username", "not an admin password"));
    }

    @Test
    void testAuthorizeAdminShouldThrowAccessDeniedExceptionWhenNotLoggedIn() {
        assertThrows(AccessDeniedException.class, () -> underTest.authorizeAdmin(null));
    }

    @Test
    void testAuthorizeAdminShouldThrowAccessDeniedExceptionWhenInvalidToken() {
        given(currentUser.getPrivilege()).willReturn(Privilege.Admin);
        successfullyAuthenticateAdmin();

        assertThrows(AccessDeniedException.class, () -> underTest.authorizeAdmin("invalid token"));
    }

    @Test
    void testAuthorizeAdminShouldDoNothingWhenTokenIsGiven() throws AccessDeniedException {
        given(currentUser.getPrivilege()).willReturn(Privilege.Admin);
        String token = successfullyAuthenticateAdmin();

        underTest.authorizeAdmin(token);
    }

    @Test
    void testClearSessionTokenShouldLogTheUserOut() throws AlreadyLoggedInException {
        given(currentUser.getPrivilege()).willReturn(Privilege.Admin);
        successfullyAuthenticateAdmin();

        underTest.clearSessionToken();
        underTest.checkIfUserAlreadyLoggedIn();
    }

    @Test
    public void testCheckIfUserAlreadyLoggedInShouldThrowAlreadyLoggedInException() {
        successfullyAuthenticateAdmin();

        assertThrows(AlreadyLoggedInException.class, () -> underTest.checkIfUserAlreadyLoggedIn());
    }

    private String successfullyAuthenticateAdmin() {
        try {
            return underTest.authenticateAdmin("admin", "admin");
        } catch (UnsuccessfulAuthenticationException | AlreadyLoggedInException e) {
            e.printStackTrace();
            return null;
        }
    }

}