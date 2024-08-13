package com.nor.flightManagementSystem.repository;

import com.nor.flightManagementSystem.bean.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, Long> {
    Optional<Ticket> findTopByOrderByTicketNumberDesc();
    List<Ticket> findByUsername(String username);
}

