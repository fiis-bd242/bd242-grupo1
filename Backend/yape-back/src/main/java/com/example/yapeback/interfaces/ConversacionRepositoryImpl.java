package com.example.yapeback.interfaces;

import com.example.yapeback.model.Conversacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConversacionRepositoryImpl implements ConversacionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Conversacion> findAllHistorial() {
        String query = """
            SELECT c.cod_ticket, 
                   c.id_cliente AS cod_cliente, 
                   CONCAT(ce.nombre_cli, ' ', ce.apellido_cli) AS nombre_completo, 
                   c.id_conv, 
                   c.estado_conv AS estado, 
                   c.fecha_inicio, 
                   c.fecha_fin 
            FROM conversacion c
            INNER JOIN cliente_externo ce ON c.id_cliente = ce.id_cliente
            """;

        return jdbcTemplate.query(query, new ConversacionRowMapper());
    }

    @Override
    // Método para obtener el historial de conversaciones filtrado por idEmpleado (asesor)
    public List<Conversacion> findByIdEmpleado(Long idEmpleado) {
        String query = """
            SELECT c.cod_ticket, 
                   c.id_cliente AS cod_cliente, 
                   CONCAT(ce.nombre_cli, ' ', ce.apellido_cli) AS nombre_completo, 
                   c.id_conv, 
                   c.estado_conv AS estado, 
                   c.fecha_inicio, 
                   c.fecha_fin 
            FROM conversacion c
            INNER JOIN cliente_externo ce ON c.id_cliente = ce.id_cliente
            WHERE c.id_empleado = ? 
            """;

        // Ejecutar la consulta pasando el idEmpleado como parámetro
        return jdbcTemplate.query(query, new ConversacionRowMapper(), idEmpleado);
    }

    private static class ConversacionRowMapper implements RowMapper<Conversacion> {
        @Override
        public Conversacion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Conversacion conversacion = new Conversacion();

            // Asignar cada columna a los atributos correspondientes
            conversacion.setCodTicket(rs.getLong("cod_ticket"));
            conversacion.setIdCliente(rs.getLong("cod_cliente"));
            conversacion.setNombreCompleto(rs.getString("nombre_completo")); // Aquí asignamos nombre completo
            conversacion.setIdConv(rs.getLong("id_conv"));

            // Mapeo del estado, convirtiendo el String a Enum
            String estado = rs.getString("estado");
            conversacion.setEstadoConv(Conversacion.EstadoConv.valueOf(estado));

            return conversacion;
        }
    }
}
