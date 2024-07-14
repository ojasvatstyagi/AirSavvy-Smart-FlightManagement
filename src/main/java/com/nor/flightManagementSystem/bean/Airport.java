package com.nor.flightManagementSystem.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @Column(nullable = false)
    private String airportCode;
    private String airportLocation;
    private String details;

    // Default constructor
    public Airport() {
        super();
    }

    // Parameterized constructor
    public Airport(String airportCode, String airportLocation, String details) {
        this.airportCode = airportCode;
        this.airportLocation = airportLocation;
        this.details = details;
    }

    // Getters and setters
    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportLocation() {
        return airportLocation;
    }

    public void setAirportLocation(String airportLocation) {
        this.airportLocation = airportLocation;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    // toString method
    @Override
    public String toString() {
        return "Airport{" +
                "airportCode='" + airportCode + '\'' +
                ", airportLocation='" + airportLocation + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
