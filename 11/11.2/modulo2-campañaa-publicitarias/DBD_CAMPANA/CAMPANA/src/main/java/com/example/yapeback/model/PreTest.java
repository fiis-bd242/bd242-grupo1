package com.example.yapeback.model;

import lombok.Data;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
@Data
public class PreTest {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pretest;
    private String audienciaA;
    private String audienciaB;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_fin;
    private String resultado;
    private Integer cod_prototipo;
    private Integer id_empleado;
}
