package com.example.yapeback.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoTicket {
    PENDIENTE("Pendiente"),
    EN_PROGRESO("En Progreso"),
    RESUELTO("Resuelto"),
    NO_RESUELTO("No Resuelto"),
    REABIERTO("Reabierto");

    private String estado;

    EstadoTicket(String estado) {
        this.estado = estado;
    }

    @JsonValue
    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return estado;
    }

    // Método para obtener el valor del Enum desde una cadena
    public static EstadoTicket fromString(String estado) {
        for (EstadoTicket et : EstadoTicket.values()) {
            if (et.estado.equalsIgnoreCase(estado)) {
                return et;
            }
        }
        throw new IllegalArgumentException("Estado no válido: " + estado);
    }
}
