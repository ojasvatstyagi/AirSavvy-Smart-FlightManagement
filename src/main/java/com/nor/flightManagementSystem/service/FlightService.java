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

		Optional<Route> routeOptional = routeDao.findRouteById(flight.getRouteId());

		if (routeOptional.isPresent()) {
			Route route = routeOptional.get();
			String sourceCode = route.getDestinationAirportCode();
			String destinationCode = route.getSourceAirportCode();

			Route newRoute = routeDao.findRouteBySourceAndDestination(sourceCode, destinationCode);

			if (newRoute != null) {
				return new Flight(newId, flight.getFlightName(), newRoute.getRouteId(), flight.getSeatCapacity(), dtime, atime);
			} else {
				throw new IllegalArgumentException("No route found for the return flight from " + sourceCode + " to " + destinationCode);
			}
		} else {
			throw new IllegalArgumentException("No route found with ID: " + flight.getRouteId());
		}
	}
}
