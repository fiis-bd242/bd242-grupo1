package com.example.yapeback.interfaces;

import com.example.yapeback.model.Observacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

@Repository
public class ObservacionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Observacion save(Observacion observacion) {
        String sql = "INSERT INTO observacion (id_entrevista, nombre, descripcion) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, observacion.getId_entrevista(), observacion.getNombre(), observacion.getDescripcion());
        return observacion;
    }

    public Observacion update(Observacion observacion) {
        String sql = "UPDATE observacion SET id_entrevista = ?, nombre = ?, descripcion = ? WHERE id_observacion = ?";
        jdbcTemplate.update(sql, observacion.getId_entrevista(), observacion.getNombre(), observacion.getDescripcion(), observacion.getId_observacion());
        return observacion;
    }

    public Optional<Observacion> findById(Long id) {
        String sql = "SELECT * FROM observacion WHERE id_observacion = ?";
        try {
            Observacion observacion = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Observacion.class), id);
            return Optional.ofNullable(observacion);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM observacion WHERE id_observacion = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Observacion> findByEntrevistaId(Long idEntrevista) {
        String sql = "SELECT * FROM observacion WHERE id_entrevista = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Observacion.class), idEntrevista);
    }

    public List<Observacion> findByPostulanteId(Long idPostulante) {
        String sql = "SELECT o.* FROM observacion o JOIN entrevista e ON o.id_entrevista = e.id_entrevista WHERE e.id_postulante = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Observacion.class), idPostulante);
    }
}