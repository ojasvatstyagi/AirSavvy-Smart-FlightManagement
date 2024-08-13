package com.nor.flightManagementSystem.repository;

import java.util.List;

import com.nor.flightManagementSystem.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nor.flightManagementSystem.bean.Ticket;

@Service
public class TicketDaoImpl implements TicketDao {

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public void save(Ticket ticket) {
		ticketRepository.save(ticket);
	}

	@Override
	public Long findLastTicketNumber() {
		return ticketRepository.findTopByOrderByTicketNumberDesc()
				.map(ticket -> ticket.getTicketNumber() + 1)
				.orElse(1000001L);
	}

	@Override
	public Ticket findTicketByTicketNumber(Long ticketNumber) {
		return ticketRepository.findById(ticketNumber)
				.orElseThrow(() -> new EntityNotFoundException("Ticket not found with number: " + ticketNumber));
	}

	@Override
	public void deleteByTicketNumber(Long ticketNumber) {
		ticketRepository.deleteById(ticketNumber);
	}

	@Override
	public List<Ticket> findAllTickets() {
		return ticketRepository.findAll();
	}

	@Override
	public List<Ticket> findTicketsByUsername(String username) {
		return ticketRepository.findByUsername(username);
	}
}
