package com.example.yapeback.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.yapeback.interfaces.AudienciaInterface;
import com.example.yapeback.model.Audiencia;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;

@Service
public class AudienciaService implements AudienciaInterface{

    private final JdbcTemplate jdbcTemplate;

    public AudienciaService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Audiencia crearAudiencia(Audiencia audiencia) {
        String insertAudienciaSql = "INSERT INTO Audiencia (Edad_rango, Genero, Ubicacion) VALUES (?, ?, ?) RETURNING id_audiencia";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(insertAudienciaSql, new String[]{"id_audiencia"});
            ps.setString(1, audiencia.getEdad_rango());
            ps.setString(2, audiencia.getGenero());
            ps.setString(3, audiencia.getUbicacion());
            return ps;
        }, keyHolder);

        String generatedId = keyHolder.getKeyList().get(0).get("id_audiencia").toString();
        audiencia.setId_audiencia(generatedId);
        return audiencia;
    }
    @Override
    public Audiencia buscarOCrearAudiencia(Audiencia audiencia) {
        // Buscar si ya existe una audiencia con los mismos valores
        String buscarSql = "SELECT id_audiencia FROM Audiencia WHERE Edad_rango = ? AND Genero = ? AND Ubicacion = ?";
        List<Map<String, Object>> resultados = jdbcTemplate.queryForList(buscarSql,
                audiencia.getEdad_rango(), audiencia.getGenero(), audiencia.getUbicacion());

        if (!resultados.isEmpty()) {
            // Si existe, asignar el ID encontrado y devolver la audiencia
            String idAudiencia = resultados.get(0).get("id_audiencia").toString();
            audiencia.setId_audiencia(idAudiencia);
            return audiencia;
        }

        // Si no existe, crear una nueva audiencia
        return crearAudiencia(audiencia);
    }


}


