package com.epam.training.ticketservice.clientservice.cli;

import com.epam.training.ticketservice.domain.theatre.Screening;
import com.epam.training.ticketservice.service.response.BasicCommandResponse;

import java.util.Date;
import java.util.List;

public interface ScreeningCommandsService {

    String createScreening(String movieTitle, String roomName, Date startDateAndTime);

    String getAllScreenings();

    String updateScreening(String movieTitle, String roomName, Date startDateAndTime);

    String deleteScreening(String movieTitle, String roomName, Date startDateAndTime);
}
