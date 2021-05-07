package com.epam.training.ticketservice.clientservice.cli;

import org.springframework.stereotype.Component;

@Component
public class Client {
    
    private String sessionToken;

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
