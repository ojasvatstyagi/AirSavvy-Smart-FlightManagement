package com.nor.flightManagementSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nor.flightManagementSystem.bean.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT MAX(routeId) FROM Route")
    Long findLastRouteId();

    @Query("SELECT r FROM Route r WHERE sourceAirportCode = ?1 AND destinationAirportCode = ?2")
    Route findRouteBySourceAndDestination(String sourceAirportCode, String destinationAirportCode);

    @Query("SELECT routeId FROM Route")
    List<Long> findAllRoutesId();
}
