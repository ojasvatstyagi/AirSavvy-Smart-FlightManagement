package com.nor.flightManagementSystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nor.flightManagementSystem.bean.Airport;

import java.util.List;
import java.util.Optional;

@Repository
public interface AirportRepository extends MongoRepository<Airport, String> {

    @Query(value = "{}", fields = "{ 'airportCode' : 1 }")
    List<String> findAllAirportCodes();

    @Query(value = "{ 'airportCode' : ?0 }")
    Airport findAirportByCode(String airportCode);

    @Query(value = "{ 'airportLocation': ?0 }", fields = "{ 'airportCode' : 1 }")
    Optional<Airport> findAirportCodeByLocation(String airportLocation);  // Changed to Optional<String>
}

