package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.domain.theatre.Movie;

import java.util.List;

public interface MovieService {

    String createMovie(String title, String genre, int length, String token);

    List<Movie> getAllMovies();

    String updateMovie(String title, String genre, int length, String token);

    String deleteMovie(String title, String token);

}
