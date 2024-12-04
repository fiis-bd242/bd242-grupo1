package pe.edu.uni.yapenosql.model;

import lombok.Data;

@Data
public class VolumenGeneral {
    private Long volumenTickets;  // Para contar los tickets, que es el volumen
    private String periodo;
}
