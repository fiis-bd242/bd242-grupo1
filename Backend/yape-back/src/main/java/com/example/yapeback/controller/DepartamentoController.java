// src/main/java/com/example/yapeback/controller/DepartamentoController.java
package com.example.yapeback.controller;

import com.example.yapeback.model.Departamento;
import com.example.yapeback.service.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departamentos")
public class DepartamentoController {

    @Autowired
    private DepartamentoService departamentoService;

    @GetMapping("/{id}/organigrama")
    public ResponseEntity<Departamento> getOrganigrama(@PathVariable Long id) {
        Departamento organigrama = departamentoService.getOrganigrama(id);
        if (organigrama != null) {
            return new ResponseEntity<>(organigrama, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}