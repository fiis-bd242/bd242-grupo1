package com.example.Promo.Repository;

import com.example.Promo.Models.EditarPromocionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EditarPromocionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Optional<EditarPromocionModel> obtenerPromocionPorId(Long id) {
        String sql = "SELECT * FROM promocion WHERE cod_promocion = ?";
        try {
            List<EditarPromocionModel> resultados = jdbcTemplate.query(sql, this::mapRowToPromocion, id);
            return resultados.isEmpty() ? Optional.empty() : Optional.of(resultados.get(0));
        } catch (Exception e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            throw e;
        }
    }

    public boolean actualizarPromocion(EditarPromocionModel promocion) {
        String sql = "UPDATE promocion SET fecha_inicio = ?, fecha_fin = ?, dscto = ?, precio_final = ?, dscrip_promo = ? WHERE cod_promocion = ?";
        try {
            int rowsUpdated = jdbcTemplate.update(sql,
                    promocion.getFecha_inicio(),      // Fecha de inicio como String (YYYY-MM-DD)
                    promocion.getFecha_fin(),        // Fecha de fin como String (YYYY-MM-DD)
                    promocion.getDscto(),           // Descuento como Double
                    promocion.getPrecio_final(),     // Precio final como Integer
                    promocion.getDscrip_Promo(),     // Descripción como String
                    promocion.getCodPromocion());   // Código de promoción como Long
            return rowsUpdated > 0;
        } catch (Exception e) {
            System.err.println("Error al actualizar la promoción: " + e.getMessage());
            return false;
        }
    }



    private EditarPromocionModel mapRowToPromocion(ResultSet rs, int rowNum) throws SQLException {
        EditarPromocionModel promocion = new EditarPromocionModel();
        promocion.setCodPromocion(rs.getLong("cod_promocion"));
        promocion.setFecha_inicio(rs.getString("fecha_inicio"));
        promocion.setFecha_fin(rs.getString("fecha_fin"));
        promocion.setDscto(rs.getDouble("dscto"));
        promocion.setPrecio_final(rs.getInt("precio_final"));
        promocion.setDscrip_Promo(rs.getString("dscrip_promo")); // <-- Cambiado
        return promocion;
    }

}