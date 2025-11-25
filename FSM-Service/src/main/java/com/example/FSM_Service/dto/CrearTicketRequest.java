package com.example.FSM_Service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CrearTicketRequest {
    private String nombre;
    private String domicilio;
    private String falla;
}
