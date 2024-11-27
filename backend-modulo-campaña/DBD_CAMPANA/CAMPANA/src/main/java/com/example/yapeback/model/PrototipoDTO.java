package com.example.yapeback.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PrototipoDTO {
    private Integer cod_prototipo;
    private LocalDateTime fecha_estado;
    private String autor; 
    private String estado_prot;

    
}
