package com.epam.training.ticketservice.domain.theatre;

import java.util.Date;

public class Screening {

    private Movie movie;

    private Room room;

    private Date startDateAndTime;

    public Screening(Movie movie, Room room, Date startDateAndTime) {
        this.movie = movie;
        this.room = room;
        this.startDateAndTime = startDateAndTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public Room getRoom() {
        return room;
    }

    public Date getStartDateAndTime() {
        return startDateAndTime;
    }
}
