// src/main/java/com/example/yapeback/model/Departamento.java
package com.example.yapeback.model;

import lombok.Data;
import java.util.List;

@Data
public class Departamento {
    private Long id_departamento;
    private String descripcion;
    private Long id_departamento_padre;
    private List<Departamento> subDepartamentos;
    private List<Puesto> puestos;
}
