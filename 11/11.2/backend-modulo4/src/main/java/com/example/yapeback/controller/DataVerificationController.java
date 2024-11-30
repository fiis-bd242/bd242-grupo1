//package com.example.yapeback.controller;
//
//import com.example.yapeback.migration.DataMigration;
//import org.neo4j.driver.AuthTokens;
//import org.neo4j.driver.Driver;
//import org.neo4j.driver.GraphDatabase;
//import org.neo4j.driver.Session;
//import org.neo4j.driver.Values;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//public class DataVerificationController {
//
//    private final DataSource dataSource;
//    private final Driver neo4jDriver;
//
//    @Autowired
//    public DataVerificationController(DataSource dataSource) {
//        this.dataSource = dataSource;
//        this.neo4jDriver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
//    }
//
//    @GetMapping("/verify/departamento")
//    public Map<String, Integer> verifyDepartamento() throws Exception {
//        Map<String, Integer> result = new HashMap<>();
//
//        try (var pgConn = dataSource.getConnection();
//             var neo4jSession = neo4jDriver.session()) {
//
//            var stmt = pgConn.createStatement();
//            var rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM Departamento");
//            rs.next();
//            int pgCount = rs.getInt("count");
//            result.put("PostgreSQL_Departamento_Count", pgCount);
//
//            var neo4jResult = neo4jSession.run("MATCH (d:Departamento) RETURN COUNT(d) AS count");
//            int neo4jCount = neo4jResult.single().get("count").asInt();
//            result.put("Neo4j_Departamento_Count", neo4jCount);
//        }
//
//        return result;
//    }
//
//    @GetMapping("/calculate/relative-score")
//    public String calculateRelativeScore() {
//        try (var neo4jSession = neo4jDriver.session()) {
//            DataMigration.calculateRelativeScore(neo4jSession);
//            return "Relative scores calculated successfully.";
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error calculating relative scores.";
//        }
//    }
//
//    @GetMapping("/calculate/relative-score/postulante")
//    public String calculateRelativeScoreForPostulante(@RequestParam int postulanteId) {
//        try (var neo4jSession = neo4jDriver.session()) {
//            DataMigration.calculateRelativeScoreForPostulante(neo4jSession, postulanteId);
//            return "Relative score calculated successfully for postulante with ID: " + postulanteId;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Error calculating relative score for postulante with ID: " + postulanteId;
//        }
//    }
//
//    @GetMapping("/get/relative-score/postulante")
//    public Map<String, Object> getRelativeScoreForPostulante(@RequestParam int postulanteId) {
//        Map<String, Object> result = new HashMap<>();
//        try (var neo4jSession = neo4jDriver.session()) {
//            // Verificar primero si el postulante existe y tiene entrevistas
//            String checkQuery = "MATCH (p:Postulante {id: $postulanteId}) " +
//                              "OPTIONAL MATCH (p)-[:TIENE]->(e:Entrevista) " +
//                              "WITH p, COUNT(e) as numEntrevistas " +
//                              "RETURN p.nombre as nombre, numEntrevistas";
//
//            var checkResult = neo4jSession.run(checkQuery, Values.parameters("postulanteId", postulanteId));
//
//            if (checkResult.hasNext()) {
//                var record = checkResult.next();
//                String nombre = record.get("nombre").asString();
//                int numEntrevistas = record.get("numEntrevistas").asInt();
//
//                result.put("postulanteId", postulanteId);
//                result.put("nombre", nombre);
//
//                if (numEntrevistas > 0) {
//                    // Calcular y obtener el puntaje
//                    DataMigration.calculateRelativeScoreForPostulante(neo4jSession, postulanteId);
//
//                    var scoreQuery = "MATCH (p:Postulante {id: $postulanteId}) " +
//                                   "RETURN COALESCE(p.puntaje_relativo, 0) as score";
//                    var scoreResult = neo4jSession.run(scoreQuery, Values.parameters("postulanteId", postulanteId));
//
//                    if (scoreResult.hasNext()) {
//                        result.put("relativeScore", scoreResult.next().get("score").asDouble());
//                        result.put("numEntrevistas", numEntrevistas);
//                    }
//                } else {
//                    result.put("error", "No interviews found for postulante");
//                }
//            } else {
//                result.put("error", "Postulante not found");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.put("error", "Error: " + e.getMessage());
//            result.put("stackTrace", e.getStackTrace());
//        }
//        return result;
//    }
//
//    @GetMapping("/verify/weights")
//    public Map<String, Object> verifyRelationshipWeights() {
//        Map<String, Object> result = new HashMap<>();
//
//        try (var neo4jSession = neo4jDriver.session()) {
//            String query = "MATCH ()-[r:TIENE|REALIZA]->() " +
//                    "RETURN type(r) as type, r.weight as weight, count(*) as count";
//            var neo4jResult = neo4jSession.run(query);
//
//            while (neo4jResult.hasNext()) {
//                var record = neo4jResult.next();
//                result.put(record.get("type").asString(),
//                        Map.of("weight", record.get("weight").asDouble(),
//                                "count", record.get("count").asInt()));
//            }
//        }
//        return result;
//    }
//
//    @GetMapping("/verify/weights/detailed")
//    public List<Map<String, Object>> verifyWeightsDetailed() {
//        List<Map<String, Object>> result = new ArrayList<>();
//
//        try (var neo4jSession = neo4jDriver.session()) {
//            String query = "MATCH (p:Postulante)-[r1:TIENE]->(e:Entrevista)-[r2:REALIZA]->(emp:Empleado) " +
//                    "RETURN p.nombre as postulante, " +
//                    "e.tipo_entrevista as tipo, " +
//                    "emp.nombre as empleado, " +
//                    "r1.weight as peso_relacion_tiene, " +
//                    "r2.weight as peso_relacion_realiza";
//
//            var neo4jResult = neo4jSession.run(query);
//
//            while (neo4jResult.hasNext()) {
//                var record = neo4jResult.next();
//                Map<String, Object> row = new HashMap<>();
//                row.put("postulante", record.get("postulante").asString());
//                row.put("tipo_entrevista", record.get("tipo").asString());
//                row.put("empleado", record.get("empleado").asString());
//                row.put("peso_tiene", record.get("peso_relacion_tiene").asDouble());
//                row.put("peso_realiza", record.get("peso_relacion_realiza").asDouble());
//                result.add(row);
//            }
//        }
//        return result;
//    }
//
//    @GetMapping("/debug/relative-score/postulante")
//    public Map<String, Object> debugRelativeScoreForPostulante(@RequestParam int postulanteId) {
//        Map<String, Object> result = new HashMap<>();
//        try (var neo4jSession = neo4jDriver.session()) {
//            String query = "MATCH (p:Postulante {id: $postulanteId}) RETURN p";
//            var neo4jResult = neo4jSession.run(query, Values.parameters("postulanteId", postulanteId));
//            if (neo4jResult.hasNext()) {
//                var record = neo4jResult.next();
//                var postulanteNode = record.get("p").asNode();
//                result.put("postulanteId", postulanteId);
//                result.put("postulante", postulanteNode.asMap());
//                if (postulanteNode.containsKey("puntaje_relativo")) {
//                    result.put("relativeScore", postulanteNode.get("puntaje_relativo").asDouble());
//                } else {
//                    result.put("error", "puntaje_relativo property not found");
//                }
//            } else {
//                result.put("error", "Postulante not found");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.put("error", "Error retrieving relative score for postulante with ID: " + postulanteId);
//        }
//        return result;
//    }
//
//    @GetMapping("/debug/entrevistas/postulante")
//    public Map<String, Object> debugEntrevistasPostulante(@RequestParam int postulanteId) {
//        Map<String, Object> result = new HashMap<>();
//        List<Map<String, Object>> entrevistas = new ArrayList<>();
//
//        try (var neo4jSession = neo4jDriver.session()) {
//            String query = "MATCH (p:Postulante {id: $postulanteId})-[:TIENE]->(e:Entrevista) " +
//                          "MATCH (v:Vacante)-[:TIENE]->(p) " +
//                          "MATCH (pu:Puesto)-[:OFRECE]->(v) " +
//                          "MATCH (d:Departamento)-[:TIENE]->(pu) " +
//                          "RETURN e.tipo_entrevista as tipo, " +
//                          "       e.puntaje_general as puntaje, " +
//                          "       d.descripcion as departamento";
//
//            var neo4jResult = neo4jSession.run(query, Values.parameters("postulanteId", postulanteId));
//
//            while (neo4jResult.hasNext()) {
//                var record = neo4jResult.next();
//                Map<String, Object> entrevista = new HashMap<>();
//                entrevista.put("tipo", record.get("tipo").asString());
//                entrevista.put("puntaje", record.get("puntaje").asInt());
//                entrevista.put("departamento", record.get("departamento").asString());
//                entrevistas.add(entrevista);
//            }
//
//            result.put("postulanteId", postulanteId);
//            result.put("entrevistas", entrevistas);
//        } catch (Exception e) {
//            e.printStackTrace();
//            result.put("error", "Error: " + e.getMessage());
//        }
//        return result;
//    }
//}