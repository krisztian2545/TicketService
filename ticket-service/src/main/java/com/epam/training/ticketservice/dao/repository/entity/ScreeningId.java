package com.epam.training.ticketservice.dao.repository.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ScreeningId implements Serializable {

    private String filmTitle;
    private String roomName;
    private Timestamp startDateAndTime;

    public ScreeningId() {
    }

    public ScreeningId(String filmTitle, String roomName, Timestamp startDateAndTime) {
        this.filmTitle = filmTitle;
        this.roomName = roomName;
        this.startDateAndTime = startDateAndTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreeningId that = (ScreeningId) o;
        return filmTitle.equals(that.filmTitle) && roomName.equals(that.roomName)
                && startDateAndTime.equals(that.startDateAndTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmTitle, roomName, startDateAndTime);
    }
}