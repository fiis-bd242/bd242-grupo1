package com.example.Promo.Repository;

import com.example.Promo.Models.PromocionesActivasModel;
import com.example.Promo.Models.ProductoModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PromocionesActivasRepository {

    private final JdbcTemplate jdbcTemplate;

    public PromocionesActivasRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Obtener las promociones activas
    public List<PromocionesActivasModel> obtenerPromocionesActivas() {
        String sql = "SELECT p.fecha_inicio, p.cod_promocion, p.precio_final, p.dscto, p.dscrip_promo, " +
                "       s.nombre_seller, " +
                "       CASE " +
                "           WHEN CURRENT_DATE BETWEEN p.fecha_inicio AND p.fecha_fin THEN 'Vigente' " +
                "           ELSE 'No Vigente' " +
                "       END AS vigencia " + // Calculamos la vigencia
                "FROM promocion p " +
                "JOIN promocionxproducto pxp ON p.cod_promocion = pxp.cod_promocion " +
                "JOIN productoxseller pxs ON pxp.cod_producto = pxs.cod_producto " +
                "JOIN seller s ON pxs.cod_seller = s.cod_seller " +
                "WHERE p.estado_promo = TRUE";

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            PromocionesActivasModel promo = new PromocionesActivasModel();
            promo.setFechaInicio(rs.getString("fecha_inicio"));
            promo.setCodPromocion(rs.getInt("cod_promocion"));
            promo.setPrecioFinal(rs.getInt("precio_final"));
            promo.setDscto(rs.getDouble("dscto"));
            promo.setDescripcion(rs.getString("dscrip_promo"));
            promo.setSeller(rs.getString("nombre_seller"));
            promo.setVigencia(rs.getString("vigencia")); // Asignamos la vigencia calculada
            return promo;
        });
    }

    // Obtener productos por promoción
    public List<ProductoModel> obtenerProductosPorPromocion(Integer codPromocion) {
        String sql = "SELECT pr.cod_producto, pr.nombre_producto " +
                "FROM promocionxproducto pxp " +
                "JOIN producto pr ON pxp.cod_producto = pr.cod_producto " +
                "WHERE pxp.cod_promocion = ?";

        return jdbcTemplate.query(sql, new Object[]{codPromocion}, (rs, rowNum) -> {
            ProductoModel producto = new ProductoModel();
            producto.setCodProducto(rs.getInt("cod_producto"));
            producto.setNombreProducto(rs.getString("nombre_producto"));
            return producto;
        });
    }
}