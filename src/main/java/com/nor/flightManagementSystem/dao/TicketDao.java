package com.nor.flightManagementSystem.dao;

import com.nor.flightManagementSystem.bean.Ticket;

public interface TicketDao {
	void save(Ticket ticket);
	Long findLastTicketNumber();
	Ticket findTicketByTicketNumber(Long ticketNumber);
	void deleteByTicketNumber(Long ticketNumber);
}
