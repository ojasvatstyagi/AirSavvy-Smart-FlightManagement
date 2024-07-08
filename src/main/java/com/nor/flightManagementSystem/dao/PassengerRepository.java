package com.nor.flightManagementSystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nor.flightManagementSystem.bean.Passenger;
import com.nor.flightManagementSystem.bean.TicketPassengerEmbed;

public interface PassengerRepository extends JpaRepository<Passenger, TicketPassengerEmbed> {
    List<Passenger> findByEmbeddedId_TicketNumber(Long ticketNumber);
}

