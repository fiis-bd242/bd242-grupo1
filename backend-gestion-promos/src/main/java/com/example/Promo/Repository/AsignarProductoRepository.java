package com.example.Promo.Repository;

import com.example.Promo.Models.AsignarProductoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AsignarProductoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Obtener tabla de promociones
    public List<Object[]> obtenerPromociones() {
        String sql = "SELECT cod_promocion, dscrip_promo, estado_promo FROM promocion";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{
                rs.getInt("cod_promocion"),
                rs.getString("dscrip_promo"),
                rs.getBoolean("estado_promo")
        });
    }

    // Obtener tabla de productos
    public List<Object[]> obtenerProductos() {
        String sql = "SELECT cod_producto, nombre_producto FROM producto";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{
                rs.getInt("cod_producto"),
                rs.getString("nombre_producto")
        });
    }

    // Obtener tabla de productoxseller
    public List<Object[]> obtenerProductoxSeller() {
        String sql = "SELECT cod_productoxseller, cod_producto, cod_seller FROM productoxseller";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{
                rs.getInt("cod_productoxseller"),
                rs.getInt("cod_producto"),
                rs.getInt("cod_seller")
        });
    }

    // Asignar producto a promoción
    public void asignarProducto(AsignarProductoModel asignarProductoModel) {
        String sql = "INSERT INTO promocionxproducto (cod_promocion, cod_producto) VALUES (?, ?)";
        jdbcTemplate.update(sql, asignarProductoModel.getCodPromocion(), asignarProductoModel.getCodProducto());
    }
}