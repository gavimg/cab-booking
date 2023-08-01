package com.gavi.CabBooking.repository;

import com.gavi.CabBooking.entities.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CabRepository extends JpaRepository<Cab, Integer> {

    public Cab findByNumberPlate(String number);

}
