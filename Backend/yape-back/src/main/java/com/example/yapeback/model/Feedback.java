package com.example.yapeback.model;

import lombok.Data;
import java.util.List;
import java.sql.Date;
@Data
public class Feedback {
    private Long id_feedback;
    private Date fecha;
    private Long id_entrevista;
    private List<Observacion> observaciones;
}