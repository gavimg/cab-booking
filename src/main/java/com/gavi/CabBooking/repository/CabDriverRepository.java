package com.gavi.CabBooking.repository;

import com.gavi.CabBooking.entities.CabDriver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabDriverRepository extends JpaRepository<CabDriver, Integer> {

    public CabDriver findByUsername(String username);

    public CabDriver findByLicenseNumber(String license);

    public CabDriver findByUsernameAndPassword(String user, String pass);
}
