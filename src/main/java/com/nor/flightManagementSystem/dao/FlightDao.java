package com.nor.flightManagementSystem.dao;

import java.util.List;

import com.nor.flightManagementSystem.bean.Flight;

public interface FlightDao {
	void addFlight(Flight flight);
	List<Flight> showAllFlights();
	Flight viewFlight(Long flightNumber);
	void updateFlight(Flight flight);
	List<Flight> findFlightsByRouteId(Long routeId);
	void deleteFlightByFlightNumber(Long flightNumber);
}
