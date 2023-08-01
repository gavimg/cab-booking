package com.gavi.CabBooking.exceptions;

public class TripInProgress extends RuntimeException{

    public TripInProgress() {

    }
    public TripInProgress(String msg) {
        super(msg);
    }
}
