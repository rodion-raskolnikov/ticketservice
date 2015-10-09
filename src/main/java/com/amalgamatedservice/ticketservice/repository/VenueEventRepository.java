package com.amalgamatedservice.ticketservice.repository;

import com.amalgamatedservice.ticketservice.entity.VenueEvent;

public interface VenueEventRepository {

    VenueEvent findVenueEvent(Integer eventId);

}
