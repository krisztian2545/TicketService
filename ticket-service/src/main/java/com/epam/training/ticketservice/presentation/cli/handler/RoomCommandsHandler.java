package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.clientservice.cli.RoomCommandsService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class RoomCommandsHandler {

    private RoomCommandsService roomCommandsService;

    public RoomCommandsHandler(RoomCommandsService roomCommandsService) {
        this.roomCommandsService = roomCommandsService;
    }

    @ShellMethod(value = "create room <terem neve> <széksorok száma> <szék oszlopok száma>", key = "create room")
    public String createRoom(String name, int rows, int columns) {
        return roomCommandsService.createRoom(name, rows, columns);
    }

    @ShellMethod(value = "Lists all the rooms", key = "list rooms")
    public String listRooms() {
        return roomCommandsService.getAllRooms();
    }

    @ShellMethod(value = "update room <terem neve> <széksorok száma> <szék oszlopok száma>", key = "update room")
    public String updateRoom(String name, int rows, int columns) {
        return roomCommandsService.updateRoom(name, rows, columns);
    }

    @ShellMethod(value = "delete room <terem neve>", key = "delete room")
    public String deleteRoom(String name) {
        return roomCommandsService.deleteRoom(name);
    }
}
