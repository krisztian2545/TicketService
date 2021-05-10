package com.epam.training.ticketservice.service.response;

public class BasicResponse implements BasicCommandResponse {

    private final String message;
    private final Boolean successful;

    public BasicResponse(String message, Boolean successful) {
        this.message = message;
        this.successful = successful;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean isSuccessful() {
        return successful;
    }
}
