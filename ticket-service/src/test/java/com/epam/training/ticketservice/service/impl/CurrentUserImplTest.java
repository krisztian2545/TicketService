package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.domain.account.Privilege;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrentUserImplTest {

    @Test
    void testGetUsername() {
        CurrentUserImpl underTest = new CurrentUserImpl();
        underTest.setCurrentUser("username", Privilege.User);

        assertEquals("username", underTest.getUsername());
    }

    @Test
    void testGetPrivilegeOfAdminReturnsAdminPrivilege() {
        CurrentUserImpl underTest = new CurrentUserImpl();
        underTest.setCurrentUser("admin", Privilege.Admin);

        assertEquals(Privilege.Admin, underTest.getPrivilege());
    }

    @Test
    void testGetPrivilegeOfUserReturnsUserPrivilege() {
        CurrentUserImpl underTest = new CurrentUserImpl();
        underTest.setCurrentUser("username", Privilege.User);

        assertEquals(Privilege.User, underTest.getPrivilege());
    }
}