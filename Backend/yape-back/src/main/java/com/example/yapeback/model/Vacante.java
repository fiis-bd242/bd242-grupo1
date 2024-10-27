package com.example.yapeback.model;

import lombok.Data;
import java.sql.Date;
import java.util.List;

@Data
public class Vacante {
    private Long id_vacante;
    private String estado;
    private Date fecha_fin;
    private Date fecha_inicio;
    private String comentario;
    private Long id_puesto;
    private List<Convocatoria> convocatorias;
    private List<Postulante> postulantes;
}
