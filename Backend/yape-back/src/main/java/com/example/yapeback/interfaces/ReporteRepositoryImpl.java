package com.example.yapeback.interfaces;

import com.example.yapeback.model.CantidadTipificaciones;
import com.example.yapeback.model.FrecuenciaReasignacion;
import com.example.yapeback.model.FrecuenciaModificaciones;
import com.example.yapeback.model.ReporteAnalistaSugerenciasCorrecciones;
import com.example.yapeback.model.ReporteAnalistaTasaAdopcion;
import com.example.yapeback.model.ReporteBusiness;
import com.example.yapeback.model.TiempoPromedioResolucion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReporteRepositoryImpl implements ReporteRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<FrecuenciaReasignacion> obtenerFrecuenciaReasignacion() {
        String sql = """
            SELECT 
                t.cod_etiqueta, 
                COUNT(*) AS frecuencia_reasignacion
            FROM 
                historial_asignacion h
            JOIN 
                tipificacion t ON h.cod_etiqueta = t.cod_etiqueta
            WHERE 
                h.tipo_accion IN ('sugerencia', 'edicion')
                AND EXTRACT(YEAR FROM h.fecha_accion) = 2023
            GROUP BY 
                t.cod_etiqueta
            ORDER BY 
                frecuencia_reasignacion DESC
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            FrecuenciaReasignacion fr = new FrecuenciaReasignacion();
            fr.setCodEtiqueta(rs.getLong("cod_etiqueta"));
            fr.setFrecuenciaReasignacion(rs.getInt("frecuencia_reasignacion"));
            return fr;
        });
    }
    @Override
    public List<TiempoPromedioResolucion> obtenerTiempoPromedioResolucion() {
        String sql = """
            SELECT 
                t.cod_etiqueta, 
                AVG(EXTRACT(EPOCH FROM (c.fecha_fin - c.fecha_inicio)) / 3600) AS tiempo_promedio_resolucion_horas
            FROM 
                conversacion c
            JOIN 
                Ticket_asig_tip t ON c.cod_ticket = t.cod_ticket_asig
            JOIN 
                Estado_ticket_asig eta ON t.id_estado = eta.id_estado
            WHERE 
                eta.id_estado = 3
                AND EXTRACT(YEAR FROM c.fecha_inicio) = 2023
            GROUP BY 
                t.cod_etiqueta
            ORDER BY 
                tiempo_promedio_resolucion_horas
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            TiempoPromedioResolucion tr = new TiempoPromedioResolucion();
            tr.setCodEtiqueta(rs.getLong("cod_etiqueta"));
            tr.setTiempoPromedioResolucionHoras(rs.getDouble("tiempo_promedio_resolucion_horas"));
            return tr;
        });
    }
    @Override
    public List<CantidadTipificaciones> obtenerCantidadTipificaciones() {
        String sql = """
            SELECT 
                EXTRACT(YEAR FROM t.fecha_creacion) AS año,
                COUNT(*) AS cantidad_tipificaciones
            FROM 
                tipificacion t
            WHERE 
                EXTRACT(YEAR FROM t.fecha_creacion) = 2023
            GROUP BY 
                EXTRACT(YEAR FROM t.fecha_creacion)
            ORDER BY 
                año
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CantidadTipificaciones ct = new CantidadTipificaciones();
            ct.setAño(rs.getInt("año"));
            ct.setCantidadTipificaciones(rs.getInt("cantidad_tipificaciones"));
            return ct;
        });
    }
    @Override
    public List<FrecuenciaModificaciones> obtenerFrecuenciaModificaciones() {
        String sql = """
            SELECT 
                h.realizado_por AS id_analista, 
                COUNT(*) AS frecuencia_modificaciones
            FROM 
                historial_asignacion h
            WHERE 
                h.tipo_accion = 'modificacion'
                AND EXTRACT(YEAR FROM h.fecha_accion) = 2023
            GROUP BY 
                h.realizado_por
            ORDER BY 
                frecuencia_modificaciones DESC
        """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            FrecuenciaModificaciones fm = new FrecuenciaModificaciones();
            fm.setIdAnalista(rs.getString("id_analista"));
            fm.setFrecuenciaModificaciones(rs.getInt("frecuencia_modificaciones"));
            return fm;
        });
    }

    // Reporte analista
    @Override
    public List<ReporteBusiness> obtenerReporteBusiness(int anio) {
        String sql = """
        SELECT 
            t.categoria, 
            t.tipologia, 
            t.funcionalidad, 
            t.motivo,
            COUNT(tat.cod_ticket_asig) AS volumen_contacto
        FROM 
            tipificacion t
        JOIN 
            Ticket_asig_tip tat ON tat.cod_etiqueta = t.cod_etiqueta
        JOIN 
            conversacion c ON c.id_conv = tat.id_conv
        WHERE 
            EXTRACT(YEAR FROM c.fecha_inicio) = ?
        GROUP BY 
            t.categoria, t.tipologia, t.funcionalidad, t.motivo
        ORDER BY 
            volumen_contacto DESC;
    """;

        return jdbcTemplate.query(sql, new Object[]{anio}, (rs, rowNum) ->
                new ReporteBusiness(
                        rs.getString("categoria"),
                        rs.getString("tipologia"),
                        rs.getString("funcionalidad"),
                        rs.getString("motivo"),
                        rs.getInt("volumen_contacto")
                )
        );
    }

    @Override
    // Reporte de sugerencias y correcciones por analista
    public List<ReporteAnalistaSugerenciasCorrecciones> obtenerReporteSugerenciasCorrecciones(int anio) {
        String sql = """
            SELECT 
                h.realizado_por AS id_analista, 
                SUM(CASE WHEN h.tipo_accion = 'sugerencia' THEN 1 ELSE 0 END) AS cantidad_sugerencias,
                SUM(CASE WHEN h.tipo_accion = 'correccion' THEN 1 ELSE 0 END) AS cantidad_correcciones
            FROM 
                historial_asignacion h
            WHERE 
                h.tipo_accion IN ('sugerencia', 'correccion')
                AND EXTRACT(YEAR FROM h.fecha_accion) = ?
            GROUP BY 
                h.realizado_por
            ORDER BY 
                id_analista;
        """;

        return jdbcTemplate.query(sql, new Object[]{anio}, (rs, rowNum) ->
                new ReporteAnalistaSugerenciasCorrecciones(
                        rs.getString("id_analista"),
                        rs.getInt("cantidad_sugerencias"),
                        rs.getInt("cantidad_correcciones")
                )
        );
    }

    // Reporte de tasa de adopción de sugerencias por analista
    @Override
    public List<ReporteAnalistaTasaAdopcion> obtenerReporteTasaAdopcion(int anio) {
        String sql = "SELECT h.realizado_por AS id_analista, COUNT(*) AS total_sugerencias, SUM(CASE WHEN n.tipo_noti = 'sugerencia' THEN 1 ELSE 0 END) AS sugerencias_adoptadas, (SUM(CASE WHEN n.tipo_noti = 'sugerencia' THEN 1 ELSE 0 END) * 100.0 / COUNT(*)) AS tasa_adopcion FROM historial_asignacion h LEFT JOIN Notificacion n ON h.cod_ticket_asig = n.cod_ticket_asig WHERE h.tipo_accion = 'sugerencia' AND EXTRACT(YEAR FROM h.fecha_accion) = ? GROUP BY h.realizado_por ORDER BY tasa_adopcion DESC";
        return jdbcTemplate.query(sql, new Object[]{anio}, (rs, rowNum) -> new ReporteAnalistaTasaAdopcion(
                rs.getString("id_analista"),
                rs.getInt("total_sugerencias"),
                rs.getInt("sugerencias_adoptadas"),
                rs.getDouble("tasa_adopcion")
        ));
    }
}

