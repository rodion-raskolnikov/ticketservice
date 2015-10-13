package com.amalgamatedservice.ticketservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableScheduling
public class ApplicationCofiguration {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationCofiguration.class);
    }

}
