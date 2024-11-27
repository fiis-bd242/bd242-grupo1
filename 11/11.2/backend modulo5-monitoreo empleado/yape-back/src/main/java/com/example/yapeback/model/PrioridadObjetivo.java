package com.example.yapeback.model;

import lombok.Data;

@Data 
public class PrioridadObjetivo {
	private Long idPrioridadObjetivo;
    private EstadoPrioridad nombre; // Enum: Alta, Media, Baja
    private Integer peso;

    public enum EstadoPrioridad {
        Alta, Media, Baja
    }
}
