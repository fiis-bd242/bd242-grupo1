package com.example.demo.model;

import lombok.Data;

@Data

public class TicketGen {
	private Long codTicket;
    private String fechaCreacion;
    private String categoria;
    private Long idEmpleado;

}
