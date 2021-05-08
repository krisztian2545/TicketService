package com.epam.training.ticketservice.clientservice.cli;

public interface MovieCommandsService {

    String createMovie(String title, String genre, int length);

    String getAllMovies();

    String updateMovie(String title, String genre, int length);

    String deleteMovie(String title);
}
