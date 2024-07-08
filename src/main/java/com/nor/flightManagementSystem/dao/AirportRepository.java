package com.nor.flightManagementSystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nor.flightManagementSystem.bean.Airport;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, String> {
    
    @Query("SELECT a.airportCode FROM Airport a")
    List<String> findAllAirportCodes();
    
    @Query("select airportCode from Airport where airportLocation=?1")
    String findAirportCodeByLocation(String airportLocation);
}
