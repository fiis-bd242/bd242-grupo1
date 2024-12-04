package pe.edu.uni.yapenosql.model;

import lombok.Data;

@Data
public class ClienteTicket {
    private String empresaCliente;
    private Long clientesActivos;
    private String periodo;
}
