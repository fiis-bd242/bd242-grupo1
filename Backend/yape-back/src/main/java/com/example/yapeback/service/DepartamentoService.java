// src/main/java/com/example/yapeback/service/DepartamentoService.java
package com.example.yapeback.service;

import com.example.yapeback.interfaces.DepartamentoRepository;
import com.example.yapeback.model.Departamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public Departamento getOrganigrama(Long id) {
        Departamento rootDepartamento = departamentoRepository.findById(id);
        if (rootDepartamento != null) {
            buildOrganigrama(rootDepartamento);
        }
        return rootDepartamento;
    }

    private void buildOrganigrama(Departamento departamento) {
        List<Departamento> subDepartamentos = departamentoRepository.findSubDepartments(departamento.getId_departamento());
        departamento.setSubDepartamentos(subDepartamentos);
        departamento.setPuestos(departamentoRepository.findPuestosByDepartamentoId(departamento.getId_departamento()));
        for (Departamento subDepartamento : subDepartamentos) {
            buildOrganigrama(subDepartamento);
        }
    }
}