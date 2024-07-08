package com.nor.flightManagementSystem.dao;

import java.util.List;

import com.nor.flightManagementSystem.bean.Airport;

public interface AirportDao {
    void addAirport(Airport airport);
    List<Airport> findAllAirports();
    Airport findAirportById(String id);
    List<String> findAllAirportCodes();
	String findAirportCodeByLocation(String sourceAirportCode);
	void deleteAirportByCode(String airportCode);
	void updateAirport(Airport airport);
    }
