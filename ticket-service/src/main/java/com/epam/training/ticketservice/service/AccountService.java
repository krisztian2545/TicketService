package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.service.response.BasicCommandResponse;
import com.epam.training.ticketservice.service.response.SignInResponse;

public interface AccountService {

    BasicCommandResponse signInAsAdmin(String username, String password);

    BasicCommandResponse signInAsUser(String username, String password);

    BasicCommandResponse signOut();

    BasicCommandResponse describeAccount();
}
