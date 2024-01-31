package com.ujjaldas.gl.ticketmanager.dao;

import com.ujjaldas.gl.ticketmanager.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketDAO extends JpaRepository<Ticket, Long> {
    
}
