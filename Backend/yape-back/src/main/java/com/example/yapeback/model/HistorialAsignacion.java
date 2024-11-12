package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HistorialAsignacion {
    private Long idAsig;
    private Long codTicketAsig;
    private Long codEtiqueta;
    private String tipoAccion;
    private Long realizadoPor;
    private LocalDate fechaAccion;
}
