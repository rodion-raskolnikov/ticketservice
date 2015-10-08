package com.amalgamatedservice.ticketservice.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by Raskolnikov on 10/7/2015.
 */
public class Venue {

    private final Integer id;
    private final String name;

    private final Map<Integer, Level> levels;

    public Venue(Integer id, String name) {
        this.id = id;
        this.name = name;

        this.levels = new HashMap<Integer, Level>();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Map<Integer, Level> getLevels() {
        return levels;
    }

    public synchronized int numSeatsAvailable(Optional<Integer> venueLevel) {
    	int result = 0;
    	
    	if(venueLevel.isPresent()) {
    		result = levels.get(venueLevel.get()).numSeatsAvailable();
    	} else {
    		for(Level l : levels.values()) {
    			result += l.numSeatsAvailable();
    		}
    	}
		return result;
    }

    public synchronized SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {
    	
    	Integer l1 = (minLevel.isPresent() ? minLevel.get() : 1);
    	Integer l2 = (maxLevel.isPresent() ? maxLevel.get() + 1 : getLevels().size());
    	
    	for(int i = l1; i < l2 && numSeats > 0; i ++) {
    		Level level = getLevels().get(i);
    		int numSeatsAvailable = Math.min(level.numSeatsAvailable(), numSeats);
    		if(numSeatsAvailable > 0) {
    			SeatHold hold = level.findAndHoldSeats(numSeatsAvailable, customerEmail);
    		}
    		numSeats -= numSeatsAvailable;
    	}
    	return null;
    }

    public synchronized String reserveSeats(int seatHoldId, String customerEmail) {
        return null;
    }
}
