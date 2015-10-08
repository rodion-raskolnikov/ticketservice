package com.amalgamatedservice.ticketservice.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * Created by Raskolnikov on 10/7/2015.
 */
public class Level {

	private static final Long EXPIRATION = 5000L;
	
    private final Integer id;
    private final String name;
    private final Integer rows;
    private final Integer nums;

    private final Queue<Seat> available;
    private final Queue<SeatHold> onHold;
    private final Map<String, SeatReservation> reserved;

    public Level(Integer id, String name, Integer rows, Integer nums) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.nums = nums;
        
        this.available = new PriorityQueue<Seat>(Seat.BEST_SEAT_COMPARATOR);
        this.onHold = new PriorityQueue<SeatHold>(SeatHold.DATE_COMPARATOR.reversed());
        this.reserved = new HashMap<String, SeatReservation>();

        for(int i = 0; i < rows; i ++) {
        	for(int j = 0; j < nums; j ++) {
        		available.add(new Seat(id, i, j));
        	}
        }
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getNums() {
        return nums;
    }

    Queue<Seat> getAvailable() {
		return available;
	}

	//TODO not idempotent ... may be it's okay.
    public int numSeatsAvailable() {
    	expireHolds(new Date(), EXPIRATION);
    	return available.size();
    }
    
    public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
    	expireHolds(new Date(), EXPIRATION);
    	
    	Set<Seat> seats = new HashSet<Seat>();
    	for(int i = 0; i < numSeats; i ++) {
    		if(available.size() > 0) {
    			seats.add(available.poll());
    		}
    	}
    	
    	SeatHold result = new SeatHold(new Date(), seats, customerEmail);
    	onHold.add(result);
    	
    	return result;
    }

    public String reserveSeats(int seatHoldId, String customerEmail) {
    	expireHolds(new Date(), EXPIRATION);
    	
    	for(Iterator<SeatHold> i = onHold.iterator(); i.hasNext();) {
    		SeatHold hold = i.next();
    		if(hold.getId() == seatHoldId && hold.getCustomerEmail().equals(customerEmail)) {
    			i.remove();
    			SeatReservation reservation = new SeatReservation(new Date(), hold.getSeats(), customerEmail);
    			reserved.put(reservation.getConfirmationCode(), reservation);
    			return reservation.getConfirmationCode();
    		}
    	}
    	return null;
    }

    private void expireHolds(Date dateTime, Long expiration) {
    	for(SeatHold hold = onHold.peek(); hold != null;) {
    		if(hold.getDateTime().getTime() + expiration < dateTime.getTime()) {
    			available.addAll(onHold.poll().getSeats());
    		}
    	}
    }

}
