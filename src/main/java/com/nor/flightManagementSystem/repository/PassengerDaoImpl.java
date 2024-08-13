package com.nor.flightManagementSystem.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.nor.flightManagementSystem.bean.Passenger;

@Service
public class PassengerDaoImpl implements PassengerDao {

    @Autowired
    private PassengerRepository passengerRepository;

    @Override
    public void save(Passenger passenger) {
        passengerRepository.save(passenger);
    }

    @Override
    public List<Passenger> findByTicketNumber(Long ticketNumber) {
        return passengerRepository.findByEmbeddedId_TicketNumber(ticketNumber);
    }

    @Override
    public List<Passenger> findAllPassengers() {
        return passengerRepository.findAll();
    }
}
