package com.nor.flightManagementSystem.repository;

import java.util.List;

import com.nor.flightManagementSystem.bean.Passenger;

public interface PassengerDao {
     void save(Passenger passenger);
     List<Passenger> findByTicketNumber(Long ticketNumber);
     List<Passenger> findAllPassengers();
}
