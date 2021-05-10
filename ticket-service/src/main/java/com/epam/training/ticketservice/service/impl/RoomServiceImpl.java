package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.dao.RoomDao;
import com.epam.training.ticketservice.domain.theatre.Room;
import com.epam.training.ticketservice.exception.AccessDeniedException;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.UserValidator;
import com.epam.training.ticketservice.service.response.BasicCommandResponse;
import com.epam.training.ticketservice.service.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private UserValidator userValidator;
    private RoomDao roomDao;

    @Autowired
    public RoomServiceImpl(UserValidator userValidator, RoomDao roomDao) {
        this.userValidator = userValidator;
        this.roomDao = roomDao;
    }

    @Override
    public BasicCommandResponse createRoom(String name, int rows, int columns, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return ResponseFactory.errorResponse("You are not privileged to use this command");
        }

        roomDao.createRoom(new Room(name, rows, columns));

        return ResponseFactory.successResponse();
    }

    @Override
    public List<Room> getAllRooms() {
        return List.copyOf(roomDao.readAll());
    }

    @Override
    public BasicCommandResponse updateRoom(String name, int rows, int columns, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return ResponseFactory.errorResponse("You are not privileged to use this command");
        }

        roomDao.updateRoom(new Room(name, rows, columns));

        return ResponseFactory.successResponse();
    }

    @Override
    public BasicCommandResponse deleteRoom(String name, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return ResponseFactory.errorResponse("You are not privileged to use this command");
        }

        //TODO prevent deleting if there is a screening in this room

        roomDao.deleteRoom(name);

        return ResponseFactory.successResponse();
    }
}
