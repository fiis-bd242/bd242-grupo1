package pe.edu.uni.yapenosql.model;

import lombok.Data;

@Data
public class TicketDetallado {
    private String categoria;
    private String funcionalidad;
    private String motivo;
    private Long volumenTickets;
    private String periodo;
}
