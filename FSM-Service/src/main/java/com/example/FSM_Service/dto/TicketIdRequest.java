package com.example.FSM_Service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketIdRequest {
    @JsonProperty("TicketIdVisita")
    private String TicketIdVisita;
}
