package com.nor.flightManagementSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nor.flightManagementSystem.bean.Flight;


public interface FlightRepositary extends JpaRepository<Flight, Long> {
	
	@Query("SELECT f FROM Flight f WHERE routeId = ?1")
    List<Flight> findFlightsByRouteId(Long routeId);
}