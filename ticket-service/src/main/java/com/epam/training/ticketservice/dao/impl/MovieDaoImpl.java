package com.epam.training.ticketservice.dao.impl;

import com.epam.training.ticketservice.dao.MovieDao;
import com.epam.training.ticketservice.dao.repository.MovieRepository;
import com.epam.training.ticketservice.domain.theatre.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class MovieDaoImpl implements MovieDao {

    private MovieRepository movieRepository;

    @Autowired
    public MovieDaoImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void createMovie(Movie movie) {

    }

    @Override
    public Collection<Movie> readAll() {
        return null;
    }

    @Override
    public void update(Movie movie) {

    }

    @Override
    public void deleteMovie(Movie movie) {

    }
}
