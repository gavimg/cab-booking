package com.gavi.CabBooking.services.customer;

import com.gavi.CabBooking.entities.CabDriver;
import com.gavi.CabBooking.entities.Customer;
import com.gavi.CabBooking.entities.TripDetails;
import com.gavi.CabBooking.exceptions.UserDoesNotExist;
import com.gavi.CabBooking.exceptions.UserNameAlreadyExist;
import com.gavi.CabBooking.repository.CabDriverRepository;
import com.gavi.CabBooking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerDao;

    @Autowired
    private CabDriverRepository cabDriverDao;

    @Override
    public ResponseEntity<Customer> insertCustomer(Customer customer) {
        Customer cust = customerDao.findByUsername(customer.getUsername());
        if(cust != null) throw new UserNameAlreadyExist("username already exist");
        customerDao.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Customer> updateCustomer(Customer customer,String user,String pass) {
        // TODO Auto-generated method stub
        Customer cust = customerDao.findByUsernameAndPassword(user, pass);
        if(cust == null) throw new UserDoesNotExist("username or password is wrong");

        if(customer.getUsername() != null) {
            Customer cust_new = customerDao.findByUsername(customer.getUsername());
            if(cust_new != null) throw new UserNameAlreadyExist("username already exist");
            cust.setUsername(customer.getUsername());
        }
        if(customer.getPassword() != null) cust.setPassword(customer.getPassword());
        if(customer.getEmail() != null) cust.setEmail(customer.getEmail());
        if(customer.getAddress() != null) cust.setAddress(customer.getAddress());
        if(customer.getMobile() != null) cust.setMobile(customer.getMobile());

        customerDao.save(cust);
        return new ResponseEntity<>(cust,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteCustomer(Customer customer) {
        // TODO Auto-generated method stub
        Customer cust = customerDao.findByUsernameAndPassword(customer.getUsername(), customer.getPassword());
        if(cust == null) throw new UserDoesNotExist("username or password is wrong");
        List<TripDetails> tripDetailsList = cust.getTripDetailsList();

        if(tripDetailsList.size() > 0) {

            if(tripDetailsList.get(tripDetailsList.size()-1).getStatus()== false) {
                CabDriver cab = tripDetailsList.get(tripDetailsList.size()-1).getCabDriver();
                cab.setAvailablity(true);
                cabDriverDao.save(cab);
                tripDetailsList.remove(tripDetailsList.size()-1);
                customerDao.save(cust);
            }
        }
        customerDao.delete(cust);
        return new ResponseEntity<>("Customer with username : " + customer.getUsername() + " deleted",HttpStatus.OK);
    }
}
