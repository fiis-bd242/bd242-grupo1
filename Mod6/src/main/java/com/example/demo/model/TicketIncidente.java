package com.example.demo.model;

import lombok.Data;

@Data

public class TicketIncidente {
	private Long codTicketInc;
    private String categoria;
    private String prioridad;
    private String estado;
    private String fechaTicketInc;

}
