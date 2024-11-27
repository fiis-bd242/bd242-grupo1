package com.example.yapeback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;

@Data
public class Idioma {
    private Long id_idioma;
    private String nombre;

    @JsonIgnore
    private List<Postulante> postulantes;
}