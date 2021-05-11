package com.epam.training.ticketservice.domain.account.impl;

import com.epam.training.ticketservice.domain.account.Privilege;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {



    @Test
    public void testGetUsernameReturnsCorrectUsername() {
        User user = new User("username");

        assertEquals("username", user.getUsername());
    }

    @Test
    public void testGetPrivilegeReturnsCorrectPrivilege() {
        User user = new User("username");

        assertEquals(Privilege.User, user.getPrivilege());
    }

}