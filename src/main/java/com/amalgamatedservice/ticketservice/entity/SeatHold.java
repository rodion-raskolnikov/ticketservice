package com.amalgamatedservice.ticketservice.entity;

import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Raskolnikov on 10/7/2015.
 */
public class SeatHold {
	
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
	
	public SeatHold(Date dateTime, Set<Seat> seats, String customerEmail) {
		this.id = ID_GENERATOR.incrementAndGet();
		this.dateTime = dateTime;
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
