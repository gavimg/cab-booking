package com.gavi.CabBooking.repository;

import com.gavi.CabBooking.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

    public Admin findByUsername(String username);

    public Admin findByUsernameAndPassword(String user,String pass);
}
