package com.example.backend_capacitaciones_yape.repository;

import com.example.backend_capacitaciones_yape.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
	
}