package com.example.yapeback.controller;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@RestController
public class DataVerificationController {

    private final DataSource dataSource;
    private final Driver neo4jDriver;

    @Autowired
    public DataVerificationController(DataSource dataSource) {
        this.dataSource = dataSource;
        // Initialize the Neo4j driver with authentication
        this.neo4jDriver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "12345678"));
    }

    @GetMapping("/verify/departamento")
    public Map<String, Integer> verifyDepartamento() throws Exception {
        Map<String, Integer> result = new HashMap<>();

        try (Connection pgConn = dataSource.getConnection();
             Session neo4jSession = neo4jDriver.session()) {

            // Verificar el número de registros en la tabla Departamento en PostgreSQL
            Statement stmt = pgConn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM Departamento");
            rs.next();
            int pgCount = rs.getInt("count");
            result.put("PostgreSQL_Departamento_Count", pgCount);

            // Verificar el número de nodos Departamento en Neo4j
            org.neo4j.driver.Result neo4jResult = neo4jSession.run("MATCH (d:Departamento) RETURN COUNT(d) AS count");
            int neo4jCount = neo4jResult.single().get("count").asInt();
            result.put("Neo4j_Departamento_Count", neo4jCount);
        }

        return result;
    }

    // Add similar methods for other tables
}