package com.epam.training.ticketservice.service.response;

public class SignInResponse {
    private final String token;
    private final String errorMessage;
    private final Boolean successful;

    public SignInResponse(String token, String errorMessage, Boolean successful) {
        this.token = token;
        this.errorMessage = errorMessage;
        this.successful = successful;
    }

    public String getToken() {
        return token;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Boolean isSuccessful() {
        return successful;
    }
}
