package com.epam.training.ticketservice.dao.impl;

import com.epam.training.ticketservice.dao.RoomDao;
import com.epam.training.ticketservice.dao.repository.RoomRepository;
import com.epam.training.ticketservice.dao.repository.entity.RoomEntity;
import com.epam.training.ticketservice.domain.theatre.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RoomDaoImpl implements RoomDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(RoomDaoImpl.class);

    private RoomRepository roomRepository;

    public RoomDaoImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void createRoom(Room room) {
        try {
            roomRepository.save(new RoomEntity(
                    room.getName(),
                    room.getRows(),
                    room.getColumns()
            ));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Collection<Room> readAll() {
        return StreamSupport.stream(roomRepository.findAll().spliterator(),false)
                .map(entity -> new Room(
                        entity.getName(),
                        entity.getRows(),
                        entity.getColumns()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Room room) {
        Optional<RoomEntity> roomEntity = roomRepository.findById(room.getName());

        roomEntity.ifPresent(entity -> {
            entity.setRows(room.getRows());
            entity.setColumns(room.getColumns());
            roomRepository.save(entity);
        });
    }

    @Override
    public void deleteRoom(String name) {
        Optional<RoomEntity> roomEntity = roomRepository.findById(name);

        roomEntity.ifPresent(entity -> roomRepository.delete(entity));
    }
}
