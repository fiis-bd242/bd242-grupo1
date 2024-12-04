package com.example.backend_capacitaciones_yape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:3000")
public class ResultadoTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/{id_test}/notas")
    public ResponseEntity<List<Map<String, Object>>> getNotas(
            @PathVariable("id_test") String idTest,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer puntaje) {

        List<Map<String, Object>> resultados = new ArrayList<>();
        int idTestInt;
        try {
            idTestInt = Integer.parseInt(idTest);
        } catch (NumberFormatException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", "El parámetro id_test no es válido.");
            return ResponseEntity.status(400).body(Collections.singletonList(error));
        }

        StringBuilder sql = new StringBuilder("""
                SELECT 
                    e.id_empleado AS codigo,
                    e.nombre || ' ' || e.apellido AS empleado,
                    rt.puntaje AS puntaje,
                    rt.comentario AS comentario
                FROM 
                    resultado_test rt
                INNER JOIN 
                    empleado e ON rt.id_empleado = e.id_empleado
                WHERE 
                    rt.id_test = ? 
                """);

        if (nombre != null && !nombre.isEmpty()) {
            sql.append(" AND e.nombre || ' ' || e.apellido ILIKE ? ");
        }
        if (puntaje != null) {
            // Aseguramos que el puntaje sea considerado en la consulta
            sql.append(" AND rt.puntaje = ? ");
        }

        sql.append(" ORDER BY e.nombre || ' ' || e.apellido");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            stmt.setInt(paramIndex++, idTestInt);

            if (nombre != null && !nombre.isEmpty()) {
                stmt.setString(paramIndex++, "%" + nombre + "%");
            }
            if (puntaje != null) {
                stmt.setInt(paramIndex, puntaje);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> row = new HashMap<>();
                    row.put("codigo", rs.getInt("codigo"));
                    row.put("empleado", rs.getString("empleado"));
                    row.put("puntaje", rs.getInt("puntaje"));
                    row.put("comentario", rs.getString("comentario"));
                    resultados.add(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500)
                                 .body(Collections.singletonList(Collections.singletonMap("error", "Ocurrió un error al recuperar los datos.")));
        }

        return ResponseEntity.ok(resultados);
    }

    @GetMapping("/{id_test}/estadisticas")
    public ResponseEntity<Map<String, Object>> getEstadisticas(@PathVariable("id_test") int idTest) {
        Map<String, Object> estadisticas = new HashMap<>();

        String sql = """
                SELECT 
                    COUNT(*) AS total_empleados,
                    AVG(rt.puntaje) AS promedio_puntaje,
                    100.0 * COUNT(CASE WHEN rt.puntaje >= 12 THEN 1 END) / COUNT(*) AS porcentaje_destacados
                FROM 
                    resultado_test rt
                WHERE 
                    rt.id_test = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTest);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    estadisticas.put("total_empleados", rs.getInt("total_empleados"));
                    estadisticas.put("promedio_puntaje", rs.getDouble("promedio_puntaje"));
                    estadisticas.put("porcentaje_destacados", rs.getDouble("porcentaje_destacados"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Collections.singletonMap("error", "Error al obtener estadísticas"));
        }

        return ResponseEntity.ok(estadisticas);
    }
}