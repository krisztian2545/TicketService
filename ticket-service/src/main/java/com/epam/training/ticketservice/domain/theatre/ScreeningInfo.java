package com.epam.training.ticketservice.domain.theatre;

import java.util.Date;

public class ScreeningInfo {

    private String movieTitle;
    private String roomName;
    private Date startDateAndTime;

    public ScreeningInfo(String movieTitle, String roomName, Date startDateAndTime) {
        this.movieTitle = movieTitle;
        this.roomName = roomName;
        this.startDateAndTime = startDateAndTime;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getRoomName() {
        return roomName;
    }

    public Date getStartDateAndTime() {
        return startDateAndTime;
    }
}
