package com.nor.flightManagementSystem.repository;

import com.mongodb.client.result.UpdateResult;
import com.nor.flightManagementSystem.bean.Flight;
import com.nor.flightManagementSystem.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.Criteria;


@Service
public class FlightDaoImpl implements FlightDao {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public void addFlight(Flight flight) {
		flightRepository.save(flight);
	}

	@Override
	public List<Flight> showAllFlights() {
		return flightRepository.findAll();
	}

	@Override
	public void deleteFlightByFlightNumber(Long flightNumber) {
		flightRepository.deleteById(flightNumber);
	}

	@Override
	public Flight findByFlightNumber(Long flightNumber) {
		return flightRepository.findById(flightNumber)
				.orElseThrow(() -> new EntityNotFoundException("Flight not found with flightNumber: " + flightNumber));
	}

	@Override
	public void updateFlight(Flight flight) {
		try {
			Query query = new Query(Criteria.where("_id").is(flight.getFlightNumber()));
			Update update = new Update()
					.set("flightName", flight.getFlightName())
					.set("routeId", flight.getRouteId())
					.set("seatCapacity", flight.getSeatCapacity())
					.set("departure", flight.getDeparture())
					.set("arrival", flight.getArrival())
					.set("seatsBooked", flight.getSeatsBooked());
			mongoTemplate.updateFirst(query, update, Flight.class);
		} catch (Exception e) {
			System.err.println("Error occurred while updating flight: " + e.getMessage());
		}
	}

	@Override
	public List<Flight> findFlightsByRouteId(Long routeId) {
		Query query = new Query(Criteria.where("routeId").is(routeId));
		return mongoTemplate.find(query, Flight.class);
	}
}


