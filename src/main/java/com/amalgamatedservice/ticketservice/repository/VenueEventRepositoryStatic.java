package com.amalgamatedservice.ticketservice.repository;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.amalgamatedservice.ticketservice.entity.Level;
import com.amalgamatedservice.ticketservice.entity.VenueEvent;

@Repository
public class VenueEventRepositoryStatic implements VenueEventRepository {

    private final static VenueEvent venueEvent = new VenueEvent(1, "High Demand Performance Venue.", new Level[]{
                new Level(1, "Orchestra", 25, 50, new BigDecimal("100.00")),
                new Level(2, "Main", 20, 100, new BigDecimal("75.00")),
                new Level(3, "Balcony 1", 15, 100, new BigDecimal("50.00")),
                new Level(4, "Balcony 2", 15, 100, new BigDecimal("40.00"))});

    @Override
    public VenueEvent findVenueEvent(Integer eventId) {
        return venueEvent;
    }

}
