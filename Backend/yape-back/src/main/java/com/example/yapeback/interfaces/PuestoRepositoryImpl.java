// src/main/java/com/example/yapeback/interfaces/PuestoRepositoryImpl.java
package com.example.yapeback.interfaces;

import com.example.yapeback.model.Puesto;
import com.example.yapeback.model.Funcion;
import com.example.yapeback.model.Vacante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PuestoRepositoryImpl implements PuestoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final class PuestoRowMapper implements RowMapper<Puesto> {
        @Override
        public Puesto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Puesto puesto = new Puesto();
            puesto.setId_puesto(rs.getLong("id_puesto"));
            puesto.setNombre(rs.getString("nombre"));
            puesto.setPaga(rs.getDouble("paga"));
            puesto.setId_departamento(rs.getLong("id_departamento"));
            return puesto;
        }
    }

    private static final class FuncionRowMapper implements RowMapper<Funcion> {
        @Override
        public Funcion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Funcion funcion = new Funcion();
            funcion.setId_funcion(rs.getLong("id_funcion"));
            funcion.setNombre(rs.getString("nombre"));
            funcion.setDescripcion(rs.getString("descripcion"));
            return funcion;
        }
    }

    private static final class VacanteRowMapper implements RowMapper<Vacante> {
        @Override
        public Vacante mapRow(ResultSet rs, int rowNum) throws SQLException {
            Vacante vacante = new Vacante();
            vacante.setId_vacante(rs.getLong("id_vacante"));
            vacante.setEstado(rs.getString("estado"));
            vacante.setFecha_fin(rs.getDate("fecha_fin"));
            vacante.setFecha_inicio(rs.getDate("fecha_inicio"));
            vacante.setComentario(rs.getString("comentario"));
            vacante.setId_puesto(rs.getLong("id_puesto"));
            return vacante;
        }
    }

    @Override
    public List<Puesto> findAll() {
        String sql = "SELECT id_puesto, nombre, paga, id_departamento FROM puesto";
        List<Puesto> puestos = jdbcTemplate.query(sql, new PuestoRowMapper());
        for (Puesto puesto : puestos) {
            puesto.setFunciones(findFuncionesByPuestoId(puesto.getId_puesto()));
            puesto.setVacantes(findVacantesByPuestoId(puesto.getId_puesto())); // Añadir esta línea
        }
        return puestos;
    }

    @Override
    public Puesto findById(Long id) {
        String sql = "SELECT id_puesto, nombre, paga, id_departamento FROM puesto WHERE id_puesto = ?";
        Puesto puesto = jdbcTemplate.queryForObject(sql, new PuestoRowMapper(), id);
        if (puesto != null) {
            puesto.setFunciones(findFuncionesByPuestoId(id));
            puesto.setVacantes(findVacantesByPuestoId(id)); // Añadir esta línea
        }
        return puesto;
    }

    @Override
    public Puesto save(Puesto puesto) {
        if (puesto.getId_puesto() == null) {
            String sql = "INSERT INTO puesto (nombre, paga, id_departamento) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, puesto.getNombre(), puesto.getPaga(), puesto.getId_departamento());
        } else {
            String sql = "UPDATE puesto SET nombre = ?, paga = ?, id_departamento = ? WHERE id_puesto = ?";
            jdbcTemplate.update(sql, puesto.getNombre(), puesto.getPaga(), puesto.getId_departamento(), puesto.getId_puesto());
        }
        saveFunciones(puesto);
        return puesto;
    }

    @Override
    public void deleteById(Long id) {
        String deletePuestoFuncionSql = "DELETE FROM puesto_funcion WHERE id_puesto = ?";
        jdbcTemplate.update(deletePuestoFuncionSql, id);

        String deletePuestoSql = "DELETE FROM puesto WHERE id_puesto = ?";
        jdbcTemplate.update(deletePuestoSql, id);
    }

    @Override
    public void savePuestoFuncion(Long idPuesto, Long idFuncion) {
        String sql = "INSERT INTO puesto_funcion (id_puesto, id_funcion) VALUES (?, ?)";
        jdbcTemplate.update(sql, idPuesto, idFuncion);
    }

    @Override
    public void deletePuestoFuncionByPuestoId(Long idPuesto) {
        String sql = "DELETE FROM puesto_funcion WHERE id_puesto = ?";
        jdbcTemplate.update(sql, idPuesto);
    }

    @Override
    public List<Vacante> findVacantesByPuestoId(Long idPuesto) {
        String sql = "SELECT id_vacante, estado, fecha_fin, fecha_inicio, comentario, id_puesto FROM vacante WHERE id_puesto = ?";
        return jdbcTemplate.query(sql, new VacanteRowMapper(), idPuesto);
    }

    private List<Funcion> findFuncionesByPuestoId(Long idPuesto) {
        String sql = "SELECT f.id_funcion, f.nombre, f.descripcion " +
                "FROM funcion f " +
                "JOIN puesto_funcion pf ON f.id_funcion = pf.id_funcion " +
                "WHERE pf.id_puesto = ?";
        return jdbcTemplate.query(sql, new FuncionRowMapper(), idPuesto);
    }

    private void saveFunciones(Puesto puesto) {
        deletePuestoFuncionByPuestoId(puesto.getId_puesto());

        for (Funcion funcion : puesto.getFunciones()) {
            if (funcion.getId_funcion() == null && funcion.getNombre() != null) {
                String sql = "SELECT id_funcion, nombre, descripcion FROM funcion WHERE nombre = ?";
                List<Funcion> existingFunciones = jdbcTemplate.query(sql, new FuncionRowMapper(), funcion.getNombre());

                if (!existingFunciones.isEmpty()) {
                    Funcion existingFuncion = existingFunciones.get(0);
                    funcion.setId_funcion(existingFuncion.getId_funcion());
                    funcion.setDescripcion(existingFuncion.getDescripcion());
                } else {
                    sql = "INSERT INTO funcion (nombre, descripcion) VALUES (?, ?)";
                    jdbcTemplate.update(sql, funcion.getNombre(), funcion.getDescripcion());
                    sql = "SELECT id_funcion FROM funcion WHERE nombre = ? AND descripcion = ?";
                    funcion.setId_funcion(jdbcTemplate.queryForObject(sql, Long.class, funcion.getNombre(), funcion.getDescripcion()));
                }
            }

            if (funcion.getId_funcion() != null) {
                if (funcion.getNombre() == null || funcion.getDescripcion() == null) {
                    String sql = "SELECT nombre, descripcion FROM funcion WHERE id_funcion = ?";
                    Funcion existingFuncion = jdbcTemplate.queryForObject(sql, new Object[]{funcion.getId_funcion()}, (rs, rowNum) -> {
                        Funcion f = new Funcion();
                        f.setNombre(rs.getString("nombre"));
                        f.setDescripcion(rs.getString("descripcion"));
                        return f;
                    });
                    if (funcion.getNombre() == null) {
                        funcion.setNombre(existingFuncion.getNombre());
                    }
                    if (funcion.getDescripcion() == null) {
                        funcion.setDescripcion(existingFuncion.getDescripcion());
                    }
                }
                savePuestoFuncion(puesto.getId_puesto(), funcion.getId_funcion());
            }
        }
    }
}