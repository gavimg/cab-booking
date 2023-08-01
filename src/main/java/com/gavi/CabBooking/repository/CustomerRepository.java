package com.gavi.CabBooking.repository;

import com.gavi.CabBooking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Customer findByUsername(String username);

    public Customer findByUsernameAndPassword(String username, String password);

}
