package com.example.yapeback.model;

import lombok.Data;

@Data

public class EstadoTicketAsig {
    private Long id_estado;
    private estado_enum nombre_estado;

    public enum estado_enum {
        Pendiente, Resuelto, No_Resuelto,En_Progreso,Reabierto
    }
}
