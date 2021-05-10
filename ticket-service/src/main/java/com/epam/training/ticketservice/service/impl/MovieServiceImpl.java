package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.dao.MovieDao;
import com.epam.training.ticketservice.domain.theatre.Movie;
import com.epam.training.ticketservice.exception.AccessDeniedException;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.UserValidator;
import com.epam.training.ticketservice.service.response.BasicResponse;
import com.epam.training.ticketservice.service.response.ResponseFactory;
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
    public BasicResponse createMovie(String title, String genre, int length, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return ResponseFactory.errorResponse("You are not privileged to use this command");
        }

        movieDao.createMovie(new Movie(title, genre, length));

        return ResponseFactory.successResponse();
    }

    @Override
    public List<Movie> getAllMovies() {
        return List.copyOf(movieDao.readAll());
    }

    @Override
    public BasicResponse updateMovie(String title, String genre, int length, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return ResponseFactory.errorResponse("You are not privileged to use this command");
        }

        movieDao.updateMovie(new Movie(title, genre, length));

        return ResponseFactory.successResponse();
    }

    @Override
    public BasicResponse deleteMovie(String title, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return ResponseFactory.errorResponse("You are not privileged to use this command");
        }

        //TODO prevent deleting if there is a screening with this film

        movieDao.deleteMovie(title);
        return ResponseFactory.successResponse();
    }


}
