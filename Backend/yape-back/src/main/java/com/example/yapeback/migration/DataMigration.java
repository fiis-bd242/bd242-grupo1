package com.example.yapeback.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.neo4j.driver.*;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class DataMigration {

    public static void main(String[] args) {
        // Configura la conexión a PostgreSQL
        String pgUrl = "jdbc:postgresql://localhost:5432/postgres";
        String pgUser = "postgres";
        String pgPassword = "contraseña";

        // Configura la conexión a Neo4j
        String neo4jUrl = "bolt://localhost:7687";
        String neo4jUser = "neo4j";
        String neo4jPassword = "12345678";

        try (
                Connection pgConn = DriverManager.getConnection(pgUrl, pgUser, pgPassword);
                Driver neo4jDriver = GraphDatabase.driver(neo4jUrl, AuthTokens.basic(neo4jUser, neo4jPassword));
                Session neo4jSession = neo4jDriver.session()
        ) {
            // Extraer datos de PostgreSQL y cargar en Neo4j
            migrateDepartamento(pgConn, neo4jSession);
            migratePuesto(pgConn, neo4jSession);
            migrateVacante(pgConn, neo4jSession);
            migrateEmpleado(pgConn, neo4jSession);
            migratePostulante(pgConn, neo4jSession);
            migrateEntrevista(pgConn, neo4jSession);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void migrateDepartamento(Connection pgConn, Session neo4jSession) throws Exception {
        Statement stmt = pgConn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Departamento");

        while (rs.next()) {
            int id = rs.getInt("id_departamento");
            String descripcion = rs.getString("descripcion");
            Integer idPadre = rs.getObject("id_departamento_padre", Integer.class);

            // Usar MERGE para evitar duplicados
            String cypher = "MERGE (d:Departamento {id: $id}) " +
                    "ON CREATE SET d.descripcion = $descripcion " +
                    "ON MATCH SET d.descripcion = $descripcion";
            neo4jSession.run(cypher, Values.parameters("id", id, "descripcion", descripcion));

            // Usar MERGE para la relación también
            if (idPadre != null) {
                String relCypher = "MATCH (d1:Departamento {id: $id}), (d2:Departamento {id: $idPadre}) " +
                        "MERGE (d1)-[:ES_PADRE_DE]->(d2)";
                neo4jSession.run(relCypher, Values.parameters("id", id, "idPadre", idPadre));
            }
        }
    }

    public static void migratePuesto(Connection pgConn, Session neo4jSession) throws Exception {
        Statement stmt = pgConn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Puesto");

        while (rs.next()) {
            int id = rs.getInt("id_puesto");
            String nombre = rs.getString("nombre");
            float paga = rs.getFloat("paga");
            int idDepartamento = rs.getInt("id_departamento");

            // Usar MERGE para evitar duplicados
            String cypher = "MERGE (p:Puesto {id: $id}) " +
                    "ON CREATE SET p.nombre = $nombre, p.paga = $paga " +
                    "ON MATCH SET p.nombre = $nombre, p.paga = $paga";
            neo4jSession.run(cypher, Values.parameters("id", id, "nombre", nombre, "paga", paga));

            // Usar MERGE para la relación también
            String relCypher = "MATCH (d:Departamento {id: $idDepartamento}), (p:Puesto {id: $id}) " +
                    "MERGE (d)-[:TIENE]->(p)";
            neo4jSession.run(relCypher, Values.parameters("idDepartamento", idDepartamento, "id", id));
        }
    }

    public static void migrateVacante(Connection pgConn, Session neo4jSession) throws Exception {
        Statement stmt = pgConn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Vacante");

        while (rs.next()) {
            int id = rs.getInt("id_vacante");
            String estado = rs.getString("estado");
            String fechaInicio = rs.getString("fecha_inicio");
            String fechaFin = rs.getString("fecha_fin");
            String comentario = rs.getString("comentario");
            int cantidad = rs.getInt("cantidad");
            int idPuesto = rs.getInt("id_puesto");

            // Usar MERGE para evitar duplicados
            String cypher = "MERGE (v:Vacante {id: $id}) " +
                    "ON CREATE SET v.estado = $estado, v.fecha_inicio = $fechaInicio, " +
                    "v.fecha_fin = $fechaFin, v.comentario = $comentario, v.cantidad = $cantidad " +
                    "ON MATCH SET v.estado = $estado, v.fecha_inicio = $fechaInicio, " +
                    "v.fecha_fin = $fechaFin, v.comentario = $comentario, v.cantidad = $cantidad";
            neo4jSession.run(cypher, Values.parameters("id", id, "estado", estado, "fechaInicio", fechaInicio,
                    "fechaFin", fechaFin, "comentario", comentario, "cantidad", cantidad));

            // Usar MERGE para la relación también
            String relCypher = "MATCH (p:Puesto {id: $idPuesto}), (v:Vacante {id: $id}) " +
                    "MERGE (p)-[:OFRECE]->(v)";
            neo4jSession.run(relCypher, Values.parameters("idPuesto", idPuesto, "id", id));
        }
    }

    public static void migratePostulante(Connection pgConn, Session neo4jSession) throws Exception {
        Statement stmt = pgConn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Postulante");

        while (rs.next()) {
            int id = rs.getInt("id_postulante");
            String nombre = rs.getString("nombre");
            long telefono = rs.getLong("telefono");
            String correo = rs.getString("correo");
            int idVacante = rs.getInt("id_vacante");
            int puntaje = rs.getInt("puntaje");

            // Usar MERGE para evitar duplicados
            String cypher = "MERGE (p:Postulante {id: $id}) " +
                    "ON CREATE SET p.nombre = $nombre, p.telefono = $telefono, p.correo = $correo, p.puntaje = $puntaje " +
                    "ON MATCH SET p.nombre = $nombre, p.telefono = $telefono, p.correo = $correo, p.puntaje = $puntaje";
            neo4jSession.run(cypher, Values.parameters("id", id, "nombre", nombre, "telefono", telefono, "correo", correo, "puntaje", puntaje));

            // Usar MERGE para la relación también
            String relCypher = "MATCH (v:Vacante {id: $idVacante}), (p:Postulante {id: $id}) " +
                    "MERGE (v)-[:TIENE]->(p)";
            neo4jSession.run(relCypher, Values.parameters("idVacante", idVacante, "id", id));
        }
    }

    public static void migrateEntrevista(Connection pgConn, Session neo4jSession) throws Exception {
        Statement stmt = pgConn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Entrevista e " +
                "JOIN Postulante p ON e.id_postulante = p.id_postulante " +
                "JOIN Vacante v ON p.id_vacante = v.id_vacante " +
                "JOIN Puesto pu ON v.id_puesto = pu.id_puesto " +
                "JOIN Departamento d ON pu.id_departamento = d.id_departamento");

        while (rs.next()) {
            int id = rs.getInt("id_entrevista");
            String estado = rs.getString("estado");
            String fecha = rs.getString("fecha");
            int puntajeGeneral = rs.getInt("puntaje_general");
            int idPostulante = rs.getInt("id_postulante");
            int idEmpleado = rs.getInt("ID_empleado");
            String tipoEntrevista = rs.getString("tipo_entrevista");

            // Usar MERGE para evitar duplicados
            String cypher = "MERGE (e:Entrevista {id: $id}) " +
                    "ON CREATE SET e.estado = $estado, e.fecha = $fecha, e.puntaje_general = $puntajeGeneral, e.tipo_entrevista = $tipoEntrevista " +
                    "ON MATCH SET e.estado = $estado, e.fecha = $fecha, e.puntaje_general = $puntajeGeneral, e.tipo_entrevista = $tipoEntrevista";
            neo4jSession.run(cypher, Values.parameters("id", id, "estado", estado, "fecha", fecha, "puntajeGeneral", puntajeGeneral, "tipoEntrevista", tipoEntrevista));

            // Obtener el departamento
            String departamento = rs.getString("descripcion");  // descripción del departamento

            // Calcular el peso de la relación basado en el tipo de entrevista y departamento
            double weight = getWeight(tipoEntrevista, departamento);

            // Modificar las queries Cypher para incluir el peso en las relaciones
            String relCypherPostulante = "MATCH (p:Postulante {id: $idPostulante}), (e:Entrevista {id: $id}) " +
                    "MERGE (p)-[:TIENE {weight: $weight}]->(e)";
            neo4jSession.run(relCypherPostulante,
                    Values.parameters("idPostulante", idPostulante, "id", id, "weight", weight));

            String relCypherEmpleado = "MATCH (emp:Empleado {id: $idEmpleado}), (e:Entrevista {id: $id}) " +
                    "MERGE (emp)-[:REALIZA {weight: $weight}]->(e)";
            neo4jSession.run(relCypherEmpleado,
                    Values.parameters("idEmpleado", idEmpleado, "id", id, "weight", weight));
        }
    }

    public static void migrateEmpleado(Connection pgConn, Session neo4jSession) throws Exception {
        Statement stmt = pgConn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Empleado");

        while (rs.next()) {
            int id = rs.getInt("ID_empleado");
            String nombre = rs.getString("Nombre");
            String apellido = rs.getString("Apellido");
            String fechaNacimiento = rs.getString("fecha_nacimiento");
            String fechaIngreso = rs.getString("fecha_ingreso");
            String estado = rs.getString("Estado");
            String documentoIdentidad = rs.getString("Documento_identidad");
            String telefono = rs.getString("Telefono");
            int idPuesto = rs.getInt("id_puesto");

            // Usar MERGE para evitar duplicados
            String cypher = "MERGE (e:Empleado {id: $id}) " +
                    "ON CREATE SET e.nombre = $nombre, e.apellido = $apellido, e.fecha_nacimiento = $fechaNacimiento, " +
                    "e.fecha_ingreso = $fechaIngreso, e.estado = $estado, e.documento_identidad = $documentoIdentidad, e.telefono = $telefono " +
                    "ON MATCH SET e.nombre = $nombre, e.apellido = $apellido, e.fecha_nacimiento = $fechaNacimiento, " +
                    "e.fecha_ingreso = $fechaIngreso, e.estado = $estado, e.documento_identidad = $documentoIdentidad, e.telefono = $telefono";
            neo4jSession.run(cypher, Values.parameters("id", id, "nombre", nombre, "apellido", apellido, "fechaNacimiento", fechaNacimiento,
                    "fechaIngreso", fechaIngreso, "estado", estado, "documentoIdentidad", documentoIdentidad, "telefono", telefono));

            // Usar MERGE para la relación también
            String relCypher = "MATCH (p:Puesto {id: $idPuesto}), (e:Empleado {id: $id}) " +
                    "MERGE (p)-[:OCUPA]->(e)";
            neo4jSession.run(relCypher, Values.parameters("idPuesto", idPuesto, "id", id));
        }
    }

    public static void calculateRelativeScore(Session neo4jSession) {
        String query = "MATCH (p:Postulante)-[r1:TIENE]->(e:Entrevista)-[r2:REALIZA]->(emp:Empleado)-[:OCUPA]->(pu:Puesto)-[:TIENE]->(d:Departamento) " +
                "WITH p, e, d.descripcion AS departamento, e.tipo_entrevista AS tipo, e.puntaje_general AS puntaje, r1.weight as weight " +
                "RETURN p.id AS postulanteId, " +
                "       COLLECT({tipo: tipo, puntaje: puntaje, departamento: departamento, weight: weight}) AS entrevistas";

        org.neo4j.driver.Result result = neo4jSession.run(query);

        while (result.hasNext()) {
            org.neo4j.driver.Record record = result.next();
            int postulanteId = record.get("postulanteId").asInt();
            List<Map<String, Object>> entrevistas = record.get("entrevistas").asList(Value::asMap);

            double relativeScore = calculateWeightedAverage(entrevistas);
            String updateQuery = "MATCH (p:Postulante {id: $postulanteId}) SET p.puntaje_relativo = $relativeScore";
            neo4jSession.run(updateQuery, Values.parameters("postulanteId", postulanteId, "relativeScore", relativeScore));
        }
    }

    public static void calculateRelativeScoreForPostulante(Session neo4jSession, int postulanteId) {
        // Consulta corregida para reflejar las direcciones correctas de las relaciones
        String query = "MATCH (p:Postulante {id: $postulanteId})<-[:TIENE]-(v:Vacante)" +
                      "<-[:OFRECE]-(pu:Puesto)<-[:TIENE]-(d:Departamento) " +
                      "OPTIONAL MATCH (p)-[:TIENE]->(e:Entrevista) " +
                      "WHERE e.puntaje_general IS NOT NULL " +
                      "RETURN p.id AS postulanteId, " +
                      "       d.descripcion AS departamento, " +
                      "       COLLECT({ " +
                      "           tipo: e.tipo_entrevista, " +
                      "           puntaje: toInteger(e.puntaje_general), " +
                      "           departamento: d.descripcion " +
                      "       }) AS entrevistas";

        org.neo4j.driver.Result result = neo4jSession.run(query, Values.parameters("postulanteId", postulanteId));

        if (result.hasNext()) {
            org.neo4j.driver.Record record = result.next();
            List<Map<String, Object>> entrevistas = record.get("entrevistas").asList(Value::asMap);

            if (!entrevistas.isEmpty()) {
                double relativeScore = calculateWeightedAverage(entrevistas);

                // Debug
                System.out.println("Puntaje relativo calculado para postulante " + postulanteId + ": " + relativeScore);

                String updateQuery = "MATCH (p:Postulante {id: $postulanteId}) " +
                                   "SET p.puntaje_relativo = $relativeScore";
                neo4jSession.run(updateQuery,
                    Values.parameters("postulanteId", postulanteId, "relativeScore", relativeScore));
            } else {
                System.out.println("El postulante " + postulanteId + " no tiene entrevistas con puntaje_general válido.");
            }
        }
    }

    private static double calculateWeightedAverage(List<Map<String, Object>> entrevistas) {
        double totalScore = 0;
        double totalWeight = 0;

        for (Map<String, Object> entrevista : entrevistas) {
            String tipo = (String) entrevista.get("tipo");
            String departamento = (String) entrevista.get("departamento");

            // Convertir el puntaje de manera segura
            Object puntajeObj = entrevista.get("puntaje");
            double puntaje = 0.0;
            if (puntajeObj instanceof Number) {
                puntaje = ((Number) puntajeObj).doubleValue();
            }

            double weight = getWeight(tipo, departamento);

            // Debug
            System.out.println(String.format(
                "Entrevista - Tipo: %s, Departamento: %s, Puntaje: %.2f, Peso: %.2f",
                tipo, departamento, puntaje, weight
            ));

            totalScore += puntaje * weight;
            totalWeight += weight;
        }

        // Debug
        System.out.println("Total Score: " + totalScore);
        System.out.println("Total Weight: " + totalWeight);

        return totalWeight > 0 ? totalScore / totalWeight : 0.0;
    }

    private static double getWeight(String tipo, String departamento) {
        switch (departamento) {
            case "Tribu tecnológica":
                switch (tipo) {
                    case "Tecnica": return 2.0;
                    case "HR": return 0.8;
                    case "Manager": return 1.2;
                }
                break;

            case "Área de Marketing":
            case "Departamento de publicidad":
            case "Departamento de comunicación":
                switch (tipo) {
                    case "Tecnica": return 1.0;
                    case "HR": return 1.2;
                    case "Manager": return 1.8;
                }
                break;

            case "Área de People and Culture":
            case "Departamento de Contratación":
            case "Departamento de gestión":
                switch (tipo) {
                    case "Tecnica": return 0.8;
                    case "HR": return 2.0;
                    case "Manager": return 1.2;
                }
                break;

            case "Tribu de Experiencia":
                switch (tipo) {
                    case "Tecnica": return 1.8;
                    case "HR": return 1.0;
                    case "Manager": return 1.2;
                }
                break;

            case "Área de Data & Analitycs":
                switch (tipo) {
                    case "Tecnica": return 2.0;
                    case "HR": return 0.8;
                    case "Manager": return 1.2;
                }
                break;

            case "Área de Soporte":
                switch (tipo) {
                    case "Tecnica": return 1.5;
                    case "HR": return 1.0;
                    case "Manager": return 1.5;
                }
                break;

            case "Área comercial":
                switch (tipo) {
                    case "Tecnica": return 1.0;
                    case "HR": return 1.2;
                    case "Manager": return 1.8;
                }
                break;

            case "Gerencia de planeamiento":
            case "CEO":
                switch (tipo) {
                    case "Tecnica": return 1.0;
                    case "HR": return 1.0;
                    case "Manager": return 2.0;
                }
                break;
        }
        return 1.0; // Default weight para cualquier otro caso
    }
}