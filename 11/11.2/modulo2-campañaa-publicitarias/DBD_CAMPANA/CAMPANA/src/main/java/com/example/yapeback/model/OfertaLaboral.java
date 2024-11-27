package com.example.yapeback.model;

import lombok.Data;
import java.sql.Date;
import java.util.List;

@Data
public class OfertaLaboral {
    private Long id_oferta;
    private Date fecha_oferta;
    private Date fecha_inicio_propuesta;
    private String link_documento_legal_sin_firma;
    private String link_documento_legal_con_firma;
    private Long id_postulante;
    private Long id_vacante;
    private List<Beneficio> beneficios;
}