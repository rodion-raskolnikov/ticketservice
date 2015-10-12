package com.amalgamatedservice.ticketservice.repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.amalgamatedservice.ticketservice.entity.Level;
import com.amalgamatedservice.ticketservice.entity.Seat;
import com.amalgamatedservice.ticketservice.entity.SeatHold;
import com.amalgamatedservice.ticketservice.entity.SeatReservation;

/**
 * 
 * Static in-memory implementation of {@link VenueEventRepository}, has to be a singleton.
 *
 */
@Repository
public class VenueEventRepositoryStatic implements VenueEventRepository {
	
	private static final Long HOLD_TTL = 1000L;

    private final Map<Integer, Level> levels;

    private final Queue<SeatHold> onHold;
    private final Map<String, SeatReservation> reserved;

    public VenueEventRepositoryStatic() {
    	
        Level[] lvls = new Level[]{
                new Level(1, "Orchestra", 25, 50, new BigDecimal("100.00")),
                new Level(2, "Main", 20, 100, new BigDecimal("75.00")),
                new Level(3, "Balcony 1", 15, 100, new BigDecimal("50.00")),
                new Level(4, "Balcony 2", 15, 100, new BigDecimal("40.00"))};
    	
        this.levels = new HashMap<Integer, Level>();
        for(Level level: lvls) {
            this.levels.put(level.getId(), level);
        }
        
        this.onHold = new PriorityQueue<SeatHold>(SeatHold.DATE_COMPARATOR);
        this.reserved = new HashMap<String, SeatReservation>();
    }
    
    @Override
    public synchronized int numSeatsAvailable(Optional<Integer> venueLevel) {
    	expireHolds(new Date(), HOLD_TTL);
    	
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

    @Override
    public synchronized SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {
    	expireHolds(new Date(), HOLD_TTL);
    	
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

    @Override
    public synchronized String reserveSeats(int seatHoldId, String customerEmail) {
//    	expireHolds(new Date(), HOLD_TTL);
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
    
    /**
     * 
     * Expires all the holds more that ttl older then current time.
     * 
     * @param dateTime current time
     * @param ttl hold ttl in milliseconds
     * 
     */
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
