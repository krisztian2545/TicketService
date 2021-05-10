package com.epam.training.ticketservice.dao.repository.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(ScreeningId.class)
@Table(name = "screening")
public class ScreeningEntity {

    @Id
    @Column(name = "film_title")
    private String filmTitle;

    @Id
    @Column(name = "room_name")
    private String roomName;

    @Id
    @Column(name = "start_date_and_time")
    private Timestamp startDateAndTime;

    public ScreeningEntity() {
    }

    public ScreeningEntity(String filmTitle, String roomName, Timestamp startDateAndTime) {
        this.filmTitle = filmTitle;
        this.roomName = roomName;
        this.startDateAndTime = startDateAndTime;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public String getRoomName() {
        return roomName;
    }

    public Timestamp getStartDateAndTime() {
        return startDateAndTime;
    }
}