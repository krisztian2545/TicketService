package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.dao.MovieDao;
import com.epam.training.ticketservice.exception.AccessDeniedException;
import com.epam.training.ticketservice.service.UserValidator;
import com.epam.training.ticketservice.service.response.BasicCommandResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

class MovieServiceImplTest {

    private MovieDao movieDao;
    private UserValidator userValidator;
    MovieServiceImpl underTest;

    @BeforeEach
    void setUp() {
        movieDao = Mockito.mock(MovieDao.class);
        userValidator = Mockito.mock(UserValidator.class);
        underTest = new MovieServiceImpl(movieDao, userValidator);
    }

    @Test
    void testCreateMovieReturnsSuccessfulBasicCommandResponse() {
        BasicCommandResponse result = underTest.createMovie("doesn't", "matter", 99, "token");

        assertTrue(result.isSuccessful());
    }

    @Test
    void testCreateMovieReturnsErrorMessageInBasicCommandResponseWhenTheUserISUnauthorized() throws AccessDeniedException {
        doThrow(AccessDeniedException.class).when(userValidator).authorizeAdmin("invalid token");
        BasicCommandResponse result = underTest.createMovie("doesn't", "matter", 99, "invalid token");

        assertFalse(result.isSuccessful());
    }

    @Test
    void testGetAllMovies() {
    }

    @Test
    void testUpdateMovie() {
    }

    @Test
    void testDeleteMovie() {
    }
}