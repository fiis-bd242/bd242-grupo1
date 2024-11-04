package com.example.yapeback.interfaces;

import com.example.yapeback.model.Indicador;
import com.example.yapeback.model.TipoEntrevista;
import com.example.yapeback.model.TipoEntrevistaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoEntrevistaRepositoryImpl implements TipoEntrevistaRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TipoEntrevistaRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<TipoEntrevistaDTO> findAllIdAndNombres() {
        String sql = "SELECT id_tipo_entrevista, nombre FROM tipo_entrevista";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            TipoEntrevistaDTO dto = new TipoEntrevistaDTO();
            dto.setId(rs.getLong("id_tipo_entrevista"));
            dto.setNombre(rs.getString("nombre"));
            return dto;
        });
    }

    @Override
    public boolean existsById(Long id) {
        String sql = "SELECT COUNT(*) FROM tipo_entrevista WHERE id_tipo_entrevista = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, id);
        return count != null && count > 0;
    }

    @Override
    public List<Indicador> findIndicadoresByTipoId(Long idTipoEntrevista) {
        String sql = "SELECT * FROM indicador WHERE id_tipo_entrevista = ?";
        return jdbcTemplate.query(sql, new IndicadorRowMapper(), idTipoEntrevista);
    }

    @Override
    public List<String> findAllNombres() {
        String sql = "SELECT nombre FROM tipo_entrevista";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}