package com.example.FSM_Service.services;

import com.example.FSM_Service.dto.CrearTicketRequest;
import com.example.FSM_Service.dto.TicketIdRequest;

public interface TicketService {
    String crearTicket(CrearTicketRequest dto);
    String actualizarTicket(TicketIdRequest idRequest);
}
