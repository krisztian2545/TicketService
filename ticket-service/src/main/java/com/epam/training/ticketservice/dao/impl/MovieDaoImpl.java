package com.epam.training.ticketservice.dao.impl;

import com.epam.training.ticketservice.dao.MovieDao;
import com.epam.training.ticketservice.dao.repository.MovieRepository;
import com.epam.training.ticketservice.dao.repository.entity.MovieEntity;
import com.epam.training.ticketservice.domain.theatre.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieDaoImpl implements MovieDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(MovieDaoImpl.class);

    private MovieRepository movieRepository;

    @Autowired
    public MovieDaoImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void createMovie(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setGenre(movie.getGenre());
        movieEntity.setLength(movie.getLength());

        try {
            movieRepository.save(movieEntity);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public Collection<Movie> readAll() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(),false)
                .map(entity -> new Movie(
                        entity.getTitle(),
                        entity.getGenre(),
                        entity.getLength()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Movie movie) {

    }

    @Override
    public void deleteMovie(String title) {
        Optional<MovieEntity> movieEntity = movieRepository.findById(title);

        movieEntity.ifPresent(entity -> movieRepository.delete(entity));
    }
}
