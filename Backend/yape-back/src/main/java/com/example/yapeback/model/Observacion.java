// Si no se utiliza en otras partes, eliminar este archivo
package com.example.yapeback.model;

import lombok.Data;

@Data
public class Observacion {
    private Long id_observacion;
    private Long id_feedback;
    private String nombre;
    private String descripcion;
}
