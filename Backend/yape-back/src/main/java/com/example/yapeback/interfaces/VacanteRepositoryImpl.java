// src/main/java/com/example/yapeback/interfaces/VacanteRepositoryImpl.java
package com.example.yapeback.interfaces;

import com.example.yapeback.model.Convocatoria;
import com.example.yapeback.model.Postulante;
import com.example.yapeback.model.Vacante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

@Repository
public class VacanteRepositoryImpl implements VacanteRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private class VacanteRowMapper implements RowMapper<Vacante> {
        @Override
        public Vacante mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vacante vacante = new Vacante();
            vacante.setId_vacante(rs.getLong("id_vacante"));
            vacante.setEstado(rs.getString("estado"));
            vacante.setFecha_fin(rs.getDate("fecha_fin"));
            vacante.setFecha_inicio(rs.getDate("fecha_inicio"));
            vacante.setComentario(rs.getString("comentario"));
            vacante.setId_puesto(rs.getLong("id_puesto"));
            vacante.setConvocatorias(findConvocatoriasByVacanteId(rs.getLong("id_vacante")));
            vacante.setPostulantes(findPostulantesByVacanteId(rs.getLong("id_vacante"))); // Añadir esta línea
            return vacante;
        }

        private List<Convocatoria> findConvocatoriasByVacanteId(Long id_vacante) {
            String sql = "SELECT * FROM convocatoria WHERE id_vacante = ?";
            return jdbcTemplate.query(sql, new ConvocatoriaRowMapper(), id_vacante);
        }

        private List<Postulante> findPostulantesByVacanteId(Long id_vacante) { // Añadir este método
            String sql = "SELECT * FROM postulante WHERE id_vacante = ?";
            return jdbcTemplate.query(sql, new PostulanteRowMapper(), id_vacante);
        }
    }

    private static final class ConvocatoriaRowMapper implements RowMapper<Convocatoria> {
        @Override
        public Convocatoria mapRow(ResultSet rs, int rowNum) throws SQLException {
            Convocatoria convocatoria = new Convocatoria();
            convocatoria.setId_convocatoria(rs.getLong("id_convocatoria"));
            convocatoria.setId_vacante(rs.getLong("id_vacante"));
            convocatoria.setMedio_publicacion(rs.getString("medio_publicacion"));
            convocatoria.setFecha_inicio(rs.getDate("fecha_inicio"));
            convocatoria.setFecha_fin(rs.getDate("fecha_fin"));
            convocatoria.setEstado(rs.getString("estado"));
            return convocatoria;
        }
    }

    private static final class PostulanteRowMapper implements RowMapper<Postulante> { // Añadir esta clase
        @Override
        public Postulante mapRow(ResultSet rs, int rowNum) throws SQLException {
            Postulante postulante = new Postulante();
            postulante.setId_postulante(rs.getLong("id_postulante"));
            postulante.setNombre(rs.getString("nombre"));
            postulante.setTelefono(rs.getInt("telefono"));
            postulante.setId_vacante(rs.getLong("id_vacante"));
            return postulante;
        }
    }

    @Override
    public List<Vacante> findAll() {
        String sql = "SELECT id_vacante, estado, fecha_fin, fecha_inicio, comentario, id_puesto FROM vacante";
        return jdbcTemplate.query(sql, new VacanteRowMapper());
    }

    @Override
    public Vacante findById(Long id) {
        String sql = "SELECT id_vacante, estado, fecha_fin, fecha_inicio, comentario, id_puesto FROM vacante WHERE id_vacante = ?";
        return jdbcTemplate.queryForObject(sql, new VacanteRowMapper(), id);
    }

    @Override
    public Vacante save(Vacante vacante) {
        if (vacante.getId_vacante() == null) {
            String sql = "INSERT INTO vacante (estado, fecha_fin, fecha_inicio, comentario, id_puesto) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, vacante.getEstado(), vacante.getFecha_fin(), vacante.getFecha_inicio(), vacante.getComentario(), vacante.getId_puesto());
            Long newId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
            vacante.setId_vacante(newId);
        } else {
            String sql = "UPDATE vacante SET estado = ?, fecha_fin = ?, fecha_inicio = ?, comentario = ?, id_puesto = ? WHERE id_vacante = ?";
            jdbcTemplate.update(sql, vacante.getEstado(), vacante.getFecha_fin(), vacante.getFecha_inicio(), vacante.getComentario(), vacante.getId_puesto(), vacante.getId_vacante());
        }
        saveConvocatorias(vacante);
        savePostulantes(vacante); // Añadir esta línea
        return vacante;
    }

    @Override
    public void deleteById(Long id) {
        String deleteConvocatoriasSql = "DELETE FROM convocatoria WHERE id_vacante = ?";
        jdbcTemplate.update(deleteConvocatoriasSql, id);

        String deletePostulantesSql = "DELETE FROM postulante WHERE id_vacante = ?"; // Añadir esta línea
        jdbcTemplate.update(deletePostulantesSql, id);

        String deleteVacanteSql = "DELETE FROM vacante WHERE id_vacante = ?";
        jdbcTemplate.update(deleteVacanteSql, id);
    }

    private void saveConvocatorias(Vacante vacante) {
        for (Convocatoria convocatoria : vacante.getConvocatorias()) {
            if (convocatoria.getId_convocatoria() == null) {
                String insertConvocatoriaSql = "INSERT INTO convocatoria (id_vacante, medio_publicacion, fecha_inicio, fecha_fin, estado) VALUES (?, ?, ?, ?, ?)";
                jdbcTemplate.update(insertConvocatoriaSql, vacante.getId_vacante(), convocatoria.getMedio_publicacion(), convocatoria.getFecha_inicio(), convocatoria.getFecha_fin(), convocatoria.getEstado());
                Long newId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
                convocatoria.setId_convocatoria(newId);
            } else {
                String updateConvocatoriaSql = "UPDATE convocatoria SET medio_publicacion = ?, fecha_inicio = ?, fecha_fin = ?, estado = ? WHERE id_convocatoria = ?";
                jdbcTemplate.update(updateConvocatoriaSql, convocatoria.getMedio_publicacion(), convocatoria.getFecha_inicio(), convocatoria.getFecha_fin(), convocatoria.getEstado(), convocatoria.getId_convocatoria());
            }
        }
    }

    private void savePostulantes(Vacante vacante) { // Añadir este método
        for (Postulante postulante : vacante.getPostulantes()) {
            if (postulante.getId_postulante() == null) {
                String insertPostulanteSql = "INSERT INTO postulante (nombre, telefono, id_vacante) VALUES (?, ?, ?)";
                jdbcTemplate.update(insertPostulanteSql, postulante.getNombre(), postulante.getTelefono(), vacante.getId_vacante());
                Long newId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
                postulante.setId_postulante(newId);
            } else {
                String updatePostulanteSql = "UPDATE postulante SET nombre = ?, telefono = ?, id_vacante = ? WHERE id_postulante = ?";
                jdbcTemplate.update(updatePostulanteSql, postulante.getNombre(), postulante.getTelefono(), vacante.getId_vacante(), postulante.getId_postulante());
            }
        }
    }

    public void deleteConvocatoriaById(Long id) {
        String sql = "DELETE FROM convocatoria WHERE id_convocatoria = ?";
        jdbcTemplate.update(sql, id);
    }

    public void deleteConvocatoriaByVacanteId(Long idVacante, Long idConvocatoria) {
        String sql = "DELETE FROM convocatoria WHERE id_vacante = ? AND id_convocatoria = ?";
        jdbcTemplate.update(sql, idVacante, idConvocatoria);
    }

    // src/main/java/com/example/yapeback/interfaces/VacanteRepositoryImpl.java
    @Override
    public List<Postulante> findPostulantesByVacanteId(Long idVacante) {
        String sql = "SELECT * FROM postulante WHERE id_vacante = ?";
        return jdbcTemplate.query(sql, new PostulanteRowMapper(), idVacante);
    }
}