//package com.example.yapeback.migration;
//
//import org.neo4j.driver.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//@Component
//public class DataMigrationRunner implements CommandLineRunner {
//
//    @Override
//    public void run(String... args) throws Exception {
//        // Configura la conexión a PostgreSQL
//        String pgUrl = "jdbc:postgresql://localhost:5432/postgres";
//        String pgUser = "postgres";
//        String pgPassword = "12345";
//
//        // Configura la conexión a Neo4j
//        String neo4jUrl = "bolt://localhost:7687";
//        String neo4jUser = "neo4j";
//        String neo4jPassword = "12345678";
//
//        try (
//                Connection pgConn = DriverManager.getConnection(pgUrl, pgUser, pgPassword);
//                Driver neo4jDriver = GraphDatabase.driver(neo4jUrl, AuthTokens.basic(neo4jUser, neo4jPassword));
//                Session neo4jSession = neo4jDriver.session()
//        ) {
//            // Extraer datos de PostgreSQL y cargar en Neo4j
//            DataMigration.migrateDepartamento(pgConn, neo4jSession);
//            DataMigration.migratePuesto(pgConn, neo4jSession);
//            DataMigration.migrateVacante(pgConn, neo4jSession);
//            DataMigration.migrateEmpleado(pgConn, neo4jSession);
//            DataMigration.migratePostulante(pgConn, neo4jSession);
//            DataMigration.migrateEntrevista(pgConn, neo4jSession);
//            DataMigration.calculateRelativeScore(neo4jSession);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}