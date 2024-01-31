package com.ujjaldas.gl.ticketmanager.controller;

import com.ujjaldas.gl.ticketmanager.entity.Ticket;
import com.ujjaldas.gl.ticketmanager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @GetMapping
    public String showTicketList(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "ticket-list";
    }

    @GetMapping("/{id}/edit")
    public String showEditTicketForm(@PathVariable Long id, Model model) {
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket", ticket);
        return "edit-ticket";
    }

    @GetMapping("/create")
    public String showCreateTicketForm(Model model) {
        model.addAttribute("ticket", new Ticket());
        return "create-ticket";
    }

    @PostMapping("/save")
    public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {
        
        ticket.setCreationDate(LocalDateTime.now());

       
        ticketService.saveTicket(ticket);

        return "redirect:/tickets";
    }

    @GetMapping("/{id}/delete")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets";
    }
    
    @PostMapping("/{id}/update")
    public String updateTicket(@PathVariable Long id, @ModelAttribute("ticket") Ticket updatedTicket) {
        
        Ticket existingTicket = ticketService.getTicketById(id);

        
        existingTicket.setName(updatedTicket.getName());
        existingTicket.setDescription(updatedTicket.getDescription());
        existingTicket.setContent(updatedTicket.getContent());

        
        ticketService.saveTicket(existingTicket);

        return "redirect:/tickets";
    }
}
