package com.example.Promo.Repository;

import com.example.Promo.Models.CrearPromocionModel;
import com.example.Promo.Models.ProductoPromocionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CrearPromocionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // Crear una nueva promoción
    public String crearPromocion(CrearPromocionModel promocion) {
        String sql = "INSERT INTO promocion (fecha_inicio, fecha_fin, dscto, precio_final, estado_promo, dscrip_promo, ID_empleado) " +
                "VALUES (CAST(? AS DATE), CAST(? AS DATE), ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, promocion.getFecha_inicio(), promocion.getFecha_fin(), promocion.getDscto(),
                promocion.getPrecio_final(), promocion.isEstado_promo(), promocion.getDscrip_promo(), promocion.getId_empleado());
        return "Promoción creada exitosamente.";
    }


    // Asociar productos a una promoción existente
    public String asociarProductos(int codPromocion, List<ProductoPromocionDTO> productos) {
        String sql = "INSERT INTO promocionxproducto (cod_promocion, cod_producto) VALUES (?, ?)";
        for (ProductoPromocionDTO producto : productos) {
            jdbcTemplate.update(sql, codPromocion, producto.getCod_producto());
        }
        return "Productos asociados exitosamente a la promoción.";
    }

    // Obtener todas las promociones
    public List<CrearPromocionModel> obtenerTodasPromociones() {
        String sql = "SELECT * FROM promocion";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CrearPromocionModel promocion = new CrearPromocionModel();
            promocion.setFecha_inicio(rs.getString("fecha_inicio"));
            promocion.setFecha_fin(rs.getString("fecha_fin"));
            promocion.setDscto(rs.getDouble("dscto"));
            promocion.setPrecio_final(rs.getDouble("precio_final"));
            promocion.setEstado_promo(rs.getBoolean("estado_promo"));
            promocion.setDscrip_promo(rs.getString("dscrip_promo"));
            promocion.setId_empleado(rs.getInt("id_empleado"));
            return promocion;
        });
    }
}