package com.amalgamatedservice.ticketservice.repository;

import java.util.Optional;

import com.amalgamatedservice.ticketservice.entity.SeatHold;

public interface VenueEventRepository {

	int numSeatsAvailable(Optional<Integer> venueLevel);

	SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail);

	String reserveSeats(int seatHoldId, String customerEmail);

}
