package com.epam.training.ticketservice.clientservice.cli.impl;

import com.epam.training.ticketservice.clientservice.Client;
import com.epam.training.ticketservice.domain.theatre.Room;
import com.epam.training.ticketservice.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class RoomCommandsServiceImplTest {

    private RoomService roomService;
    private Client client;
    private RoomCommandsServiceImpl underTest;

    @BeforeEach
    void setUp() {
        roomService = Mockito.mock(RoomService.class);
        client = Mockito.mock(Client.class);

        underTest = new RoomCommandsServiceImpl(roomService, client);
    }

    @Test
    void createRoom() {
    }

    @Test
    void testGetAllRoomsReturnsExactMessageWhenThereAreNoRooms() {
        given(roomService.getAllRooms()).willReturn(List.of());

        String result = underTest.getAllRooms();

        assertEquals("There are no rooms at the moment", result);
    }

    @Test
    void testGetAllRoomsReturnsFormattedRoomsWhenThereAreRooms() {
        given(roomService.getAllRooms()).willReturn(List.of(
                new Room("garázs", 2, 5)
        ));

        String result = underTest.getAllRooms();

        assertEquals("Room garázs with 10 seats, 2 rows and 5 columns\n", result);
    }

    @Test
    void updateRoom() {
    }

    @Test
    void deleteRoom() {
    }
}