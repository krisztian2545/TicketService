package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.clientservice.cli.MovieCommandsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MovieCommandsHandler {

    private MovieCommandsService movieCommandsService;

    @Autowired
    public MovieCommandsHandler(MovieCommandsService movieCommandsService) {
        this.movieCommandsService = movieCommandsService;
    }

    @ShellMethod(value = "create movie <film címe> <műfaj> <vetítés hossza percben>", key = "create movie")
    public String createMovie(String title, String genre, int length) {
        return movieCommandsService.createMovie(title, genre, length);
    }

    @ShellMethod(value = "Lists all the movies", key = "list movies")
    public String listMovies() {
        return movieCommandsService.getAllMovies();
    }

    @ShellMethod(value = "delete movie <film címe>", key = "delete movie")
    public String deleteMovie(String title) {
        return movieCommandsService.deleteMovie(title);
    }

}
