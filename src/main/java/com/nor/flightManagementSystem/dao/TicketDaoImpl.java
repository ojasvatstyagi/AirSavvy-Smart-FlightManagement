package com.nor.flightManagementSystem.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nor.flightManagementSystem.bean.Ticket;

@Service
public class TicketDaoImpl implements TicketDao{

	@Autowired
	private TicketRepository repo;
	
	@Override
	public void save(Ticket ticket) {
		repo.save(ticket);
	}
	
	@Override
	public Long findLastTicketNumber() {
		Long val = repo.findLastTicketNumber();
		if (val == null)	val = 1000001L;
		else	val += 1;
		return val;
	}

	@Override
	public Ticket findTicketByTicketNumber(Long ticketNumber) {
		return repo.findById(ticketNumber).get();
	}

	@Override
	public void deleteByTicketNumber(Long ticketNumber) {
		repo.deleteById(ticketNumber);
	}

}
