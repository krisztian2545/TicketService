package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.dao.MovieDao;
import com.epam.training.ticketservice.domain.theatre.Movie;
import com.epam.training.ticketservice.exception.AccessDeniedException;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieDao movieDao;
    private UserValidator userValidator;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao, UserValidator userValidator) {
        this.movieDao = movieDao;
        this.userValidator = userValidator;
    }


    @Override
    public String createMovie(String title, String genre, int length, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return "You are not privileged to use this command";
        }

        movieDao.createMovie(new Movie(title, genre, length));

        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        return List.copyOf(movieDao.readAll());
    }

    @Override
    public String updateMovie(String title, String genre, int length, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return "You are not privileged to use this command";
        }

        movieDao.update(new Movie(title, genre, length));

        return null;
    }

    @Override
    public String deleteMovie(String title, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return "You are not privileged to use this command";
        }

        movieDao.deleteMovie(title);
        return null;
    }


}
