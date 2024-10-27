// src/main/java/com/example/yapeback/model/Puesto.java
package com.example.yapeback.model;

import lombok.Data;
import java.util.List;

@Data
public class Puesto {
    private Long id_puesto;
    private String nombre;
    private Double paga;
    private Long id_departamento;
    private List<Funcion> funciones;
    private List<Vacante> vacantes;
}