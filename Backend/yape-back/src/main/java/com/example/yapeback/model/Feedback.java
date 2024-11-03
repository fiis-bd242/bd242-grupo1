// src/main/java/com/example/yapeback/model/Feedback.java
package com.example.yapeback.model;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class Feedback {
    private Long id_feedback;
    private Date fecha;
    private List<Observacion> observaciones;

    // Add the getDescripcion method
    public String getDescripcion() {
        StringBuilder descripcion = new StringBuilder();
        for (Observacion observacion : observaciones) {
            descripcion.append(observacion.getDescripcion()).append(" ");
        }
        return descripcion.toString().trim();
    }
}