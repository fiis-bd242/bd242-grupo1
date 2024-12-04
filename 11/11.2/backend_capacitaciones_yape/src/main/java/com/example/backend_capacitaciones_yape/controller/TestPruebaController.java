package com.example.backend_capacitaciones_yape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@CrossOrigin(origins = "http://localhost:3000")
public class TestPruebaController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/{id_test}/prueba")
    public ResponseEntity<Map<String, Object>> getPreguntas(@PathVariable("id_test") int idTest) {
        List<Map<String, Object>> preguntas = new ArrayList<>();
        
        // Consulta SQL para obtener las preguntas y alternativas del test
        String sql = """
                SELECT 
                    t.nombre AS nombre_test,
                    p.ID_pregunta,
                    p.titulo AS titulo_pregunta,
                    a.ID_alternativa,
                    a.contenido,
                    a.es_correcta
                FROM 
                    Test t
                JOIN 
                    Pregunta p ON t.ID_test = p.ID_test
                JOIN 
                    Alternativa a ON p.ID_pregunta = a.ID_pregunta
                WHERE 
                    t.ID_test = ?
                """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, idTest);  // Establecemos el ID del test en la consulta

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> pregunta = new HashMap<>();
                    pregunta.put("nombre_test", rs.getString("nombre_test"));
                    pregunta.put("ID_pregunta", rs.getInt("ID_pregunta"));
                    pregunta.put("titulo_pregunta", rs.getString("titulo_pregunta"));
                    pregunta.put("ID_alternativa", rs.getInt("ID_alternativa"));
                    pregunta.put("contenido", rs.getString("contenido"));
                    pregunta.put("es_correcta", rs.getString("es_correcta"));
                    preguntas.add(pregunta);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(Map.of("error", "Ocurrió un error al obtener las preguntas"));
        }

        // Formateamos las preguntas para devolverlas de una forma más manejable
        Map<String, Object> result = new HashMap<>();
        result.put("preguntas", preguntas);

        return ResponseEntity.ok(result);
    }
}
