package com.nor.flightManagementSystem.repository;

import com.nor.flightManagementSystem.bean.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportDaoImpl implements AirportDao {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void addAirport(Airport airport) {
        airportRepository.save(airport);
    }

    @Override
    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public List<String> findAllAirportCodes() {
        return airportRepository.findAllAirportCodes();
    }

    @Override
    public String findAirportCodeByLocation(String airportLocation) {
        Optional<Airport> airport = airportRepository.findAirportCodeByLocation(airportLocation);
        return airport.get().getAirportCode();
    }

    @Override
    public void deleteAirportByCode(String airportCode) {
        airportRepository.deleteById(airportCode);
    }

    @Override
    public void updateAirport(Airport airport) {
        // Create a query to find the airport by airportCode
        Query query = new Query();
        query.addCriteria(Criteria.where("airportCode").is(airport.getAirportCode()));

        // Create an update object with the fields to be updated
        Update update = new Update();
        update.set("airportLocation", airport.getAirportLocation());
        update.set("details", airport.getDetails());

        // Execute the update operation
        mongoTemplate.updateFirst(query, update, Airport.class);
    }

    @Override
    public Airport findAirportByCode(String airportCode) {
        return airportRepository.findAirportByCode(airportCode);
    }
}
