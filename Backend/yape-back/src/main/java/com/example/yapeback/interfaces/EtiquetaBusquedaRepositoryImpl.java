package com.example.yapeback.interfaces;

import com.example.yapeback.model.EtiquetaBusqueda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EtiquetaBusquedaRepositoryImpl implements EtiquetaBusquedaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EtiquetaBusqueda> buscarEtiqueta(String busqueda) {
        // Establece el umbral de similitud
        jdbcTemplate.execute("SET pg_trgm.similarity_threshold = 0.023");
        String sql = """
            SELECT 
                cod_etiqueta AS codigoEtiqueta,
                categoria,
                tipologia,
                funcionalidad,
                descripcion,
                similarity(descripcion, ?) AS descripcionSimilarity
            FROM 
                tipificacion
            WHERE 
                descripcion % ? OR categoria % ? OR tipologia % ? OR funcionalidad % ?
            ORDER BY 
                descripcionSimilarity DESC
            LIMIT 10
            """;

        return jdbcTemplate.query(sql, new Object[]{busqueda, busqueda, busqueda, busqueda, busqueda},
                new BeanPropertyRowMapper<>(EtiquetaBusqueda.class));
    }
}
