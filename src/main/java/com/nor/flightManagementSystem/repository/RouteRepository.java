
package com.nor.flightManagementSystem.repository;

import java.util.List;

import com.nor.flightManagementSystem.bean.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends MongoRepository<Route, Long> {

    List<Route> findTop1ByOrderByRouteIdDesc();

    @Query(value = "{ 'sourceAirportCode': ?0, 'destinationAirportCode': ?1 }")
    Route findRouteBySourceAndDestination(String sourceAirportCode, String destinationAirportCode);
}

