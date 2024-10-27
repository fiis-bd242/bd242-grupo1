// src/main/java/com/example/yapeback/interfaces/DepartamentoRepositoryImpl.java
package com.example.yapeback.interfaces;

import com.example.yapeback.model.Departamento;
import com.example.yapeback.model.Puesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DepartamentoRepositoryImpl implements DepartamentoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class DepartamentoRowMapper implements RowMapper<Departamento> {
        @Override
        public Departamento mapRow(ResultSet rs, int rowNum) throws SQLException {
            Departamento departamento = new Departamento();
            departamento.setId_departamento(rs.getLong("id_departamento"));
            departamento.setDescripcion(rs.getString("descripcion"));
            departamento.setId_departamento_padre(rs.getLong("id_departamento_padre"));
            return departamento;
        }
    }

    @Override
    public List<Departamento> findAll() {
        String sql = "SELECT id_departamento, descripcion, id_departamento_padre FROM departamento";
        return jdbcTemplate.query(sql, new DepartamentoRowMapper());
    }

    @Override
    public Departamento findById(Long id) {
        String sql = "SELECT id_departamento, descripcion, id_departamento_padre FROM departamento WHERE id_departamento = ?";
        Departamento departamento = jdbcTemplate.queryForObject(sql, new DepartamentoRowMapper(), id);
        if (departamento != null) {
            departamento.setPuestos(findPuestosByDepartamentoId(id));
        }
        return departamento;
    }

    @Override
    public List<Departamento> findSubDepartments(Long id) {
        String sql = "SELECT id_departamento, descripcion, id_departamento_padre FROM departamento WHERE id_departamento_padre = ?";
        return jdbcTemplate.query(sql, new DepartamentoRowMapper(), id);
    }

    @Override
    public List<Puesto> findPuestosByDepartamentoId(Long idDepartamento) {
        String sql = "SELECT id_puesto, nombre, paga, id_departamento FROM puesto WHERE id_departamento = ?";
        return jdbcTemplate.query(sql, new PuestoRepositoryImpl.PuestoRowMapper(), idDepartamento);
    }
}