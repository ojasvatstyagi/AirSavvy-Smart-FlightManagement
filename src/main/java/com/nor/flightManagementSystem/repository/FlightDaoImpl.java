package com.nor.flightManagementSystem.repository;

import com.nor.flightManagementSystem.bean.Flight;
import com.nor.flightManagementSystem.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightDaoImpl implements FlightDao {

	@Autowired
	private FlightRepository flightRepository;

	@Override
	public void addFlight(Flight flight) {
		flightRepository.save(flight);
	}

	@Override
	public List<Flight> showAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	public Flight viewFlight(Long flightNumber) {
		return flightRepository.findById(flightNumber)
				.orElseThrow(() -> new EntityNotFoundException("Flight not found with number: " + flightNumber));
	}

	@Override
	public void updateFlight(Flight flight) {
		flightRepository.save(flight);
	}

	@Override
	public List<Flight> findFlightsByRouteId(Long routeId) {
		return flightRepository.findFlightsByRouteId(routeId);
	}

	@Override
	public void deleteFlightByFlightNumber(Long flightNumber) {
		flightRepository.deleteById(flightNumber);
	}

	@Override
	public Flight findByFlightNumber(Long flightNumber) {
		return flightRepository.findById(flightNumber).get();
	}
}
