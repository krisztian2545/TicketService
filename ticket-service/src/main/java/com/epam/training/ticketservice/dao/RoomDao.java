package com.epam.training.ticketservice.dao;

import com.epam.training.ticketservice.domain.theatre.Room;

import java.util.Collection;

public interface RoomDao {

    void createRoom(Room room);

    Collection<Room> readAll();

    void update(Room room);

    void deleteRoom(String name);
}
