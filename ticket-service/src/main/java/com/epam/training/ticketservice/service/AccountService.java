package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.service.response.SignInResponse;

public interface AccountService {

    SignInResponse signInAsAdmin(String username, String password);

    String signInAsUser(String username, String password);

    String signOut();
}
