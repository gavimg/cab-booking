package com.gavi.CabBooking.services.tripdetailsservice;

import com.gavi.CabBooking.dtos.TripDetailsDTO;
import com.gavi.CabBooking.entities.BillDetails;
import com.gavi.CabBooking.entities.TripDetails;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TripDetailsService {

    public ResponseEntity<TripDetails> insertTripDetails(TripDetailsDTO tripDto);

    public ResponseEntity<String> deleteBookedTrip(TripDetailsDTO tripDto);

    public ResponseEntity<List<TripDetails>> getAllTripsOfCustomer(TripDetailsDTO tripDto);

    public ResponseEntity<String> calculateBill(TripDetailsDTO tripDto);

    public ResponseEntity<BillDetails> generateBill(TripDetailsDTO tripDto);
}
