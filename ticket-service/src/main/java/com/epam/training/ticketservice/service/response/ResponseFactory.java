package com.epam.training.ticketservice.service.response;

public class ResponseFactory {

    public static SignInResponse successfulSignInResponse(String token) {
        return new SignInResponse(token, "", true);
    }

    public static SignInResponse unSuccessfulSignInResponse(String errorMessage) {
        return new SignInResponse("", errorMessage, false);
    }
}
