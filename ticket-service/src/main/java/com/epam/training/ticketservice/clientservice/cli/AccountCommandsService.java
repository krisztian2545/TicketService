package com.epam.training.ticketservice.clientservice.cli;

public interface AccountCommandsService {

    String signIn(String username, String password);

    String signOut();

    String describeAccount();

}
