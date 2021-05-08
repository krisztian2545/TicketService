package com.epam.training.ticketservice.dao.repository;

import com.epam.training.ticketservice.dao.repository.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieEntity, String> {

}
