package com.example.yapeback.model;

import lombok.Data;
@Data
public class Mensaje {
    private Long idMensaje;
    private String contenido;
    private Long remitenteCliente;
    private Long remitenteAsesor;
}
