package com.example.yapeback.interfaces;

import com.example.yapeback.model.Empleado;
import java.util.List;

public interface EmpleadoRepository {
    List<Empleado> findAll();
    Empleado findById(Long id);
    Empleado save(Empleado empleado);
    void deleteById(Long id);
}
