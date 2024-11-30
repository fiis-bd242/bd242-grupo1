package com.example.yapeback.model;

import lombok.Data;
import java.time.LocalDateTime;
@Data
public class ClienteExterno {
    private Long id_cliente;
    private String direccion;
    private String nombreCli;
    private LocalDateTime fechaRegistro;
    private String apellidoCli;
    private String empresaCli;
    private String correoCli;
}
