package pe.edu.uni.yapenosql.model;

import lombok.Data;

@Data
public class EmpleadoTicket {
    private String nombreEmpleado;
    private Long volumenTicketsGestionados;
    private String periodo;
}
