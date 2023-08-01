package com.gavi.CabBooking.services.admin;

import com.gavi.CabBooking.entities.Admin;
import com.gavi.CabBooking.entities.Cab;
import com.gavi.CabBooking.entities.Customer;
import com.gavi.CabBooking.entities.TripDetails;
import com.gavi.CabBooking.exceptions.UserDoesNotExist;
import com.gavi.CabBooking.exceptions.UserNameAlreadyExist;
import com.gavi.CabBooking.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminDao;

    @Autowired
    private CustomerRepository customerDao;

    @Autowired
    private CabDriverRepository cabDriverDao;

    @Autowired
    private TripDetailsRepository tripDetailsDoa;

    @Autowired
    private CabRepository cabDao;

    @Override
    public ResponseEntity<Admin> insertAdmin(Admin admin) {
        Admin adm = adminDao.findByUsername(admin.getUsername());
        if(adm!=null) throw new UserNameAlreadyExist("Username already exist");
        adminDao.save(admin);
        ResponseEntity<Admin> re=new ResponseEntity<>(admin, HttpStatus.ACCEPTED);
        return re;
    }

    @Override
    public ResponseEntity<Admin> updateAdmin(Admin admin, String user, String pass) {
        Admin adm = adminDao.findByUsernameAndPassword(user, pass);
        if(adm == null) throw new UserDoesNotExist("Username or Password is wrong");

        if(adm.getUsername() != null) {
            Admin adm_new = adminDao.findByUsername(adm.getUsername());
            if(adm_new != null) throw new UserNameAlreadyExist("username already exists");
            adm.setUsername(admin.getUsername());
        }
        if(admin.getPassword() != null) adm.setPassword(admin.getPassword());
        if(admin.getEmail() != null) adm.setEmail(admin.getEmail());
        if(admin.getAddress() != null) adm.setAddress(admin.getAddress());
        if(admin.getMobile() != null) adm.setMobile(admin.getMobile());
        adminDao.save(adm);
        return new ResponseEntity<>(adm, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteAdmin(Admin admin) {
        Admin adm = adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());
        if(adm==null) throw new UserDoesNotExist("Username or Password is wrong");
        adminDao.delete(adm);
        return new ResponseEntity<>("Admin with username : "+admin.getUsername()+" deleted",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TripDetails>> getAllTrips(Admin admin) {
        Admin adm=adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());

        if(adm==null) throw new UserDoesNotExist("Username or Password is wrong");
        List<TripDetails> allTrips = tripDetailsDoa.findAll();
        return new ResponseEntity<>(allTrips, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TripDetails>> getAllTripsByCab(Admin admin, Integer cabId) {
        Admin adm=adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());

        if(adm==null) throw new UserDoesNotExist("Username or Password is wrong");
        Cab cab = cabDao.findById(cabId).get();
        if(cab==null) throw new UserDoesNotExist("Cab Does not Exist");
        List<TripDetails> allTripsByCab = cab.getCabDriver().getTripDetailsList();
        return new ResponseEntity<>(allTripsByCab, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<TripDetails>> getAllTripsByCustomer(Admin admin, String username) {
        Admin adm=adminDao.findByUsernameAndPassword(admin.getUsername(), admin.getPassword());

        if(adm==null) throw new UserDoesNotExist("Username or Password is wrong");
        Customer customer = customerDao.findByUsername(username);
        if(customer == null) throw new UserDoesNotExist("Customer does not Exist");
        List<TripDetails> allTripsByCustomer = customer.getTripDetailsList();
        return new ResponseEntity<>(allTripsByCustomer, HttpStatus.OK);
    }
}
