package com.epam.training.ticketservice.clientservice.cli.impl;

import com.epam.training.ticketservice.clientservice.Client;
import com.epam.training.ticketservice.domain.theatre.Movie;
import com.epam.training.ticketservice.domain.theatre.Room;
import com.epam.training.ticketservice.domain.theatre.Screening;
import com.epam.training.ticketservice.service.ScreeningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class ScreeningCommandsServiceImplTest {

    private ScreeningService screeningService;
    private Client client;
    private ScreeningCommandsServiceImpl underTest;

    @BeforeEach
    void setUp() {
        screeningService = Mockito.mock(ScreeningService.class);
        client = Mockito.mock(Client.class);

        underTest = new ScreeningCommandsServiceImpl(screeningService, client);
    }

    @Test
    void createScreening() {
    }

    @Test
    void testGetAllScreeningsReturnsExactMessageWhenThereAreNoScreenings() {
        given(screeningService.getAllScreenings()).willReturn(List.of());

        String result = underTest.getAllScreenings();

        assertEquals("There are no screenings", result);
    }

    @Test
    void testGetAllScreeningsReturnsFormattedScreeningsWhenThereAreScreenings() {
        given(screeningService.getAllScreenings()).willReturn(List.of(new Screening(
                new Movie("zsír", "dráma", 600),
                new Room("garázs", 2, 5),
                new Date(121, 3, 14, 10, 0)
        )));

        String result = underTest.getAllScreenings();

        assertEquals("zsír (dráma, 600 minutes), screened in room garázs, at 2021-04-14 10:00\n", result);
    }

    @Test
    void updateScreening() {
    }

    @Test
    void deleteScreening() {
    }
}