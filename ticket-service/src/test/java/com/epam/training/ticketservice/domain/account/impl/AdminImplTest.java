package com.epam.training.ticketservice.domain.account.impl;

import com.epam.training.ticketservice.domain.account.Privilege;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminImplTest {

    @Test
    public void testGetUsernameReturnsCorrectUsername() {
        AdminImpl admin = new AdminImpl("admin");

        assertEquals("admin", admin.getUsername());
    }

    @Test
    public void testGetPrivilegeReturnsCorrectPrivilege() {
        AdminImpl admin = new AdminImpl("admin");

        assertEquals(Privilege.Admin, admin.getPrivilege());
    }

}