package com.epam.training.ticketservice.dao;

import com.epam.training.ticketservice.domain.theatre.Screening;
import com.epam.training.ticketservice.domain.theatre.ScreeningInfo;
import com.epam.training.ticketservice.exception.NoSuchMovieException;
import com.epam.training.ticketservice.exception.NoSuchRoomException;

import java.util.Collection;

public interface ScreeningDao {

    void createScreening(ScreeningInfo screening) throws NoSuchMovieException, NoSuchRoomException;

    Collection<Screening> readAll();

    void updateScreening(ScreeningInfo screening);

    void deleteScreening(ScreeningInfo screening);
}
