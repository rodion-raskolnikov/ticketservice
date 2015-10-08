package com.amalgamatedservice.ticketservice;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amalgamatedservice.ticketservice.service.TicketService;

/**
 * Created by Raskolnikov on 10/8/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
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

}
