package com.epam.training.ticketservice.dao.impl;

import com.epam.training.ticketservice.dao.ScreeningDao;
import com.epam.training.ticketservice.dao.repository.MovieRepository;
import com.epam.training.ticketservice.dao.repository.RoomRepository;
import com.epam.training.ticketservice.dao.repository.ScreeningRepository;
import com.epam.training.ticketservice.dao.repository.entity.MovieEntity;
import com.epam.training.ticketservice.dao.repository.entity.RoomEntity;
import com.epam.training.ticketservice.dao.repository.entity.ScreeningEntity;
import com.epam.training.ticketservice.dao.repository.entity.ScreeningId;
import com.epam.training.ticketservice.domain.theatre.Movie;
import com.epam.training.ticketservice.domain.theatre.Room;
import com.epam.training.ticketservice.domain.theatre.Screening;
import com.epam.training.ticketservice.domain.theatre.ScreeningInfo;
import com.epam.training.ticketservice.exception.NoSuchMovieException;
import com.epam.training.ticketservice.exception.NoSuchRoomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ScreeningDaoImpl implements ScreeningDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(ScreeningDaoImpl.class);

    private ScreeningRepository screeningRepository;
    private MovieRepository movieRepository;
    private RoomRepository roomRepository;

    @Autowired
    public ScreeningDaoImpl(ScreeningRepository screeningRepository, MovieRepository movieRepository, RoomRepository roomRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void createScreening(ScreeningInfo screening) throws NoSuchMovieException, NoSuchRoomException {
        if (!movieRepository.existsById(screening.getMovieTitle())) {
            throw new NoSuchMovieException();
        }

        if (!roomRepository.existsById(screening.getRoomName())) {
            throw new NoSuchRoomException();
        }

        try {
            screeningRepository.save(new ScreeningEntity(
                    screening.getMovieTitle(),
                    screening.getRoomName(),
                    new Timestamp(screening.getStartDateAndTime().getTime())
            ));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Collection<Screening> readAll() {
        return StreamSupport.stream(screeningRepository.findAll().spliterator(), false)
                .map(entity -> {
                        MovieEntity movieEntity = movieRepository.findById(entity.getFilmTitle()).get();
                        RoomEntity roomEntity = roomRepository.findById(entity.getRoomName()).get();

                        return new Screening(
                                new Movie(
                                        movieEntity.getTitle(),
                                        movieEntity.getGenre(),
                                        movieEntity.getLength()
                                ),
                                new Room(
                                        roomEntity.getName(),
                                        roomEntity.getRows(),
                                        roomEntity.getColumns()
                                ),
                                new Date(entity.getStartDateAndTime().getTime())
                        );
                        }
                ).collect(Collectors.toList());
    }

    @Override
    public void updateScreening(ScreeningInfo screening) {
        LOGGER.error("This feature is unavailable right now!");
    }

    @Override
    public void deleteScreening(ScreeningInfo screening) {
        Optional<ScreeningEntity> screeningEntity = screeningRepository.findById(new ScreeningId(
                screening.getMovieTitle(),
                screening.getRoomName(),
                new Timestamp(screening.getStartDateAndTime().getTime())
        ));

        screeningEntity.ifPresent(entity -> screeningRepository.delete(entity));
    }
}
