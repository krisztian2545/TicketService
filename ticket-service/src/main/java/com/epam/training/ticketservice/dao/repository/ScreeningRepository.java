package com.epam.training.ticketservice.dao.repository;

import com.epam.training.ticketservice.dao.repository.entity.ScreeningEntity;
import com.epam.training.ticketservice.dao.repository.entity.ScreeningId;
import org.springframework.data.repository.CrudRepository;

public interface ScreeningRepository extends CrudRepository<ScreeningEntity, ScreeningId> {
}
