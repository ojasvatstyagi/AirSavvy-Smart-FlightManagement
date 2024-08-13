package com.nor.flightManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nor.flightManagementSystem.bean.Flight;
import com.nor.flightManagementSystem.bean.Route;
import com.nor.flightManagementSystem.repository.RouteDao;

import java.util.Optional;

@Service
public class FlightService {

	@Autowired
	private RouteDao routeDao;

	public Flight createReturnFlight(Flight flight, String dtime, String atime) {
		Long newId = flight.getFlightNumber() + 1;

		// Find the current route by routeId
		Optional<Route> routeOptional = routeDao.findRouteById(flight.getRouteId());

		if (routeOptional.isPresent()) {
			Route route = routeOptional.get();
			String sourceCode = route.getDestinationAirportCode();  // Swap source and destination for the return flight
			String destinationCode = route.getSourceAirportCode();

			// Find the new route based on swapped source and destination
			Route newRoute = routeDao.findRouteBySourceAndDestination(sourceCode, destinationCode);

			if (newRoute != null) {
				// Create and return the new return flight with the updated route and times
				return new Flight(newId, flight.getFlightName(), newRoute.getRouteId(), flight.getSeatCapacity(), dtime, atime);
			} else {
				// Handle the case where the reverse route doesn't exist
				throw new IllegalArgumentException("No route found for the return flight from " + sourceCode + " to " + destinationCode);
			}
		} else {
			// Handle the case where the original route is not found
			throw new IllegalArgumentException("No route found with ID: " + flight.getRouteId());
		}
	}
}
