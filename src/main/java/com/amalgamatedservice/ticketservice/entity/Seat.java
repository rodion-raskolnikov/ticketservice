package com.amalgamatedservice.ticketservice.entity;

import java.util.Date;

/**
 * Created by Raskolnikov on 10/8/2015.
 */
public class Seat {

    private SeatStatus status = SeatStatus.AVAILABLE;
    private Date statusSetTime;

    public SeatStatus getStatus() {
        return status;
    }

    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public Date getStatusSetTime() {
        return statusSetTime;
    }

    public void setStatusSetTime(Date statusSetTime) {
        this.statusSetTime = statusSetTime;
    }
}
