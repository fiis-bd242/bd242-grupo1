// src/main/java/com/example/yapeback/service/DepartamentoService.java
package com.example.yapeback.service;

import com.example.yapeback.interfaces.DepartamentoRepository;
import com.example.yapeback.model.Departamento;
import com.example.yapeback.model.Puesto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    @Autowired
    public DepartamentoService(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public Departamento getOrganigrama(Long id) {
        Departamento root = departamentoRepository.findById(id);
        if (root != null) {
            buildHierarchy(root);
        }
        return root;
    }

    private void buildHierarchy(Departamento departamento) {
        List<Departamento> subDepartments = departamentoRepository.findSubDepartments(departamento.getId_departamento());
        for (Departamento subDepartment : subDepartments) {
            buildHierarchy(subDepartment);
        }
        departamento.setSubDepartments(subDepartments);
        departamento.setPuestos(departamentoRepository.findPuestosByDepartamentoId(departamento.getId_departamento()));
    }
}