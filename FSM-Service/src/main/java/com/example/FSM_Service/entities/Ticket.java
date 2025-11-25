package com.example.FSM_Service.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ticket {
    @Id
    private String id;
    private String nombre;
    private String domicilio;
    private String falla;
    private String estado;
}
