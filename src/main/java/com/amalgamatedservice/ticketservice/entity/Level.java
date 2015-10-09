package com.amalgamatedservice.ticketservice.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Level {

    private final Integer id;
    private final String name;
    private final Integer rows;
    private final Integer nums;
    private final BigDecimal price;

    private final Queue<Seat> available;

    public Level(Integer id, String name, Integer rows, Integer nums, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.nums = nums;
        this.price = price;
        
        this.available = new PriorityQueue<Seat>(Seat.BEST_SEAT_COMPARATOR);

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

    public BigDecimal getPrice() {
    	return price;
    }
    
    public int numSeatsAvailable() {
    	return available.size();
    }
    
    public Set<Seat> takeSeats(int numSeats) {
    	Set<Seat> result = new HashSet<Seat>();
    	for(int i = 0; i < numSeats; i ++) {
    		if(available.size() > 0) {
    			result.add(available.poll());
    		}
    	}
    	return result;
    }
    public void returnSeats(Set<Seat> seats) {
    	available.addAll(seats);
    }
    public void returnSeat(Seat seat) {
    	available.add(seat);
    }
}
