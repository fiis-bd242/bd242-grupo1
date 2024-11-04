package com.example.yapeback.interfaces;

import com.example.yapeback.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDate;

@Repository
public class PostulanteRepositoryImpl implements PostulanteRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostulanteRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Postulante> findAll() {
        String sql = "SELECT * FROM postulante";
        return jdbcTemplate.query(sql, new PostulanteRowMapper(jdbcTemplate));
    }

    @Override
    public Postulante findById(Long id) {
        String sql = "SELECT * FROM postulante WHERE id_postulante = ?";
        return jdbcTemplate.queryForObject(sql, new PostulanteRowMapper(jdbcTemplate), id);
    }

    @Override
    public Postulante save(Postulante postulante) {
        if (postulante.getId_postulante() == null) {
            String sql = "INSERT INTO postulante (nombre, telefono, id_vacante, correo, puntaje) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, 
                postulante.getNombre(), 
                postulante.getTelefono(), 
                postulante.getId_vacante(), 
                postulante.getCorreo(), 
                postulante.getPuntaje());
            Long newId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
            postulante.setId_postulante(newId);
        } else {
            String sql = "UPDATE postulante SET nombre = ?, telefono = ?, id_vacante = ?, correo = ?, puntaje = ? WHERE id_postulante = ?";
            jdbcTemplate.update(sql, 
                postulante.getNombre(), 
                postulante.getTelefono(), 
                postulante.getId_vacante(), 
                postulante.getCorreo(), 
                postulante.getPuntaje(), 
                postulante.getId_postulante());
        }
        return postulante;
    }

    @Override
    public void deleteById(Long id) {
        try {
            // 1. Eliminar las entrevistas relacionadas
            List<Entrevista> entrevistas = findEntrevistasByPostulanteId(id);
            for (Entrevista entrevista : entrevistas) {
                String sqlDelete = "DELETE FROM entrevista WHERE id_entrevista = ?";
                jdbcTemplate.update(sqlDelete, entrevista.getId_entrevista());
            }

            // 2. Eliminar idiomas relacionados
            deleteIdiomasByPostulanteId(id);

            // 3. Eliminar educaciones
            deleteEducacionesByPostulanteId(id);

            // 4. Eliminar habilidades
            deleteHabilidadesByPostulanteId(id);

            // 5. Eliminar experiencias laborales y sus habilidades
            deleteExperienciasByPostulanteId(id);

            // 6. Eliminar oferta laboral y sus beneficios
            OfertaLaboral ofertaLaboral = findOfertaLaboralByPostulanteId(id);
            if (ofertaLaboral != null) {
                String deleteBeneficiosSQL = "DELETE FROM oferta_laboral_beneficio WHERE id_oferta = ?";
                jdbcTemplate.update(deleteBeneficiosSQL, ofertaLaboral.getId_oferta());

                String deleteOfertaSQL = "DELETE FROM oferta_laboral WHERE id_postulante = ?";
                jdbcTemplate.update(deleteOfertaSQL, id);
            }

            // 7. Finalmente eliminar el postulante
            String sql = "DELETE FROM postulante WHERE id_postulante = ?";
            jdbcTemplate.update(sql, id);
            
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar el postulante y sus relaciones: " + e.getMessage(), e);
        }
    }

    private OfertaLaboral findOfertaLaboralByPostulanteId(Long idPostulante) {
        try {
            String sql = "SELECT * FROM oferta_laboral WHERE id_postulante = ?";
            return jdbcTemplate.queryForObject(sql, new OfertaLaboralRowMapper(), idPostulante);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Idioma> findIdiomasByPostulanteId(Long idPostulante) {
        String sql = "SELECT i.* FROM idioma i JOIN idioma_postulante ip ON i.id_idioma = ip.id_idioma WHERE ip.id_postulante = ?";
        return jdbcTemplate.query(sql, new IdiomaRowMapper(), idPostulante);
    }

    @Override
    public void saveIdioma(Long idPostulante, Idioma idioma) {
        if (idioma.getId_idioma() == null) {
            String sqlIdioma = "INSERT INTO idioma (nombre) VALUES (?) RETURNING id_idioma";
            Long idIdioma = jdbcTemplate.queryForObject(sqlIdioma, Long.class, idioma.getNombre());
            idioma.setId_idioma(idIdioma);
        }
        String sql = "INSERT INTO idioma_postulante (id_postulante, id_idioma) VALUES (?, ?)";
        jdbcTemplate.update(sql, idPostulante, idioma.getId_idioma());
    }

    @Override
    public void deleteIdiomasByPostulanteId(Long idPostulante) {
        String sql = "DELETE FROM idioma_postulante WHERE id_postulante = ?";
        jdbcTemplate.update(sql, idPostulante);
    }

    @Override
    public void saveEducacion(Long idPostulante, Educacion educacion) {
        String sql = "INSERT INTO educacion (id_postulante, titulo, institucion, fecha_inicio, fecha_fin, en_curso) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, idPostulante, educacion.getTitulo(), educacion.getInstitucion(), educacion.getFecha_inicio(), educacion.getFecha_fin(), educacion.isEn_curso());
    }

    @Override
    public void deleteEducacionesByPostulanteId(Long idPostulante) {
        String sql = "DELETE FROM educacion WHERE id_postulante = ?";
        jdbcTemplate.update(sql, idPostulante);
    }

    @Override
    public void saveHabilidad(Long idPostulante, Habilidad habilidad) {
        String sql = "INSERT INTO habilidad_postulante (id_postulante, nombre, nivel) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, idPostulante, habilidad.getNombre(), habilidad.getNivel());
    }

    @Override
    public void deleteHabilidadesByPostulanteId(Long idPostulante) {
        String sql = "DELETE FROM habilidad_postulante WHERE id_postulante = ?";
        jdbcTemplate.update(sql, idPostulante);
    }

    @Override
    public void saveExperiencia(Long idPostulante, ExperienciaLaboral experiencia) {
        String sql = "INSERT INTO experiencia_laboral (id_postulante, empresa, puesto, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?, ?) RETURNING id_experiencia";
        Long idExperiencia = jdbcTemplate.queryForObject(sql, Long.class, 
            idPostulante, experiencia.getEmpresa(), experiencia.getPuesto(), 
            experiencia.getFecha_inicio(), experiencia.getFecha_fin());
        
        // Guardar las habilidades de la experiencia
        if (experiencia.getHabilidades() != null) {
            for (HabilidadExperiencia habilidad : experiencia.getHabilidades()) {
                String sqlHabilidad = "INSERT INTO habilidad_experiencia (nombre, id_experiencia) VALUES (?, ?)";
                jdbcTemplate.update(sqlHabilidad, habilidad.getNombre(), idExperiencia);
            }
        }
    }

    @Override
    public void deleteExperienciasByPostulanteId(Long idPostulante) {
        // Primero obtener todas las experiencias del postulante
        String selectSql = "SELECT id_experiencia FROM experiencia_laboral WHERE id_postulante = ?";
        List<Long> experienciaIds = jdbcTemplate.queryForList(selectSql, Long.class, idPostulante);
        
        // Eliminar las habilidades asociadas a cada experiencia
        String deleteHabilidadesSql = "DELETE FROM habilidad_experiencia WHERE id_experiencia = ?";
        for (Long expId : experienciaIds) {
            jdbcTemplate.update(deleteHabilidadesSql, expId);
        }
        
        // Finalmente eliminar las experiencias
        String deleteExperienciasSql = "DELETE FROM experiencia_laboral WHERE id_postulante = ?";
        jdbcTemplate.update(deleteExperienciasSql, idPostulante);
    }

    @Override
    public List<Educacion> findEducacionesByPostulanteId(Long idPostulante) {
        String sql = "SELECT * FROM educacion WHERE id_postulante = ?";
        return jdbcTemplate.query(sql, new EducacionRowMapper(), idPostulante);
    }

    private class PostulanteRowMapper implements RowMapper<Postulante> {
        private final JdbcTemplate jdbcTemplate;

        public PostulanteRowMapper(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public Postulante mapRow(ResultSet rs, int rowNum) throws SQLException {
            Postulante postulante = new Postulante();
            postulante.setId_postulante(rs.getLong("id_postulante"));
            postulante.setNombre(rs.getString("nombre"));
            postulante.setTelefono(rs.getBigDecimal("telefono").toBigInteger());
            postulante.setId_vacante(rs.getLong("id_vacante"));
            postulante.setCorreo(rs.getString("correo"));
            postulante.setPuntaje(rs.getInt("puntaje"));
            postulante.setIdiomas(findIdiomasByPostulanteId(rs.getLong("id_postulante")));
            postulante.setEducaciones(findEducacionesByPostulanteId(rs.getLong("id_postulante")));
            postulante.setHabilidades(findHabilidadesByPostulanteId(rs.getLong("id_postulante")));
            postulante.setExperienciasLaborales(findExperienciasByPostulanteId(rs.getLong("id_postulante")));
            postulante.setOfertaLaboral(findOfertaLaboralByPostulanteId(rs.getLong("id_postulante")));
            return postulante;
        }

        private List<Idioma> findIdiomasByPostulanteId(Long idPostulante) {
            String sql = "SELECT i.* FROM idioma i JOIN idioma_postulante ip ON i.id_idioma = ip.id_idioma WHERE ip.id_postulante = ?";
            return jdbcTemplate.query(sql, new IdiomaRowMapper(), idPostulante);
        }

        private List<Educacion> findEducacionesByPostulanteId(Long idPostulante) {
            String sql = "SELECT * FROM educacion WHERE id_postulante = ?";
            return jdbcTemplate.query(sql, new EducacionRowMapper(), idPostulante);
        }

        private List<Habilidad> findHabilidadesByPostulanteId(Long idPostulante) {
            String sql = "SELECT * FROM habilidad_postulante WHERE id_postulante = ?";
            return jdbcTemplate.query(sql, new HabilidadRowMapper(), idPostulante);
        }

        private List<ExperienciaLaboral> findExperienciasByPostulanteId(Long idPostulante) {
            String sql = "SELECT * FROM experiencia_laboral WHERE id_postulante = ?";
            return jdbcTemplate.query(sql, new ExperienciaLaboralRowMapper(), idPostulante);
        }

        private OfertaLaboral findOfertaLaboralByPostulanteId(Long idPostulante) {
            String sql = "SELECT * FROM oferta_laboral WHERE id_postulante = ?";
            try {
                return jdbcTemplate.queryForObject(sql, new OfertaLaboralRowMapper(), idPostulante);
            } catch (EmptyResultDataAccessException e) {
                return null;
            }
        }
    }

    @Override
    public List<Habilidad> findHabilidadesByPostulanteId(Long idPostulante) {
        String sql = "SELECT * FROM habilidad_postulante WHERE id_postulante = ?";
        return jdbcTemplate.query(sql, new HabilidadRowMapper(), idPostulante);
    }

    private class IdiomaRowMapper implements RowMapper<Idioma> {
        @Override
        public Idioma mapRow(ResultSet rs, int rowNum) throws SQLException {
            Idioma idioma = new Idioma();
            idioma.setId_idioma(rs.getLong("id_idioma"));
            idioma.setNombre(rs.getString("nombre"));
            return idioma;
        }
    }

    private class EducacionRowMapper implements RowMapper<Educacion> {
        @Override
        public Educacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Educacion educacion = new Educacion();
            educacion.setId_educacion(rs.getLong("id_educacion"));
            educacion.setTitulo(rs.getString("titulo"));
            educacion.setInstitucion(rs.getString("institucion"));
            educacion.setFecha_inicio(rs.getDate("fecha_inicio"));
            educacion.setFecha_fin(rs.getDate("fecha_fin"));
            educacion.setEn_curso(rs.getBoolean("en_curso"));
            educacion.setId_postulante(rs.getLong("id_postulante"));
            return educacion;
        }
    }

    private class HabilidadRowMapper implements RowMapper<Habilidad> {
        @Override
        public Habilidad mapRow(ResultSet rs, int rowNum) throws SQLException {
            Habilidad habilidad = new Habilidad();
            habilidad.setId_habilidad_postulante(rs.getLong("id_habilidad_postulante"));
            habilidad.setNombre(rs.getString("nombre"));
            habilidad.setNivel(rs.getString("nivel"));
            habilidad.setId_postulante(rs.getLong("id_postulante"));
            return habilidad;
        }
    }

    private class ExperienciaLaboralRowMapper implements RowMapper<ExperienciaLaboral> {
        @Override
        public ExperienciaLaboral mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExperienciaLaboral experiencia = new ExperienciaLaboral();
            experiencia.setId_experiencia(rs.getLong("id_experiencia"));
            experiencia.setEmpresa(rs.getString("empresa"));
            experiencia.setPuesto(rs.getString("puesto"));
            experiencia.setFecha_inicio(rs.getDate("fecha_inicio"));
            experiencia.setFecha_fin(rs.getDate("fecha_fin"));
            experiencia.setId_postulante(rs.getLong("id_postulante"));
            experiencia.setHabilidades(findHabilidadesByExperienciaId(rs.getLong("id_experiencia")));
            return experiencia;
        }

        private List<HabilidadExperiencia> findHabilidadesByExperienciaId(Long idExperiencia) {
            String sql = "SELECT * FROM habilidad_experiencia WHERE id_experiencia = ?";
            return jdbcTemplate.query(sql, new HabilidadExperienciaRowMapper(), idExperiencia);
        }
    }

    private class HabilidadExperienciaRowMapper implements RowMapper<HabilidadExperiencia> {
        @Override
        public HabilidadExperiencia mapRow(ResultSet rs, int rowNum) throws SQLException {
            HabilidadExperiencia habilidad = new HabilidadExperiencia();
            habilidad.setId_habilidad_experiencia(rs.getLong("id_habilidad_experiencia"));
            habilidad.setNombre(rs.getString("nombre"));
            habilidad.setId_experiencia(rs.getLong("id_experiencia"));
            return habilidad;
        }
    }

    private class OfertaLaboralRowMapper implements RowMapper<OfertaLaboral> {
        @Override
        public OfertaLaboral mapRow(ResultSet rs, int rowNum) throws SQLException {
            OfertaLaboral ofertaLaboral = new OfertaLaboral();
            ofertaLaboral.setId_oferta(rs.getLong("id_oferta"));
            ofertaLaboral.setFecha_oferta(rs.getDate("fecha_oferta"));
            ofertaLaboral.setFecha_inicio_propuesta(rs.getDate("fecha_inicio_propuesta"));
            ofertaLaboral.setLink_documento_legal_sin_firma(rs.getString("link_documento_legal_sin_firma"));
            ofertaLaboral.setLink_documento_legal_con_firma(rs.getString("link_documento_legal_con_firma"));
            ofertaLaboral.setId_postulante(rs.getLong("id_postulante"));
            ofertaLaboral.setId_vacante(rs.getLong("id_vacante"));
            ofertaLaboral.setBeneficios(findBeneficiosByOfertaLaboralId(rs.getLong("id_oferta")));
            return ofertaLaboral;
        }

        private List<Beneficio> findBeneficiosByOfertaLaboralId(Long idOferta) {
            String sql = "SELECT b.* FROM beneficio b JOIN oferta_laboral_beneficio olb ON b.id_beneficio = olb.id_beneficio WHERE olb.id_oferta = ?";
            return jdbcTemplate.query(sql, new BeneficioRowMapper(), idOferta);
        }
    }

    private class BeneficioRowMapper implements RowMapper<Beneficio> {
        @Override
        public Beneficio mapRow(ResultSet rs, int rowNum) throws SQLException {
            Beneficio beneficio = new Beneficio();
            beneficio.setId_beneficio(rs.getLong("id_beneficio"));
            beneficio.setDescripcion(rs.getString("descripcion"));
            return beneficio;
        }
    }

    @Override
    public Long saveIdiomaAndGetId(String nombre) {
        String sqlIdioma = "INSERT INTO idioma (nombre) VALUES (?) RETURNING id_idioma";
        return jdbcTemplate.queryForObject(sqlIdioma, Long.class, nombre);
    }

    @Override
    public Long saveHabilidadExperienciaAndGetId(String nombre, Long idExperiencia) {
        String sql = "INSERT INTO habilidad_experiencia (nombre, id_experiencia) VALUES (?, ?) RETURNING id_habilidad_experiencia";
        return jdbcTemplate.queryForObject(sql, Long.class, nombre, idExperiencia);
    }

    @Override
    public Long saveExperienciaAndGetId(Long idPostulante, ExperienciaLaboral experiencia) {
        String sql = "INSERT INTO experiencia_laboral (id_postulante, empresa, puesto, fecha_inicio, fecha_fin) VALUES (?, ?, ?, ?, ?) RETURNING id_experiencia";
        return jdbcTemplate.queryForObject(sql, Long.class, 
            idPostulante, experiencia.getEmpresa(), experiencia.getPuesto(), 
            experiencia.getFecha_inicio(), experiencia.getFecha_fin());
    }

    @Override
    public List<Entrevista> findEntrevistasByPostulanteId(Long idPostulante) {
        String sql = "SELECT * FROM entrevista WHERE id_postulante = ?";
        return jdbcTemplate.query(sql, new EntrevistaRowMapper(), idPostulante);
    }

    private static final class EntrevistaRowMapper implements RowMapper<Entrevista> {
        @Override
        public Entrevista mapRow(ResultSet rs, int rowNum) throws SQLException {
            Entrevista entrevista = new Entrevista();
            entrevista.setId_entrevista(rs.getLong("id_entrevista"));
            entrevista.setEstado(rs.getString("estado"));
            entrevista.setFecha(rs.getDate("fecha").toLocalDate());
            entrevista.setPuntaje_general(rs.getInt("puntaje_general"));
            entrevista.setId_postulante(rs.getLong("id_postulante"));
            entrevista.setId_empleado(rs.getLong("id_empleado"));
            entrevista.setTipo_entrevista(rs.getString("tipo_entrevista"));
            return entrevista;
        }
    }

    private class ObservacionRowMapper implements RowMapper<Observacion> {
        @Override
        public Observacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Observacion observacion = new Observacion();
            observacion.setId_observacion(rs.getLong("id_observacion"));
            observacion.setId_feedback(rs.getLong("id_feedback"));
            observacion.setNombre(rs.getString("id_nombre"));
            observacion.setDescripcion(rs.getString("descripcion"));// Asignar el nombre de la categoría
            return observacion;
        }
    }

    public List<Observacion> findObservacionesByFeedbackId(Long idFeedback) {
        String sql = "SELECT * FROM observacion WHERE id_feedback = ?";
        return jdbcTemplate.query(sql, new ObservacionRowMapper(), idFeedback);
    }

    // Implementación del RowMapper para Feedback que incluye Observaciones
    private class FeedbackRowMapper implements RowMapper<Feedback> {
        @Override
        public Feedback mapRow(ResultSet rs, int rowNum) throws SQLException {
            Feedback feedback = new Feedback();
            feedback.setId_feedback(rs.getLong("id_feedback"));
            LocalDate fecha = rs.getDate("fecha") != null ? rs.getDate("fecha").toLocalDate() : null;
            feedback.setFecha(fecha);
            // Mapeo de Observaciones
            feedback.setObservaciones(findObservacionesByFeedbackId(feedback.getId_feedback()));
            return feedback;
        }
    }
}