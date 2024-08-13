package com.nor.flightManagementSystem.repository;

import java.util.List;

import com.nor.flightManagementSystem.bean.Ticket;

public interface TicketDao {
	void save(Ticket ticket);
	Long findLastTicketNumber();
	Ticket findTicketByTicketNumber(Long ticketNumber);
	void deleteByTicketNumber(Long ticketNumber);
	List<Ticket> findAllTickets();
	List<Ticket> findTicketsByUsername(String username);
}
