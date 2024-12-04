package com.example.backend_capacitaciones_yape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:3000")
public class CalculoTestController {

    @Autowired
    private DataSource dataSource;

    @PostMapping("/calculo/submit/{id_test}/{id_empleado}")
    public ResponseEntity<Map<String, Object>> calcularPuntaje(
            @RequestBody List<Map<String, Integer>> respuestas,
            @PathVariable int id_test,
            @PathVariable int id_empleado
    ) {
        int puntaje = 0;
        int puntajeMaximo = respuestas.size() * 5; // Cada pregunta correcta vale 5 puntos

        String sqlValidacion = "SELECT es_correcta FROM Alternativa WHERE ID_pregunta = ? AND ID_alternativa = ?";
        String sqlInsertResultado = "INSERT INTO Resultado_Test (puntaje, ID_empleado, ID_test) VALUES (?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmtValidacion = conn.prepareStatement(sqlValidacion);
             PreparedStatement stmtInsertResultado = conn.prepareStatement(sqlInsertResultado)) {

            // Validar las respuestas y calcular el puntaje
            for (Map<String, Integer> respuesta : respuestas) {
                int idPregunta = respuesta.get("ID_pregunta");
                int idAlternativa = respuesta.get("ID_alternativa");

                stmtValidacion.setInt(1, idPregunta);
                stmtValidacion.setInt(2, idAlternativa);

                try (ResultSet rs = stmtValidacion.executeQuery()) {
                    if (rs.next() && "correcto".equals(rs.getString("es_correcta"))) {
                        puntaje += 5; // Incrementar puntaje si es correcta
                    }
                }
            }

            // Insertar el resultado en la tabla Resultado_Test
            stmtInsertResultado.setInt(1, puntaje);
            stmtInsertResultado.setInt(2, id_empleado);
            stmtInsertResultado.setInt(3, id_test);

            stmtInsertResultado.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Error al calcular el puntaje y guardar el resultado"));
        }

        // Crear el resultado y retornarlo
        Map<String, Object> result = new HashMap<>();
        result.put("puntaje", puntaje);
        result.put("puntajeMaximo", puntajeMaximo);
        return ResponseEntity.ok(result);
    }
}