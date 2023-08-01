package com.gavi.CabBooking.repository;

import com.gavi.CabBooking.entities.TripDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripDetailsRepository extends JpaRepository<TripDetails, Integer> {
}
