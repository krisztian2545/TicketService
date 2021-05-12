package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.dao.ScreeningDao;
import com.epam.training.ticketservice.domain.theatre.Movie;
import com.epam.training.ticketservice.domain.theatre.Room;
import com.epam.training.ticketservice.domain.theatre.Screening;
import com.epam.training.ticketservice.domain.theatre.ScreeningInfo;
import com.epam.training.ticketservice.exception.AccessDeniedException;
import com.epam.training.ticketservice.exception.NoSuchMovieException;
import com.epam.training.ticketservice.exception.NoSuchRoomException;
import com.epam.training.ticketservice.service.UserValidator;
import com.epam.training.ticketservice.service.response.BasicCommandResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

class ScreeningServiceImplTest {

    private ScreeningDao screeningDao;
    private UserValidator userValidator;
    private ScreeningServiceImpl underTest;

    @BeforeEach
    void setUp() {
        screeningDao = Mockito.mock(ScreeningDao.class);
        userValidator = Mockito.mock(UserValidator.class);

        underTest = new ScreeningServiceImpl(screeningDao, userValidator);
    }

    @Test
    void testCreateScreeningReturnsErrorMessageInBasicCommandResponseWhenTheUserISUnauthorized() throws AccessDeniedException {
        doThrow(AccessDeniedException.class).when(userValidator).authorizeAdmin("invalid token");
        BasicCommandResponse result = underTest.createScreening("doesn't", "matter", new Date(), "invalid token");

        assertFalse(result.isSuccessful());
    }

    @Test
    void testCreateScreeningReturnsSuccessfulBasicCommandResponseWhenRunsSuccessfully() {
        BasicCommandResponse result = underTest.createScreening("doesn't", "matter", new Date(), "token");

        assertTrue(result.isSuccessful());
    }

    @Test
    void testCreateScreeningReturnsErrorMessageInBasicCommandResponseWhenThereIsOverlappingScreening() {
        given(screeningDao.readAll()).willReturn(List.of(new Screening(
                new Movie("zsír", "dráma", 600),
                new Room("garázs", 2, 5),
                new Date(2021, 3, 14, 10, 0)
        )));

        BasicCommandResponse result = underTest.createScreening("zsír", "garázs",
                new Date(2021, 3, 14, 12, 0), "token");

        assertTrue(!result.isSuccessful() && result.getMessage().equals("There is an overlapping screening"));
    }

    @Test
    void testCreateScreeningReturnsErrorMessageInBasicCommandResponseWhenSameMovieAndRoomButDifferentTime() {
        given(screeningDao.readAll()).willReturn(List.of(new Screening(
                new Movie("zsír", "dráma", 60),
                new Room("garázs", 2, 5),
                new Date(2021, 3, 14, 10, 0)
        )));

        BasicCommandResponse result = underTest.createScreening("zsír", "garázs",
                new Date(2021, 3, 14, 12, 0), "token");

        assertTrue(result.isSuccessful());
    }

    @Test
    void testCreateScreeningReturnsErrorMessageInBasicCommandResponseWhenThereIsOverlappingScreeningsBreak() {
        given(screeningDao.readAll()).willReturn(List.of(new Screening(
                new Movie("zsír", "dráma", 60),
                new Room("garázs", 2, 5),
                new Date(2021, 3, 14, 10, 0)
        )));

        BasicCommandResponse result = underTest.createScreening("zsír", "garázs",
                new Date(2021, 3, 14, 11, 9), "token");

        assertTrue(!result.isSuccessful() && result.getMessage()
                .equals("This would start in the break period after another screening in this room"));
    }

    @Test
    void testCreateScreeningReturnsErrorMessageInBasicCommandResponseWhenThereIsNoSuchMovie() throws NoSuchMovieException, NoSuchRoomException {
        ScreeningInfo scInfo = new ScreeningInfo("nincs ilyen film", "szoba", new Date(2021, 3, 14, 11, 9));
        doThrow(NoSuchMovieException.class).when(screeningDao).createScreening(scInfo);

        BasicCommandResponse result = underTest.createScreening(scInfo.getMovieTitle(), scInfo.getRoomName(),
                scInfo.getStartDateAndTime(), "token");

        assertTrue(!result.isSuccessful() && result.getMessage()
                .equals("We don't have this movie!"));
    }

    @Test
    void testCreateScreeningReturnsErrorMessageInBasicCommandResponseWhenThereIsNoSuchRoom() throws NoSuchMovieException, NoSuchRoomException {
        ScreeningInfo scInfo = new ScreeningInfo("zsír", "szoba", new Date(2021, 3, 14, 11, 9));
        doThrow(NoSuchRoomException.class).when(screeningDao).createScreening(scInfo);

        BasicCommandResponse result = underTest.createScreening(scInfo.getMovieTitle(), scInfo.getRoomName(),
                scInfo.getStartDateAndTime(), "token");

        assertTrue(!result.isSuccessful() && result.getMessage()
                .equals("There is no such room!"));
    }

    @Test
    void getAllScreenings() {
    }

    @Test
    void testUpdateScreeningReturnsErrorMessageInBasicCommandResponseWhenTheUserISUnauthorized() throws AccessDeniedException {
        doThrow(AccessDeniedException.class).when(userValidator).authorizeAdmin("invalid token");
        BasicCommandResponse result = underTest.updateScreening("doesn't", "matter", new Date(), "invalid token");

        assertFalse(result.isSuccessful());
    }

    @Test
    void testDeleteScreeningReturnsErrorMessageInBasicCommandResponseWhenTheUserISUnauthorized() throws AccessDeniedException {
        doThrow(AccessDeniedException.class).when(userValidator).authorizeAdmin("invalid token");
        BasicCommandResponse result = underTest.deleteScreening("doesn't", "matter", new Date(), "invalid token");

        assertFalse(result.isSuccessful());
    }

    @Test
    void testDeleteScreeningReturnsSuccessfulBasicCommandResponseWhenRunsSuccessfully() {
        BasicCommandResponse result = underTest.deleteScreening("doesn't", "matter", new Date(), "token");

        assertTrue(result.isSuccessful());
    }
}