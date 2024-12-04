package com.example.backend_capacitaciones_yape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class CapacitacionDetalleController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/api/mis-capacitaciones/{slug}")
    public ResponseEntity<Map<String, Object>> obtenerCapacitacionConDetalle(@PathVariable String slug) {
        try {
            // Consultar la información de la capacitación
            String sqlCapacitacion = "SELECT id_capacitacion, nombre AS capacitacion_nombre, descripcion AS capacitacion_desc " +
                                     "FROM capacitacion WHERE LOWER(REPLACE(nombre, ' ', '-')) = ?";
            Map<String, Object> capacitacion = jdbcTemplate.queryForMap(sqlCapacitacion, slug.toLowerCase());

            // Consultar los módulos, recursos y tests desde la vista
            String sqlModulos = "SELECT * FROM vista_capacitacion_detalle WHERE id_capacitacion = ?";
            List<Map<String, Object>> resultados = jdbcTemplate.queryForList(sqlModulos, capacitacion.get("id_capacitacion"));

            // Agrupar recursos y tests por módulos
            Map<Long, Map<String, Object>> modulos = new LinkedHashMap<>();
            for (Map<String, Object> fila : resultados) {
                Long idModulo = ((Integer) fila.get("id_modulo")).longValue();

                // Filtrar módulos inactivos
                if ("inactivo".equals(fila.get("modulo_estado"))) {
                    continue;  // No incluir módulos inactivos
                }

                // Si el módulo no existe en el mapa, lo creamos
                modulos.putIfAbsent(idModulo, new HashMap<String, Object>() {{
                    put("id_modulo", idModulo);
                    put("modulo_nombre", fila.get("modulo_nombre"));
                    put("modulo_orden", fila.get("modulo_orden"));
                    put("estado", fila.get("modulo_estado"));
                    put("recursos", new ArrayList<Map<String, Object>>());
                    put("tests", new ArrayList<Map<String, Object>>());
                }});

                // Agregar recurso
                if (fila.get("id_recurso") != null) {
                    Map<String, Object> recursoData = new HashMap<>();
                    recursoData.put("id_recurso", fila.get("id_recurso"));
                    recursoData.put("nombre", fila.get("recurso_nombre"));
                    recursoData.put("contenido", fila.get("recurso_contenido"));
                    recursoData.put("tipo", fila.get("recurso_tipo"));
                    recursoData.put("orden", fila.get("recurso_orden"));
                    recursoData.put("estado", fila.get("recurso_estado"));
                    ((List<Map<String, Object>>) modulos.get(idModulo).get("recursos")).add(recursoData);
                }

                // Agregar test
                if (fila.get("id_test") != null) {
                    Map<String, Object> testData = new HashMap<>();
                    testData.put("id_test", fila.get("id_test"));
                    testData.put("nombre", fila.get("test_nombre"));
                    testData.put("estado", fila.get("test_estado"));
                    ((List<Map<String, Object>>) modulos.get(idModulo).get("tests")).add(testData);
                }
            }

            // Filtrar los módulos con recursos y tests, eliminando aquellos vacíos
            List<Long> modulosAEliminar = new ArrayList<>();
            for (Map.Entry<Long, Map<String, Object>> mod : modulos.entrySet()) {
                Map<String, Object> modulo = mod.getValue();
                if (((List<Map<String, Object>>) modulo.get("recursos")).isEmpty() && 
                    ((List<Map<String, Object>>) modulo.get("tests")).isEmpty()) {
                    modulosAEliminar.add(mod.getKey());
                }
            }

            // Eliminar los módulos que están vacíos
            for (Long idModulo : modulosAEliminar) {
                modulos.remove(idModulo);
            }

            // Construir la respuesta final
            capacitacion.put("modulos", new ArrayList<>(modulos.values()));
            return ResponseEntity.ok(capacitacion);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(Map.of("error", "Error al procesar la solicitud"));
        }
    }
}