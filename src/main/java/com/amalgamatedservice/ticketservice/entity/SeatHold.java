package com.amalgamatedservice.ticketservice.entity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class SeatHold implements Serializable {

	private static final long serialVersionUID = 1L;
	
	static final Comparator<SeatHold> DATE_COMPARATOR = new Comparator<SeatHold>() {
		@Override
		public int compare(SeatHold o1, SeatHold o2) {
			return o1.getDateTime().compareTo(o2.getDateTime());
		}
	};
	
	private static final AtomicInteger ID_GENERATOR = new AtomicInteger();
	
	private final Integer id;
	
	private final Date dateTime;

	private final Set<Seat> seats;
	
	private final String customerEmail;
	
	public SeatHold(Set<Seat> seats, String customerEmail) {
		this.id = ID_GENERATOR.incrementAndGet();
		this.dateTime = new Date();
		this.seats = seats;
		this.customerEmail = customerEmail;
	}

	public Integer getId() {
		return id;
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

}
