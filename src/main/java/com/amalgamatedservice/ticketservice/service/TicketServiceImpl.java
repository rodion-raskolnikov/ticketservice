package com.amalgamatedservice.ticketservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amalgamatedservice.ticketservice.entity.SeatHold;
import com.amalgamatedservice.ticketservice.entity.Venue;
import com.amalgamatedservice.ticketservice.repository.VenueRepository;

/**
 * Created by Raskolnikov on 10/8/2015.
 */

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private VenueRepository venueRepository;

    @Override
    public int numSeatsAvailable(Optional<Integer> venueLevel) {
    	Venue venue = venueRepository.findVenue(1);
    	return venue.numSeatsAvailable(venueLevel);
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
