package com.example.FSM_Service.repositories;

import com.example.FSM_Service.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, String> {
}
