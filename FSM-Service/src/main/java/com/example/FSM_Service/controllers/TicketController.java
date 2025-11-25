package com.example.FSM_Service.controllers;

import com.example.FSM_Service.dto.CrearTicketRequest;
import com.example.FSM_Service.dto.TicketIdRequest;
import com.example.FSM_Service.services.TicketService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping(value = "/crear", produces = MediaType.TEXT_PLAIN_VALUE)
    public String crearTicket(@RequestBody CrearTicketRequest dto) {
        return service.crearTicket(dto);
    }

    @PostMapping(value = "/actualizar", produces = MediaType.TEXT_PLAIN_VALUE)
    public String actualizarTicket(@RequestBody TicketIdRequest id) {
        return service.actualizarTicket(id);
    }
}
