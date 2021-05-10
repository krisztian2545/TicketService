package com.epam.training.ticketservice.service.impl;

import com.epam.training.ticketservice.dao.ScreeningDao;
import com.epam.training.ticketservice.domain.theatre.Screening;
import com.epam.training.ticketservice.domain.theatre.ScreeningInfo;
import com.epam.training.ticketservice.exception.AccessDeniedException;
import com.epam.training.ticketservice.exception.NoSuchMovieException;
import com.epam.training.ticketservice.exception.NoSuchRoomException;
import com.epam.training.ticketservice.service.ScreeningService;
import com.epam.training.ticketservice.service.UserValidator;
import com.epam.training.ticketservice.service.response.BasicCommandResponse;
import com.epam.training.ticketservice.service.response.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {

    private ScreeningDao screeningDao;
    private UserValidator userValidator;

    @Autowired
    public ScreeningServiceImpl(ScreeningDao screeningDao, UserValidator userValidator) {
        this.screeningDao = screeningDao;
        this.userValidator = userValidator;
    }

    @Override
    public BasicCommandResponse createScreening(String movieTitle, String roomName, Date startDateAndTime, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return ResponseFactory.errorResponse("You are not privileged to use this command");
        }

        for (Screening sc : getAllScreenings()) {
            long timeDiff = (startDateAndTime.getTime() - sc.getStartDateAndTime().getTime()) / 60000;
            if (roomName.equals(sc.getRoom().getName()) &&
                    timeDiff >= 0 &&
                    timeDiff < sc.getMovie().getLength() + 10) {
                if (timeDiff < sc.getMovie().getLength()) {
                    return ResponseFactory.errorResponse("There is an overlapping screening");
                }
                return ResponseFactory.errorResponse("This would start in the break period after another screening in this room");
            }
        }

        try {
            screeningDao.createScreening(new ScreeningInfo(movieTitle, roomName, startDateAndTime));
        } catch (NoSuchMovieException e) {
            return ResponseFactory.errorResponse("We don't have this movie!");
        } catch (NoSuchRoomException e) {
            return ResponseFactory.errorResponse("There is no such room!");
        }

        return ResponseFactory.successResponse();
    }

    @Override
    public List<Screening> getAllScreenings() {
        return List.copyOf(screeningDao.readAll());
    }

    @Override
    public BasicCommandResponse updateScreening(String filmTitle, String roomName, Date startDateAndTime, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return ResponseFactory.errorResponse("You are not privileged to use this command");
        }

        return ResponseFactory.errorResponse("This feature is unavailable right now!");
    }

    @Override
    public BasicCommandResponse deleteScreening(String movieTitle, String roomName, Date startDateAndTime, String token) {
        try {
            userValidator.authorizeAdmin(token);
        } catch (AccessDeniedException e) {
            return ResponseFactory.errorResponse("You are not privileged to use this command");
        }

        screeningDao.deleteScreening(new ScreeningInfo(movieTitle, roomName, startDateAndTime));

        return ResponseFactory.successResponse();
    }
}
