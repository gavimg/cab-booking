package com.gavi.CabBooking.controller;

import com.gavi.CabBooking.dtos.TripDetailsDTO;
import com.gavi.CabBooking.entities.BillDetails;
import com.gavi.CabBooking.entities.Customer;
import com.gavi.CabBooking.entities.TripDetails;
import com.gavi.CabBooking.services.customer.CustomerServiceImpl;
import com.gavi.CabBooking.services.tripdetailsservice.TripDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl custService;

    @Autowired
    private TripDetailsService tripService;

    @PostMapping("/create")
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
        return custService.insertCustomer(customer);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestParam String user,@RequestParam String pass,@RequestBody Customer customer) {
        return custService.updateCustomer(customer, user, pass);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer) {
        return custService.deleteCustomer(customer);
    }

    @PostMapping("/booktrip")
    public ResponseEntity<TripDetails> bookTrip(@RequestBody TripDetailsDTO tripDto){
        return tripService.insertTripDetails(tripDto);
    }

    @DeleteMapping("/canceltrip")
    public ResponseEntity<String> deleleTrip(@RequestBody TripDetailsDTO tripDto){
        return tripService.deleteBookedTrip(tripDto);
    }

    @PostMapping("/triplist")
    public ResponseEntity<List<TripDetails>> getAllCustomerTripList(@RequestBody TripDetailsDTO tripDto){

        return tripService.getAllTripsOfCustomer(tripDto);
    }

    @PostMapping("/generatebill")
    public ResponseEntity<BillDetails> generateBillHandler(@RequestBody TripDetailsDTO tripDto){
        return tripService.generateBill(tripDto);
    }
}
