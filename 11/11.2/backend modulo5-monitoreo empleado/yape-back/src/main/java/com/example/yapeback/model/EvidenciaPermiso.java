package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data 
public class EvidenciaPermiso {
    private Long idEvidencia;
    private String nombreArchivo;
    private String rutaArchivo; // Ruta o URL donde se almacena el archivo
    private LocalDateTime fechaSubida;
    private Long idPermiso; // Relación con Permiso
}
