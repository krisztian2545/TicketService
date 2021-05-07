package com.epam.training.ticketservice.clientservice.cli;

public interface AdminCommandsService {

    String signIn(String username, String password);

    String signOut();

}
