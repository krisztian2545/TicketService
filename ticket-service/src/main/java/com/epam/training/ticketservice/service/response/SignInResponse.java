package com.epam.training.ticketservice.service.response;

public class SignInResponse extends BasicResponse {
    private final String token;

    public SignInResponse(String token, String errorMessage, Boolean successful) {
        super(errorMessage, successful);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getErrorMessage() {
        return getMessage();
    }
}
