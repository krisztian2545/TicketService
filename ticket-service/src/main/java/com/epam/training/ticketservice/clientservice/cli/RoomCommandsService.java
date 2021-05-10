package com.epam.training.ticketservice.clientservice.cli;

public interface RoomCommandsService {

    String createRoom(String name, int rows, int columns);

    String getAllRooms();

    String updateRoom(String name, int rows, int columns);

    String deleteRoom(String name);
}
