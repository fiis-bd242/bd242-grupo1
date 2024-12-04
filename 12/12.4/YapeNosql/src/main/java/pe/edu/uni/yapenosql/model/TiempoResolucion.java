package pe.edu.uni.yapenosql.model;

import lombok.Data;

@Data
public class TiempoResolucion {
    private Long codEtiqueta;
    private Double tiempoResolucionPromedio;
    private String categoria;
    private String funcionalidad;
    private String motivo;
}
