package com.epam.training.ticketservice.clientservice.cli.impl;

import com.epam.training.ticketservice.clientservice.Client;
import com.epam.training.ticketservice.clientservice.cli.MovieCommandsService;
import com.epam.training.ticketservice.domain.theatre.Movie;
import com.epam.training.ticketservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieCommandsServiceImpl implements MovieCommandsService {

    private MovieService movieService;
    private Client client;

    @Autowired
    public MovieCommandsServiceImpl(MovieService movieService, Client client) {
        this.movieService = movieService;
        this.client = client;
    }

    @Override
    public String createMovie(String title, String genre, int length) {
        return movieService.createMovie(title, genre, length, client.getSessionToken());
    }

    @Override
    public String getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();

        if (movies.isEmpty()) {
            return "There are no movies at the moment";
        }

        StringBuilder output = new StringBuilder();
        for (Movie m : movies) {
            output.append(String.format("%s (%s, %d minutes)\n",
                    m.getTitle(), m.getGenre(), m.getLength()));
        }

        return output.toString(); // TODO
    }

    @Override
    public String updateMovie(String title, String genre, int length) {
        return null;
    }

    @Override
    public String deleteMovie(String title) {
        return movieService.deleteMovie(title, client.getSessionToken());
    }
}
