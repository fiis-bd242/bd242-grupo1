package com.example.demo.interfaces;

import com.example.demo.model.Solucion;
import com.example.demo.interfaces.SolucionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SolucionRepositoryImpl implements SolucionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int crearSolucion(Solucion solucion) {
        String sql = "INSERT INTO Solucion (estado_solucion, test, cod_diag) " +
                     "VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, 
            solucion.getEstadoSolucion(),
            solucion.getTest(), 
            solucion.getCodDiagnostico());
    }
}
