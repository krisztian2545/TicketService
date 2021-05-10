package com.epam.training.ticketservice.dao;

import com.epam.training.ticketservice.domain.theatre.Movie;

import java.util.Collection;

public interface MovieDao {

    void createMovie(Movie movie);

    Collection<Movie> readAll();

    void updateMovie(Movie movie);

    void deleteMovie(String title);
}
