package com.epam.training.ticketservice.dao.repository;

import com.epam.training.ticketservice.dao.repository.entity.RoomEntity;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<RoomEntity, String> {
}
