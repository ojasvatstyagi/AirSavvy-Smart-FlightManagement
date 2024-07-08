package com.nor.flightManagementSystem.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nor.flightManagementSystem.bean.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	@Query("SELECT MAX(ticketNumber) FROM Ticket")
    Long findLastTicketNumber();
	
	
}
