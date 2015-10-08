package com.amalgamatedservice.ticketservice.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raskolnikov on 10/7/2015.
 */
public class Level {

    private final Integer id;
    private final String name;
    private final Integer rows;
    private final Integer rowSeats;

    private final List<Seat> seats;

    public Level(Integer id, String name, Integer rows, Integer rowSeats) {
        this.id = id;
        this.name = name;
        this.rows = rows;
        this.rowSeats = rowSeats;

        this.seats = new ArrayList<Seat>(rows * rowSeats);
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

    public Integer getRowSeats() {
        return rowSeats;
    }

    private Seat getSeat(Integer rowNum, Integer seatNum) {
        return seats.get(rowNum * rowSeats + seatNum);
    }
}
