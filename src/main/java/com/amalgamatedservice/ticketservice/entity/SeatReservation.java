package com.amalgamatedservice.ticketservice.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class SeatReservation implements Serializable {

	private static final long serialVersionUID = 1L;

	private final Date dateTime;
	
	private final Set<Seat> seats;
	
	private final String customerEmail;
	
	private final String confirmationCode;
	
	public SeatReservation(Set<Seat> seats, String customerEmail) {
		this.dateTime = new Date();
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
