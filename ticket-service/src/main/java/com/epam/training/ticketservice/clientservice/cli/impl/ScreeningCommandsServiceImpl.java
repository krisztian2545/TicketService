package com.epam.training.ticketservice.clientservice.cli.impl;

import com.epam.training.ticketservice.clientservice.Client;
import com.epam.training.ticketservice.clientservice.cli.ScreeningCommandsService;
import com.epam.training.ticketservice.domain.theatre.Screening;
import com.epam.training.ticketservice.service.ScreeningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ScreeningCommandsServiceImpl implements ScreeningCommandsService {

    private ScreeningService screeningService;
    Client client;

    @Autowired
    public ScreeningCommandsServiceImpl(ScreeningService screeningService, Client client) {
        this.screeningService = screeningService;
        this.client = client;
    }

    @Override
    public String createScreening(String movieTitle, String roomName, Date startDateAndTime) {
        return screeningService.createScreening(movieTitle, roomName, startDateAndTime, client.getSessionToken())
                .getMessage();
    }

    @Override
    public String getAllScreenings() {
        List<Screening> screenings = screeningService.getAllScreenings();

        if (screenings.isEmpty()) {
            return "There are no screenings";
        }

        StringBuilder output = new StringBuilder();
        for (Screening sc : screenings) {
            output.append(String.format("%s (%s, %d minutes), screened in room %s, at %s\n",
                    sc.getMovie().getTitle(), sc.getMovie().getGenre(), sc.getMovie().getLength(),
                    sc.getRoom().getName(),
                    (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(sc.getStartDateAndTime()) ));
        }

        return output.toString();
    }

    @Override
    public String updateScreening(String movieTitle, String roomName, Date startDateAndTime) {
        return screeningService.updateScreening(movieTitle, roomName, startDateAndTime, client.getSessionToken())
                .getMessage();
    }

    @Override
    public String deleteScreening(String movieTitle, String roomName, Date startDateAndTime) {
        return screeningService.deleteScreening(movieTitle, roomName, startDateAndTime, client.getSessionToken())
                .getMessage();
    }
}
