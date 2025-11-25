package com.example.FSM_Service.services;

import com.example.FSM_Service.dto.CrearTicketRequest;
import com.example.FSM_Service.dto.TicketIdRequest;
import com.example.FSM_Service.entities.Ticket;
import com.example.FSM_Service.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository repository;

    public TicketServiceImpl(TicketRepository repository) {
        this.repository = repository;
    }

    public String crearTicket(CrearTicketRequest dto) {
        Ticket ticket = new Ticket();

        String ticketId = "EmTK-" + UUID.randomUUID().toString().substring(0,4);

        ticket.setId(ticketId);
        ticket.setNombre(dto.getNombre());
        ticket.setDomicilio(dto.getDomicilio());
        ticket.setFalla(dto.getFalla());
        ticket.setEstado("CREADO");
        repository.save(ticket);

        System.out.println("Ticket creado: " + ticketId);
        return ticket.getId();
    }

    public String actualizarTicket(TicketIdRequest idRequest) {
        String id = idRequest.getTicketIdVisita();
        Ticket ticket = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));

        String estado = "resuelto";

        ticket.setEstado(estado);
        repository.save(ticket);

        System.out.println("Ticket actualizado: " + id);

        return "Ticket " + estado.toLowerCase() + " con ID: " + id;
    }
}