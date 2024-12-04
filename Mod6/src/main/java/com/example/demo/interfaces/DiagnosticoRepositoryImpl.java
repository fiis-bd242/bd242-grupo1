package com.example.demo.interfaces;

import com.example.demo.model.Diagnostico;
import com.example.demo.interfaces.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DiagnosticoRepositoryImpl implements DiagnosticoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int crearDiagnostico(Diagnostico diagnostico) {
        String sql = "INSERT INTO diagnostico (comentario, fecha_realizacion, cod_incidente, id_empleado, cod_proveedor, cod_causa) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, 
            diagnostico.getComentario(),
            diagnostico.getFechaRealizacion(), 
            diagnostico.getCodIncidente(), 
            diagnostico.getIdEmpleado(), 
            diagnostico.getCodProveedor(), 
            diagnostico.getCodCausa());
    }
} 

