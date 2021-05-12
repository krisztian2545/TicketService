package com.epam.training.ticketservice.domain.theatre;

import java.util.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScreeningInfo that = (ScreeningInfo) o;
        return movieTitle.equals(that.movieTitle)
                && roomName.equals(that.roomName)
                && startDateAndTime.equals(that.startDateAndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieTitle, roomName, startDateAndTime);
    }
}
