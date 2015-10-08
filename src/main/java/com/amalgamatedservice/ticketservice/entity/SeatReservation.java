package com.amalgamatedservice.ticketservice.entity;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Raskolnikov on 10/7/2015.
 */
public class SeatReservation {

	private final Date dateTime;
	
	private final Set<Seat> seats;
	
	private final String customerEmail;
	
	private final String confirmationCode;
	
	public SeatReservation(Date dateTime, Set<Seat> seats, String customerEmail) {
		this.dateTime = dateTime;
		this.seats = seats;
		this.customerEmail = customerEmail;
		this.confirmationCode = UUID.randomUUID().toString();
	}

	public Date getDateTime() {
		return dateTime;
	}

	public Set<Seat> getSeats() {
		return seats;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

}
