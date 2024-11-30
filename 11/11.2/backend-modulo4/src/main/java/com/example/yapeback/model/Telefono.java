package com.example.yapeback.model;

import lombok.Data;

@Data
public class Telefono {
    private Long idTelefonoCli;
    private String telefono;
    private Long idCliente;  // Representa el id_cliente desde ClienteExterno
}
