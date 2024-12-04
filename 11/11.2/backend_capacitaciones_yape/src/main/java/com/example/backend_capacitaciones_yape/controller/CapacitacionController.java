package com.example.backend_capacitaciones_yape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

@RestController
public class CapacitacionController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Endpoint para obtener una lista de capacitaciones con filtros opcionales.
     *
     * @param idInstructor ID del instructor (requerido).
     * @param nombre       Nombre de la capacitación (opcional, filtro).
     * @param estado       Estado de la capacitación (opcional, filtro).
     * @return Lista de capacitaciones filtradas.
     */
    @GetMapping("/api/capacitaciones")
    public ResponseEntity<List<Map<String, Object>>> obtenerCapacitaciones(
        @RequestParam("idInstructor") int idInstructor,
        @RequestParam(value = "nombre", required = false) String nombre,
        @RequestParam(value = "estado", required = false) String estado) {

        // Validar el estado para que coincida con los valores esperados
        if (estado != null && !estado.isEmpty() &&
            !estado.equals("pendiente") &&
            !estado.equals("en_proceso") &&
            !estado.equals("finalizado")) {
            return ResponseEntity.badRequest().body(null); // Error si el estado no es válido
        }

        // Construir la consulta SQL con los filtros
        String sql = "SELECT c.id_capacitacion AS Cod, " +
                     "c.nombre AS Nombre, " +
                     "TO_CHAR(c.fecha_inicio, 'DD-MM-YYYY') AS \"Fecha de Inicio\", " +
                     "TO_CHAR(c.fecha_final, 'DD-MM-YYYY') AS \"Fecha Fin\", " +
                     "c.estado AS Estado " +
                     "FROM capacitacion c " +
                     "WHERE c.id_instructor = ? " +
                     "AND LOWER(c.nombre) LIKE LOWER('%' || ? || '%') " +
                     "AND c.estado = CAST(? AS estado_capacitacion)";

        // Ejecutar la consulta con los parámetros
        List<Map<String, Object>> capacitaciones = jdbcTemplate.queryForList(sql,
                idInstructor, nombre == null ? "" : nombre, estado);

        return ResponseEntity.ok(capacitaciones);
    }

    /**
     * Endpoint para obtener los detalles de una capacitación por su slug.
     *
     * @param slug Slug de la capacitación (nombre convertido a URL amigable).
     * @return Detalles de la capacitación.
     */
    @GetMapping("/api/capacitacion/{slug}")
    public ResponseEntity<Map<String, Object>> obtenerCapacitacionPorSlug(@PathVariable String slug) {
        // Construir la consulta SQL para obtener detalles por slug
        String sql = "SELECT id_capacitacion AS Cod, " +
                     "nombre, " +
                     "TO_CHAR(fecha_inicio, 'DD-MM-YYYY') AS fecha_inicio, " +
                     "TO_CHAR(fecha_final, 'DD-MM-YYYY') AS fecha_final, " +
                     "estado, " +
                     "descripcion " +
                     "FROM capacitacion " +
                     "WHERE LOWER(REPLACE(nombre, ' ', '-')) = LOWER(?)";

        // Ejecutar la consulta con el slug
        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sql, slug.toLowerCase());

        if (resultados.isEmpty()) {
            return ResponseEntity.notFound().build(); // Si no hay resultados, retornar 404
        }

        return ResponseEntity.ok(resultados.get(0)); // Retornar el primer resultado encontrado
    }
}