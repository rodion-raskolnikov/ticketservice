package com.amalgamatedservice.ticketservice.service;

import com.amalgamatedservice.ticketservice.entity.SeatHold;
import com.amalgamatedservice.ticketservice.repository.VenueRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

/**
 * Created by Raskolnikov on 10/8/2015.
 */
public class TicketServiceImpl implements TicketService {

    @Autowired
    private VenueRepository venueRepository;

    @Override
    public int numSeatsAvailable(Optional<Integer> venueLevel) {
        return venueRepository.findVenue(1).getLevels().size();
    }

    @Override
    public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {
        return null;
    }

    @Override
    public String reserveSeats(int seatHoldId, String customerEmail) {
        return null;
    }
}
