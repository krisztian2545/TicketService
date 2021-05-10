package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.domain.theatre.Movie;
import com.epam.training.ticketservice.service.response.BasicCommandResponse;

import java.util.List;

public interface MovieService {

    BasicCommandResponse createMovie(String title, String genre, int length, String token);

    List<Movie> getAllMovies();

    BasicCommandResponse updateMovie(String title, String genre, int length, String token);

    BasicCommandResponse deleteMovie(String title, String token);

}
