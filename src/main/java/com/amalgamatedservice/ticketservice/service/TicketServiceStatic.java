package com.amalgamatedservice.ticketservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amalgamatedservice.ticketservice.entity.SeatHold;
import com.amalgamatedservice.ticketservice.repository.VenueEventRepository;

@Service
public class TicketServiceStatic implements TicketService {

    @Autowired
    private VenueEventRepository venueEventRepository;

    @Override
    public int numSeatsAvailable(Optional<Integer> venueLevel) {
    	return venueEventRepository.numSeatsAvailable(venueLevel);
    }

    @Override
    public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {
    	return venueEventRepository.findAndHoldSeats(numSeats, minLevel, maxLevel, customerEmail);
    }

    @Override
    public String reserveSeats(int seatHoldId, String customerEmail) {
    	return venueEventRepository.reserveSeats(seatHoldId, customerEmail);
    }
}
