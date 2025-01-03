package com.example.yapeback.interfaces;

import com.example.yapeback.model.Entrevista;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@Repository
public class EntrevistaRepositoryImpl implements EntrevistaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Entrevista save(Entrevista entrevista) {
        String sql = "INSERT INTO entrevista (estado, fecha, puntaje_general, id_postulante, id_empleado, tipo_entrevista) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, entrevista.getEstado(), entrevista.getFecha(), entrevista.getPuntaje_general(), entrevista.getId_postulante(), entrevista.getId_empleado(), entrevista.getTipo_entrevista());
        return entrevista;
    }

    @Override
    public Entrevista update(Entrevista entrevista) {
        String sql = "UPDATE entrevista SET estado = ?, fecha = ?, puntaje_general = ?, id_postulante = ?, id_empleado = ?, tipo_entrevista = ? WHERE id_entrevista = ?";
        
        int rowsAffected = jdbcTemplate.update(sql, 
            entrevista.getEstado(),
            entrevista.getFecha(),
            entrevista.getPuntaje_general(),
            entrevista.getId_postulante(),
            entrevista.getId_empleado(),
            entrevista.getTipo_entrevista(),
            entrevista.getId_entrevista()
        );

        if (rowsAffected == 0) {
            throw new EmptyResultDataAccessException("Entrevista not found with id: " + entrevista.getId_entrevista(), 1);
        }

        return findById(entrevista.getId_entrevista()).orElseThrow(() -> 
            new EmptyResultDataAccessException("Failed to retrieve updated entrevista", 1));
    }

    @Override
    public Optional<Entrevista> findById(Long id) {
        String sql = "SELECT * FROM entrevista WHERE id_entrevista = ?";
        try {
            Entrevista entrevista = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Entrevista.class), id);
            return Optional.ofNullable(entrevista);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM entrevista WHERE id_entrevista = ?";
        jdbcTemplate.update(sql, id);
    }
}