package com.amalgamatedservice.ticketservice;

import com.amalgamatedservice.ticketservice.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

/**
 * Created by Raskolnikov on 10/8/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class TicketServiceTests {

    @Autowired
    private TicketService ticketService;

    @Test
    public void testLevels() throws Exception {
        ticketService.numSeatsAvailable(Optional.of(1));
    }

}
