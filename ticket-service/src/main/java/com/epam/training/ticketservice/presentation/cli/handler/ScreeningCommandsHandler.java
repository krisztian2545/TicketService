package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.clientservice.cli.ScreeningCommandsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@ShellComponent
public class ScreeningCommandsHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(ScreeningCommandsHandler.class);
    private final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    private ScreeningCommandsService screeningCommandsService;

    @Autowired
    public ScreeningCommandsHandler(ScreeningCommandsService screeningCommandsService) {
        this.screeningCommandsService = screeningCommandsService;
    }

    @ShellMethod(value = "create screening <film címe> <terem neve> <vetítés kezdetének dátuma és ideje, " +
            "YYYY-MM-dd HH:mm formátumban>", key = "create screening")
    public String createScreening(String movieTitle, String roomName, String startDateAndTime) {
        try {
            LOGGER.error(new SimpleDateFormat(DATE_FORMAT).parse(startDateAndTime).toString() + DATE_FORMAT);

            return screeningCommandsService.createScreening(movieTitle, roomName,
                    new SimpleDateFormat(DATE_FORMAT).parse(startDateAndTime));
        } catch (ParseException e) {
            return "Incorrect date and time!";
        }
    }

    @ShellMethod(value = "Lists all the screenings", key = "list screenings")
    public String listScreenings() {
        return screeningCommandsService.getAllScreenings();
    }

    @ShellMethod(value = "delete screening <film címe> <terem neve> <vetítés kezdetének dátuma és ideje, " +
            "YYYY-MM-dd HH:mm formátumban>", key = "delete screening")
    public String deleteScreening(String movieTitle, String roomName, String startDateAndTime) {
        try {
            return screeningCommandsService.deleteScreening(movieTitle, roomName,
                    new SimpleDateFormat(DATE_FORMAT).parse(startDateAndTime));
        } catch (ParseException e) {
            return "Incorrect date and time!";
        }
    }
}
