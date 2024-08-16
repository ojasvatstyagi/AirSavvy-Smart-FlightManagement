package com.nor.flightManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import com.nor.flightManagementSystem.bean.Airport;

public interface AirportDao {
     void addAirport(Airport airport);
     List<Airport> findAllAirports();
     List<String> findAllAirportCodes();
     String findAirportCodeByLocation(String airportLocation);
     void deleteAirportByCode(String airportCode);
     void updateAirport(Airport airport);
     Airport findAirportByCode(String airportCode);
}
