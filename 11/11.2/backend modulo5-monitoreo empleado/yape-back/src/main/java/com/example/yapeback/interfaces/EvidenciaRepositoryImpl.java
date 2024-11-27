package com.example.yapeback.interfaces;

import com.example.yapeback.model.EvidenciaPermiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class EvidenciaRepositoryImpl implements EvidenciaRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void guardarEvidencia(EvidenciaPermiso evidenciaPermiso) {
        String sql = """
            INSERT INTO EvidenciaPermiso (nombre_archivo, ruta_archivo, fecha_subida, id_permiso)
            VALUES (?, ?, CURRENT_TIMESTAMP, ?)
        """;
        jdbcTemplate.update(sql,
                evidenciaPermiso.getNombreArchivo(),
                evidenciaPermiso.getRutaArchivo(),
                evidenciaPermiso.getIdPermiso());
    }
    
    @Override
    public List<Map<String, Object>> obtenerEvidenciasPorPermiso(Long idPermiso) {
        String sql = """
            SELECT 
                nombre_archivo, 
                CONCAT('http://localhost:8080/uploads/evidencias/', id_permiso, '/', nombre_archivo) AS ruta_archivo
            FROM EvidenciaPermiso
            WHERE id_permiso = ?
        """;
        return jdbcTemplate.queryForList(sql, idPermiso);
    }

   // @Override
  //  public List<EvidenciaPermiso> obtenerEvidenciasPorPermiso(Long idPermiso) {
  //      String sql = """
  //          SELECT id_evidencia, nombre_archivo, ruta_archivo, fecha_subida, id_permiso
   //         FROM EvidenciaPermiso
   //         WHERE id_permiso = ?
   //     """;
   //     return jdbcTemplate.query(sql, (rs, rowNum) -> mapRowToEvidencia(rs), idPermiso);
  //  }

    private EvidenciaPermiso mapRowToEvidencia(ResultSet rs) throws SQLException {
        EvidenciaPermiso evidencia = new EvidenciaPermiso();
        evidencia.setIdEvidencia(rs.getLong("id_evidencia"));
        evidencia.setNombreArchivo(rs.getString("nombre_archivo"));
        evidencia.setRutaArchivo(rs.getString("ruta_archivo"));
        evidencia.setFechaSubida(rs.getTimestamp("fecha_subida").toLocalDateTime());
        evidencia.setIdPermiso(rs.getLong("id_permiso"));
        return evidencia;
    }
}
