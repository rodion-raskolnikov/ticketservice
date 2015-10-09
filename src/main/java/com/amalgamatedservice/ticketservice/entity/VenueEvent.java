package com.amalgamatedservice.ticketservice.entity;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class VenueEvent {

	private static final Long TTL = 1000L;
	
    private final Integer id;
    private final String name;

    private final Map<Integer, Level> levels;

    private final Queue<SeatHold> onHold;
    private final Map<String, SeatReservation> reserved;
    
    public VenueEvent(Integer id, String name, Level[] levels) {
        this.id = id;
        this.name = name;

        this.levels = new HashMap<Integer, Level>();
        for(Level level: levels) {
            this.levels.put(level.getId(), level);
        }
        
        this.onHold = new PriorityQueue<SeatHold>(SeatHold.DATE_COMPARATOR.reversed());
        this.reserved = new HashMap<String, SeatReservation>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public synchronized int numSeatsAvailable(Optional<Integer> venueLevel) {
    	expireHolds(new Date(), TTL);
    	
    	int result = 0;
    	
    	if(venueLevel.isPresent()) {
    		result = levels.get(venueLevel.get()).numSeatsAvailable();
    	} else {
    		for(Level level : levels.values()) {
    			result += level.numSeatsAvailable();
    		}
    	}
		return result;
    }

    public synchronized SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {
    	expireHolds(new Date(), TTL);
    	
    	Integer l1 = (minLevel.isPresent() ? minLevel.get() : 1);
    	Integer l2 = (maxLevel.isPresent() ? maxLevel.get() : levels.size());

    	int totalSeatsAvailable = 0;
    	for(int i = l1; i <= l2; i ++) {
    		totalSeatsAvailable += levels.get(i).numSeatsAvailable();
    	}
    	if(totalSeatsAvailable < numSeats) {
    		return null;
    	}
    	
    	Set<Seat> seats = new HashSet<Seat>();
    	for(int i = l1; i <= l2 && numSeats > 0; i ++) {
    		Level level = levels.get(i);
    		int numSeatsAvailable = Math.min(level.numSeatsAvailable(), numSeats);
    		if(numSeatsAvailable > 0) {
    			seats.addAll(level.takeSeats(numSeatsAvailable));
    		}
    		numSeats -= numSeatsAvailable;
    	}
    	
    	SeatHold result = new SeatHold(seats, customerEmail);
    	onHold.add(result);
    	
    	return result;
    }

    public synchronized String reserveSeats(int seatHoldId, String customerEmail) {
//    	expireHolds(new Date(), TTL);
    	for(Iterator<SeatHold> i = onHold.iterator(); i.hasNext();) {
    		SeatHold hold = i.next();
    		if(hold.getId() == seatHoldId && hold.getCustomerEmail().equals(customerEmail)) {
    			i.remove();
    			SeatReservation reservation = new SeatReservation(hold.getSeats(), customerEmail);
    			reserved.put(reservation.getConfirmationCode(), reservation);
    			return reservation.getConfirmationCode();
    		}
    	}
    	return null;
    }
    
    private synchronized void expireHolds(Date dateTime, Long ttl) {
    	for(SeatHold hold = onHold.peek(); hold != null; hold = onHold.peek()) {
    		if(hold.getDateTime().getTime() + ttl < dateTime.getTime()) {
    			for(Seat seat : onHold.poll().getSeats()) {
    				Level level = levels.get(seat.getLevelId());
    				level.returnSeat(seat);
    			}
    		} else {
    			break;
    		}
    	}
    }
}
