package com.nor.flightManagementSystem.repository;

import java.util.List;

import com.nor.flightManagementSystem.bean.Flight;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FlightRepository extends MongoRepository<Flight, Long> {
    List<Flight> findFlightsByRouteId(Long routeId);
}

