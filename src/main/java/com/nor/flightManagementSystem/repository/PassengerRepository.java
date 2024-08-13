package com.nor.flightManagementSystem.repository;

import java.util.List;

import com.nor.flightManagementSystem.bean.Passenger;
import com.nor.flightManagementSystem.bean.TicketPassengerEmbed;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PassengerRepository extends MongoRepository<Passenger, TicketPassengerEmbed> {
    List<Passenger> findByEmbeddedId_TicketNumber(Long ticketNumber);
}
