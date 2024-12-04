package com.example.backend_capacitaciones_yape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Map;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/{id_test}")
    public ResponseEntity<Map<String, Object>> getTestById(@PathVariable("id_test") int idTest) {
        String sql = """
            SELECT 
                t.nombre AS test_nombre,
                t.descripcion AS test_descripcion,
                t.fecha AS test_fecha,
                t.estado AS test_estado,
                c.nombre AS capacitacion_nombre
            FROM Test t
            INNER JOIN Modulo m ON t.ID_modulo = m.ID_modulo
            INNER JOIN Capacitacion c ON m.ID_capacitacion = c.ID_capacitacion
            WHERE t.ID_test = ?;
        """;

        try {
            // Ejecutar la consulta y devolver el resultado como un Map
            Map<String, Object> result = jdbcTemplate.queryForMap(sql, idTest);
            return ResponseEntity.ok(result);
        } catch (EmptyResultDataAccessException e) {
            // Manejar el caso en el que no se encuentra el test
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Test no encontrado"));
        } catch (Exception e) {
            // Manejar otros errores inesperados
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Error interno del servidor"));
        }
    }
}