package com.amalgamatedservice.ticketservice;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amalgamatedservice.ticketservice.entity.SeatHold;
import com.amalgamatedservice.ticketservice.service.TicketService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationCofiguration.class)
public class TicketServiceTests {

    @Autowired
    private TicketService ticketService;

    @Test
    public void testNumSeatsAvailable() throws Exception {
        System.out.println(ticketService.numSeatsAvailable(Optional.of(1)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(2)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(3)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(4)));
        System.out.println(ticketService.numSeatsAvailable(Optional.empty()));
    }

    @Test
    public void findAndHoldSeats() throws Exception {
    	
        System.out.println(ticketService.numSeatsAvailable(Optional.of(1)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(2)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(3)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(4)));
    	
    	SeatHold hold = ticketService.findAndHoldSeats(400, Optional.empty(), Optional.empty(), "raskolnikov@amalgamatedservice.com");
    	hold = ticketService.findAndHoldSeats(2000, Optional.of(3), Optional.of(4), "raskolnikov@amalgamatedservice.com");
    	System.out.println(hold.getSeats().size());

        System.out.println(ticketService.numSeatsAvailable(Optional.of(1)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(2)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(3)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(4)));

        ticketService.reserveSeats(hold.getId(), "raskolnikov@amalgamatedservice.com");
        
    	Thread.sleep(2L * 1000L);

    	System.out.println("-------------------------");
    	
        System.out.println(ticketService.numSeatsAvailable(Optional.of(1)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(2)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(3)));
        System.out.println(ticketService.numSeatsAvailable(Optional.of(4)));
    }
}
