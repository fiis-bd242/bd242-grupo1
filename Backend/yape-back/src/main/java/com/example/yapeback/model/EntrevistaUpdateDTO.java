package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class EntrevistaUpdateDTO {
    private LocalDate fecha;
    private String estado;
    private Long id_empleado;
    private Long id_postulante;
    private Long id_tipo_entrevista;
    private List<EntrevistaIndicador> indicadores;
}
