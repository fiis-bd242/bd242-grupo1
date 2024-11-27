package com.example.yapeback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.List;

@Data
public class Beneficio {
    private Long id_beneficio;
    private String descripcion;

    @JsonIgnore
    private List<OfertaLaboral> ofertasLaborales;
}