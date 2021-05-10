package com.epam.training.ticketservice.clientservice.cli.impl;

import com.epam.training.ticketservice.clientservice.Client;
import com.epam.training.ticketservice.clientservice.cli.RoomCommandsService;
import com.epam.training.ticketservice.domain.theatre.Room;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomCommandsServiceImpl implements RoomCommandsService {

    private RoomService roomService;
    private Client client;

    @Autowired
    public RoomCommandsServiceImpl(RoomService roomService, Client client) {
        this.roomService = roomService;
        this.client = client;
    }

    @Override
    public String createRoom(String name, int rows, int columns) {
        return roomService.createRoom(name, rows, columns, client.getSessionToken()).getMessage();
    }

    @Override
    public String getAllRooms() {
        List<Room> rooms = roomService.getAllRooms();

        if (rooms.isEmpty()) {
            return "There are no rooms at the moment";
        }

        StringBuilder output = new StringBuilder();
        for (Room r : rooms) {
            output.append(String.format("Room %s with %d seats, %d rows and %d columns\n",
                    r.getName(), r.getRows() * r.getColumns(), r.getRows(), r.getColumns()));
        }

        return output.toString();
    }

    @Override
    public String updateRoom(String name, int rows, int columns) {
        return roomService.updateRoom(name, rows, columns, client.getSessionToken()).getMessage();
    }

    @Override
    public String deleteRoom(String name) {
        return roomService.deleteRoom(name, client.getSessionToken()).getMessage();
    }
}
