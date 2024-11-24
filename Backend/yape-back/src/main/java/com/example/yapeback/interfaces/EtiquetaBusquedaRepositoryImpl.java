package com.example.yapeback.interfaces;

import com.example.yapeback.model.EtiquetaBusqueda;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EtiquetaBusquedaRepositoryImpl implements EtiquetaBusquedaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<EtiquetaBusqueda> buscarEtiqueta(String busqueda, String tipologia, String funcionalidad, String motivo) {
        // Construcción de la consulta dinámica
        StringBuilder sql = new StringBuilder("""
            SELECT 
                cod_etiqueta AS codigoEtiqueta,
                descripcion,
                funcionalidad,
                tipologia,
                motivo,
                categoria,
                similarity(descripcion, ?) AS descripcionSimilarity
            FROM 
                tipificacion
            WHERE 
                similarity(descripcion, ?) > 0.023
            """);

        // Lista para los parámetros de la consulta
        List<Object> params = new ArrayList<>();
        params.add(busqueda); // Primer parámetro para similarity
        params.add(busqueda); // Segundo parámetro para el filtro de umbral

        // Agregar filtros dinámicos
        if (tipologia != null && !tipologia.isEmpty()) {
            sql.append(" AND similarity(tipologia, ?) > 0.15 ");
            params.add(tipologia);
        }
        if (funcionalidad != null && !funcionalidad.isEmpty()) {
            sql.append(" AND similarity(funcionalidad, ?) > 0.023 ");
            params.add(funcionalidad);
        }
        if (motivo != null && !motivo.isEmpty()) {
            sql.append(" AND similarity(motivo, ?) > 0.023 "); // Cambiado para usar la columna "motivo"
            params.add(motivo);
        }

        // Ordenar resultados y limitar
        sql.append("""
            ORDER BY 
                descripcionSimilarity DESC
            LIMIT 10
            """);

        // Ejecutar consulta
        try {
            return jdbcTemplate.query(sql.toString(), params.toArray(),
                    new BeanPropertyRowMapper<>(EtiquetaBusqueda.class));
        } catch (Exception e) {
            // Log del error
            System.err.println("Error en la búsqueda de etiquetas: " + e.getMessage());
            return new ArrayList<>(); // Devuelve lista vacía si ocurre algún error
        }
    }
}
