package com.example.yapeback.interfaces;

import com.example.yapeback.model.OfertaLaboral;
import com.example.yapeback.model.Beneficio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OfertaLaboralRepositoryImpl implements OfertaLaboralRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<OfertaLaboral> findAll() {
        String sql = "SELECT * FROM oferta_laboral";
        return jdbcTemplate.query(sql, new OfertaLaboralRowMapper());
    }

    @Override
    public OfertaLaboral findById(Long id) {
        String sql = "SELECT * FROM oferta_laboral WHERE id_oferta = ?";
        return jdbcTemplate.queryForObject(sql, new OfertaLaboralRowMapper(), id);
    }

    @Override
    public OfertaLaboral save(OfertaLaboral ofertaLaboral) {
        if (ofertaLaboral.getId_oferta() == null) {
            String sql = "INSERT INTO oferta_laboral (fecha_oferta, fecha_inicio_propuesta, link_documento_legal_sin_firma, link_documento_legal_con_firma, id_postulante, id_vacante) VALUES (?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(sql, ofertaLaboral.getFecha_oferta(), ofertaLaboral.getFecha_inicio_propuesta(), ofertaLaboral.getLink_documento_legal_sin_firma(), ofertaLaboral.getLink_documento_legal_con_firma(), ofertaLaboral.getId_postulante(), ofertaLaboral.getId_vacante());
            Long newId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Long.class);
            ofertaLaboral.setId_oferta(newId);
        } else {
            String sql = "UPDATE oferta_laboral SET fecha_oferta = ?, fecha_inicio_propuesta = ?, link_documento_legal_sin_firma = ?, link_documento_legal_con_firma = ?, id_postulante = ?, id_vacante = ? WHERE id_oferta = ?";
            jdbcTemplate.update(sql, ofertaLaboral.getFecha_oferta(), ofertaLaboral.getFecha_inicio_propuesta(), ofertaLaboral.getLink_documento_legal_sin_firma(), ofertaLaboral.getLink_documento_legal_con_firma(), ofertaLaboral.getId_postulante(), ofertaLaboral.getId_vacante(), ofertaLaboral.getId_oferta());
        }
        return ofertaLaboral;
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM oferta_laboral WHERE id_oferta = ?";
        jdbcTemplate.update(sql, id);
    }

    public void updateBeneficios(OfertaLaboral ofertaLaboral) {
        String deleteSql = "DELETE FROM Oferta_Laboral_Beneficio WHERE id_oferta = ?";
        jdbcTemplate.update(deleteSql, ofertaLaboral.getId_oferta());

        String insertSql = "INSERT INTO Oferta_Laboral_Beneficio (id_oferta, id_beneficio) VALUES (?, ?)";
        for (Beneficio beneficio : ofertaLaboral.getBeneficios()) {
            jdbcTemplate.update(insertSql, ofertaLaboral.getId_oferta(), beneficio.getId_beneficio());
        }
    }

    private static class OfertaLaboralRowMapper implements RowMapper<OfertaLaboral> {
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
            return ofertaLaboral;
        }
    }
}