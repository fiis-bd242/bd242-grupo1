package com.example.yapeback.migration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.neo4j.driver.*;

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
        ResultSet rs = stmt.executeQuery("SELECT * FROM Entrevista");

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

            // Usar MERGE para la relación también
            String relCypherPostulante = "MATCH (p:Postulante {id: $idPostulante}), (e:Entrevista {id: $id}) " +
                    "MERGE (p)-[:TIENE]->(e)";
            neo4jSession.run(relCypherPostulante, Values.parameters("idPostulante", idPostulante, "id", id));

            // Usar MERGE para la relación también
            String relCypherEmpleado = "MATCH (emp:Empleado {id: $idEmpleado}), (e:Entrevista {id: $id}) " +
                    "MERGE (emp)-[:REALIZA]->(e)";
            neo4jSession.run(relCypherEmpleado, Values.parameters("idEmpleado", idEmpleado, "id", id));
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
}