package com.amalgamatedservice.ticketservice.repository.mem;

import com.amalgamatedservice.ticketservice.entity.Level;
import com.amalgamatedservice.ticketservice.entity.Venue;
import com.amalgamatedservice.ticketservice.repository.VenueRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Raskolnikov on 10/8/2015.
 */
@Repository
public class VenueRepositoryStatic implements VenueRepository {

    private final static Venue venue = new Venue(1, "High Demand Performance Venue.");
    static {
        Level[] levels = new Level[]{
                new Level(1, "Orchestra", 25, 50),
                new Level(2, "Main", 20, 100),
                new Level(3, "Balcony 1", 15, 100),
                new Level(4, "Balcony 2", 15, 100)};

        for(Level level: levels) {
            venue.getLevels().put(level.getId(), level);
        }
    }

    @Override
    public Venue findVenue(Integer venueId) {
        return venue;
    }

}
