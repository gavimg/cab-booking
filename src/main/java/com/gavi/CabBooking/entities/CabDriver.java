package com.gavi.CabBooking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CabDriver extends User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer driverId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cabId")
    @JsonIgnore
    private Cab cab;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cabDriver", orphanRemoval = true)
    @JsonIgnore
    List<TripDetails> tripDetailsList = new ArrayList<>();

    @NotNull(message = "License Number cannot be null")
    @Column(unique = true)
    private String licenseNumber;

    private Boolean availablity = true;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public List<TripDetails> getTripDetailsList() {
        return tripDetailsList;
    }

    public void setTripDetailsList(List<TripDetails> tripDetailsList) {
        this.tripDetailsList = tripDetailsList;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Boolean getAvailablity() {
        return availablity;
    }

    public void setAvailablity(Boolean availablity) {
        this.availablity = availablity;
    }

    public CabDriver(Integer driverId, Cab cab) {
        super();
        this.driverId = driverId;
        this.cab = cab;
    }

    public CabDriver() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "CabDriver [driverId=" + driverId + ", tripDetailsList=" + tripDetailsList + ", licenseNumber="
                + licenseNumber + ", availablity=" + availablity + "]";
    }
}
