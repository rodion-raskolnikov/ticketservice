package com.amalgamatedservice.ticketservice;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amalgamatedservice.ticketservice.entity.Seat;
import com.amalgamatedservice.ticketservice.entity.SeatHold;
import com.amalgamatedservice.ticketservice.service.TicketService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationCofiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class TicketServiceTests {

    @Autowired
    private TicketService ticketService;

    @Test
    public void testNumSeatsAvailable() throws Exception {
        Assert.assertEquals(ticketService.numSeatsAvailable(Optional.of(1)), 1250);
        Assert.assertEquals(ticketService.numSeatsAvailable(Optional.of(2)), 2000);
        Assert.assertEquals(ticketService.numSeatsAvailable(Optional.of(3)), 1500);
        Assert.assertEquals(ticketService.numSeatsAvailable(Optional.of(4)), 1500);
        Assert.assertEquals(ticketService.numSeatsAvailable(Optional.empty()), 6250);
    }

    @Test
    public void testFindAndHoldSeats() throws Exception {
    	
    	SeatHold hold = ticketService.findAndHoldSeats(2, Optional.empty(), Optional.empty(), "raskolnikov@amalgamatedservice.com");
        Assert.assertEquals(ticketService.numSeatsAvailable(Optional.of(1)), 1248);
    	
    	Assert.assertEquals(hold.getSeats().size(), 2);
    	for(Seat seat : hold.getSeats()) {
    		Assert.assertEquals(seat.getLevelId(), new Integer(1));
    		Assert.assertEquals(seat.getRow(), new Integer(0));
    	}

    	hold = ticketService.findAndHoldSeats(2, Optional.of(2), Optional.of(4), "raskolnikov@amalgamatedservice.com");
        Assert.assertEquals(ticketService.numSeatsAvailable(Optional.of(2)), 1998);
        Assert.assertEquals(ticketService.numSeatsAvailable(Optional.empty()), 6246);
        
    	Assert.assertEquals(hold.getSeats().size(), 2);
    	for(Seat seat : hold.getSeats()) {
    		Assert.assertEquals(seat.getLevelId(), new Integer(2));
    		Assert.assertEquals(seat.getRow(), new Integer(0));
    	}

    	Thread.sleep(2L * 1000L);
    	
    	hold = ticketService.findAndHoldSeats(2, Optional.of(2), Optional.of(4), "raskolnikov@amalgamatedservice.com");
        Assert.assertEquals(ticketService.numSeatsAvailable(Optional.empty()), 6248);

    }
    
    @Test
    public void testReserveSeats() throws Exception {

    	SeatHold hold = ticketService.findAndHoldSeats(2, Optional.empty(), Optional.empty(), "raskolnikov@amalgamatedservice.com");
    	
    	String reservationId = ticketService.reserveSeats(hold.getId(), "someoneelse@amalgamatedservice.com");
    	Assert.assertNull(reservationId);
    	
    	reservationId = ticketService.reserveSeats(hold.getId(), "raskolnikov@amalgamatedservice.com");
    	Assert.assertNotNull(reservationId);
    	Assert.assertEquals(ticketService.numSeatsAvailable(Optional.empty()), 6248);
    	
    	Thread.sleep(2L * 1000L);
    	
        Assert.assertEquals(ticketService.numSeatsAvailable(Optional.empty()), 6248);

    }
}
