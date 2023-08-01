package com.gavi.CabBooking.controller;

import com.gavi.CabBooking.entities.Admin;
import com.gavi.CabBooking.entities.TripDetails;
import com.gavi.CabBooking.services.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/create")
    public ResponseEntity<Admin> insertAdminHandler(@Valid @RequestBody Admin admin)
    {
        return adminService.insertAdmin(admin);
    }

    @PutMapping("/update")
    public ResponseEntity<Admin> updateAdminHandler(@RequestBody Admin admin, @RequestParam String user, @RequestParam String pass)
    {
        return adminService.updateAdmin(admin, user, pass);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAdminHandler(@RequestBody Admin admin)
    {
        return adminService.deleteAdmin(admin);
    }

    @PostMapping("/getalltrips")
    public ResponseEntity<List<TripDetails>> getAllTripsHandler(@RequestBody Admin admin)
    {
        return adminService.getAllTrips(admin);
    }

    @PostMapping("/getalltripsbycab/{cabId}")
    public ResponseEntity<List<TripDetails>> getAllTripsByCabHandler(@RequestBody Admin admin, @PathVariable Integer cabId)
    {
        return adminService.getAllTripsByCab(admin, cabId);
    }

    @PostMapping("/getalltripsbycustomer/{username}")
    public ResponseEntity<List<TripDetails>> getAllTripsByCustomerHandler(@RequestBody Admin admin, @PathVariable String username)
    {
        return adminService.getAllTripsByCustomer(admin, username);
    }
}
