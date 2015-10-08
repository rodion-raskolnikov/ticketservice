package com.amalgamatedservice.ticketservice.repository;

import com.amalgamatedservice.ticketservice.entity.Venue;

/**
 * Created by Raskolnikov on 10/7/2015.
 */
public interface VenueRepository {

    Venue findVenue(Integer venueId);

}
