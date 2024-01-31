package com.ujjaldas.gl.ticketmanager.service;

import com.ujjaldas.gl.ticketmanager.dao.TicketDAO;
import com.ujjaldas.gl.ticketmanager.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private final TicketDAO ticketDAO;

    @Autowired
    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public List<Ticket> getAllTickets() {
        return ticketDAO.findAll();
    }

    public Ticket getTicketById(Long id) {
        return ticketDAO.findById(id).orElse(null);
    }

    public void saveTicket(Ticket ticket) {
        
        ticket.setCreationDate(LocalDateTime.now());

        
        ticketDAO.saveAndFlush(ticket);
    }

    public void deleteTicket(Long id) {
        ticketDAO.deleteById(id);
    }
}