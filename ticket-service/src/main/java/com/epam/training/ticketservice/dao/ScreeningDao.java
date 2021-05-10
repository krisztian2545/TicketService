package com.epam.training.ticketservice.dao;

import com.epam.training.ticketservice.domain.theatre.Room;
import com.epam.training.ticketservice.domain.theatre.Screening;
import com.epam.training.ticketservice.exception.NoSuchMovieException;
import com.epam.training.ticketservice.exception.NoSuchRoomException;

import java.util.Collection;

public interface ScreeningDao {

    void createScreening(Screening screening) throws NoSuchMovieException, NoSuchRoomException;

    Collection<Screening> readAll();

    void updateScreening(Screening screening);

    void deleteScreening(Screening screening);
}
