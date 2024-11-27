package com.example.Promo.Repository;

import com.example.Promo.Models.GestionPromosModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GestionPromosRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<GestionPromosModel> mostrarPromociones() {
        String sql = """
                SELECT 
                    p.fecha_inicio AS fechaInicio, 
                    p.fecha_fin AS fechaFin, 
                    p.cod_promocion AS codPromocion, 
                    p.dscto, 
                    p.precio_final AS precioFinal, 
                    CAST(EXTRACT(DAY FROM AGE(p.fecha_fin, p.fecha_inicio)) AS INT) AS vigencia, 
                    p.estado_promo AS estadoPromo, 
                    s.nombre_seller AS seller
                FROM promocion p
                LEFT JOIN promocionxproducto pp ON p.cod_promocion = pp.cod_promocion
                LEFT JOIN Producto prod ON pp.cod_producto = prod.cod_producto
                LEFT JOIN productoxseller ps ON prod.cod_producto = ps.cod_producto
                LEFT JOIN Seller s ON ps.cod_seller = s.cod_seller
                """;
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(GestionPromosModel.class));
    }

    public List<GestionPromosModel> filtrarPromociones(Integer vigencia, String seller) {
        StringBuilder sql = new StringBuilder("""
                SELECT 
                    p.fecha_inicio AS fechaInicio, 
                    p.fecha_fin AS fechaFin, 
                    p.cod_promocion AS codPromocion, 
                    p.dscto, 
                    p.precio_final AS precioFinal, 
                    CAST(EXTRACT(DAY FROM AGE(p.fecha_fin, p.fecha_inicio)) AS INT) AS vigencia, 
                    p.estado_promo AS estadoPromo, 
                    s.nombre_seller AS seller
                FROM promocion p
                LEFT JOIN promocionxproducto pp ON p.cod_promocion = pp.cod_promocion
                LEFT JOIN Producto prod ON pp.cod_producto = prod.cod_producto
                LEFT JOIN productoxseller ps ON prod.cod_producto = ps.cod_producto
                LEFT JOIN Seller s ON ps.cod_seller = s.cod_seller
                WHERE 1 = 1
                """);

        if (vigencia != null) {
            sql.append(" AND CAST(EXTRACT(DAY FROM AGE(p.fecha_fin, p.fecha_inicio)) AS INT) = ").append(vigencia);
        }
        if (seller != null && !seller.isEmpty()) {
            sql.append(" AND LOWER(s.nombre_seller) LIKE LOWER('%").append(seller).append("%')");
        }

        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(GestionPromosModel.class));
    }

    // Cambio sugerido en GestionPromosRepository.java
    public void actualizarEstado(Integer codPromocion, boolean estado) {
        String sql = """
        UPDATE promocion 
        SET estado_promo = ? 
        WHERE cod_promocion = ?
        """;
        jdbcTemplate.update(sql, estado, codPromocion);
    }


    public void eliminarPromocion(Integer codPromocion) {
        String sql1 = """
                DELETE FROM promocionxproducto
                WHERE cod_promocion = CAST(? AS INTEGER)
                """;
        jdbcTemplate.update(sql1, codPromocion);
        String sql = """
                DELETE FROM promocion 
                WHERE cod_promocion = CAST(? AS INTEGER)
                """;
        jdbcTemplate.update(sql, codPromocion);
    }
}





