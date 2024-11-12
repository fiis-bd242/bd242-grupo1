package com.example.yapeback.interfaces;

import com.example.yapeback.model.HistorialTipificacion;
import com.example.yapeback.model.Tipificacion;
import com.example.yapeback.model.TipificacionViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipificacionRepositoryImpl implements TipificacionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<TipificacionViewModel> obtenerListaTipificaciones() {
        String sql = """
        SELECT 
            t.cod_etiqueta, 
            t.fecha_creacion, 
            t.id_asignador AS responsable, 
            t.funcionalidad, 
            t.categoria, 
            t.tipologia, 
            ha.tipo_accion, 
            t.motivo 
        FROM 
            tipificacion t
        INNER JOIN 
            empleado e ON e.id_empleado = t.id_asignador
        INNER JOIN 
            historial_asignacion ha ON ha.tipo_accion = t.cod_etiqueta
    """;

        // Consulta para obtener la lista de tipificaciones
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            TipificacionViewModel tipificacion = new TipificacionViewModel();
            tipificacion.setCodEtiqueta(rs.getLong("cod_etiqueta"));
            tipificacion.setFechaCreacion(rs.getTimestamp("fecha_creacion").toLocalDateTime());
            tipificacion.setResponsable(rs.getString("responsable"));
            tipificacion.setFuncionalidad(rs.getString("funcionalidad"));
            tipificacion.setCategoria(rs.getString("categoria"));
            tipificacion.setTipologia(rs.getString("tipologia"));
            tipificacion.setTipoAccion(rs.getString("tipo_accion"));
            tipificacion.setMotivo(rs.getString("motivo"));
            return tipificacion;
        });
    }


    @Override
    public void crearTipificacion(Tipificacion tipificacion) {
        String sql = """
        INSERT INTO tipificacion (cod_etiqueta, fecha_creacion, funcionalidad, tipologia, motivo, categoria, descripcion)
                VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?, ?)
    """;

        jdbcTemplate.update(sql,
                tipificacion.getCodEtiqueta(),
                tipificacion.getFuncionalidad(),
                tipificacion.getTipologia(),
                tipificacion.getMotivo(),
                tipificacion.getCategoria(),
                tipificacion.getDescripcion()
        );
    }
    @Override
    public List<HistorialTipificacion> obtenerHistorialTipificaciones() {
        String sql = """
        SELECT 
            ha.fecha_accion AS fecha,
            t.id_asignador AS responsable,
            t.categoria, 
            t.funcionalidad,
            t.motivo,
            t.tipologia,
            t.cod_etiqueta
        FROM 
            tipificacion t
        INNER JOIN 
            empleado e ON e.id_empleado = t.id_asignador
        INNER JOIN 
            historial_asignacion ha ON ha.tipo_accion = t.cod_etiqueta
    """;

        // Ejecutar la consulta y mapear los resultados a objetos HistorialTipificacion
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            HistorialTipificacion historial = new HistorialTipificacion();
            historial.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
            historial.setResponsable(rs.getString("responsable"));
            historial.setCategoria(rs.getString("categoria"));
            historial.setFuncionalidad(rs.getString("funcionalidad"));
            historial.setMotivo(rs.getString("motivo"));
            historial.setTipologia(rs.getString("tipologia"));
            historial.setCodEtiqueta(rs.getLong("cod_etiqueta"));
            return historial;
        });
    }


}
