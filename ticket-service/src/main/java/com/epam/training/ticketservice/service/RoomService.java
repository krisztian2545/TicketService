package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.domain.theatre.Room;
import com.epam.training.ticketservice.service.response.BasicCommandResponse;

import java.util.List;

public interface RoomService {

    BasicCommandResponse createRoom(String name, int rows, int columns, String token);

    List<Room> getAllRooms();

    BasicCommandResponse updateRoom(String name, int rows, int columns, String token);

    BasicCommandResponse deleteRoom(String name, String token);
}
