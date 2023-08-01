package com.gavi.CabBooking.controller;

import com.gavi.CabBooking.dtos.CabDriverCabDTO;
import com.gavi.CabBooking.dtos.TripDetailsDTO;
import com.gavi.CabBooking.entities.CabDriver;
import com.gavi.CabBooking.services.cabdriver.CabDriverService;
import com.gavi.CabBooking.services.tripdetailsservice.TripDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/cabdriver")
public class CabDriverController {

    @Autowired
    private CabDriverService cabDriverService;

    @Autowired
    private TripDetailsService tripDetailsService;

    @PostMapping("/create")
    public ResponseEntity<CabDriver> insertCabDriverHandler(@RequestBody CabDriverCabDTO cabdto){

        return cabDriverService.insertCabDriver(cabdto);
    }

    @PutMapping("/update")
    public ResponseEntity<CabDriver> updateCabDriverHandler(@RequestBody CabDriverCabDTO cabdto, @RequestParam String user, @RequestParam String pass){

        return cabDriverService.updateCabDriver(cabdto,user,pass);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCabDriverHandler(@RequestBody CabDriver cabDriver){
        return cabDriverService.deleteCabDriver(cabDriver);
    }

    @PostMapping("/tripcompleted")
    public ResponseEntity<String> tripCompletionHandler(@RequestBody TripDetailsDTO tripDto){
        return tripDetailsService.calculateBill(tripDto);
    }

    @PostMapping("/updatestatus")
    public ResponseEntity<String> updateStatusHandler(@RequestBody CabDriver cDriver){
        return cabDriverService.updateStatus(cDriver);
    }
}
