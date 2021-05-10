package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.domain.theatre.Screening;
import com.epam.training.ticketservice.service.response.BasicCommandResponse;

import java.util.Date;
import java.util.List;

public interface ScreeningService {

    BasicCommandResponse createScreening(String filmTitle, String roomName, Date startDateAndTime, String token);

    List<Screening> getAllScreenings();

    BasicCommandResponse updateScreening(String filmTitle, String roomName, Date startDateAndTime, String token);

    BasicCommandResponse deleteRoom(String filmTitle, String roomName, Date startDateAndTime, String token);
}
