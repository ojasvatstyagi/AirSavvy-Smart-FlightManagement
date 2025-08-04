package com.nor.flightManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import com.nor.flightManagementSystem.bean.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepository extends MongoRepository<Flight, Long> {

    List<Flight> findFlightsByRouteId(Long routeId);

    @Query(sort = "{ flightNumber : -1 }")
    List<Flight> findTopByOrderByFlightNumberDesc();
}

