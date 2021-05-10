package com.epam.training.ticketservice.service.response;

public class ResponseFactory {

    public static SignInResponse successfulSignInResponse(String token) {
        return new SignInResponse(token, "", true);
    }

    public static SignInResponse unSuccessfulSignInResponse(String errorMessage) {
        return new SignInResponse("", errorMessage, false);
    }

    public static BasicResponse successResponse() {
        return new BasicResponse("", true);
    }

    public static BasicResponse successResponse(String message) {
        return new BasicResponse(message, true);
    }

    public static BasicResponse errorResponse(String message) {
        return new BasicResponse(message, false);
    }
}
