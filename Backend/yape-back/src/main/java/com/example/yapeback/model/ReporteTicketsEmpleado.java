package com.example.yapeback.model;

import lombok.Data;

@Data
public class ReporteTicketsEmpleado {
    private Integer idEmpleado;
    private Integer ticketsGestionados;
    private String periodo;
}
