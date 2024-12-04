package com.example.demo.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ReporteRepositoryImpl implements ReporteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> reporteDetallado() {
        // Consulta total de incidentes
        String totalSql = "SELECT COUNT(*) AS total FROM incidente";
        Long totalIncidentes = jdbcTemplate.queryForObject(totalSql, Long.class); // Se usa Long en lugar de Integer

        // Consulta por categoría
        String categoriaSql = "SELECT categoria, COUNT(*) AS total FROM incidente GROUP BY categoria";
        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(categoriaSql);

        // Lista para almacenar el reporte final
        List<Map<String, Object>> reporteFinal = new ArrayList<>();

        // Procesamos cada fila del resultado de la consulta
        for (Map<String, Object> row : resultados) {
            String categoria = (String) row.get("categoria");
            Long totalCategoria = (Long) row.get("total"); // Usamos Long aquí también
            double porcentaje = (totalCategoria.doubleValue() / totalIncidentes.doubleValue()) * 100;

            // Determinamos el grado de incidencia basado en el porcentaje
            String gradoIncidencia = porcentaje > 20 ? "Alto" : "Bajo";

            // Agregar los resultados a la lista del reporte
            Map<String, Object> resultado = Map.of(
                    "Categoria", categoria,
                    "Total", totalCategoria.intValue(),  // Convertimos el Long a int solo al agregarlo al reporte
                    "Porcentaje", String.format("%.2f", porcentaje),  // Formateamos el porcentaje con dos decimales
                    "Grado de Incidencia", gradoIncidencia
            );

            // Añadir el resultado al reporte final
            reporteFinal.add(resultado);
        }

        return reporteFinal;
    }
}
