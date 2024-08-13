package com.nor.flightManagementSystem.repository;

import com.nor.flightManagementSystem.bean.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportDaoImpl implements AirportDao {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public void addAirport(Airport airport) {
        airportRepository.save(airport);
    }

    @Override
    public List<Airport> findAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public Optional<Airport> findAirportById(String id) {
        return airportRepository.findById(id);
    }

    @Override
    public List<String> findAllAirportCodes() {
        return airportRepository.findAllAirportCodes();
    }

    @Override
    public Optional<String> findAirportCodeByLocation(String airportLocation) {
        return airportRepository.findAirportCodeByLocation(airportLocation);
    }

    @Override
    public void deleteAirportByCode(String airportCode) {
        airportRepository.deleteById(airportCode);
    }

    @Override
    public void updateAirport(Airport airport) {
        airportRepository.save(airport);  // Changed from insert() to save()
    }
}
