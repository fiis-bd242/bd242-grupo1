package com.example.yapeback.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Recursos {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_recurso_prot; 
    private String nombre_recurso; 
    private String url_recurso; 
    private Integer id_tipo_recurso; 
    private Integer cod_prototipo; 
    
    public Recursos(Integer id_recurso_prot, String nombre_recurso, String url_recurso, Integer id_tipo_recurso, Integer cod_prototipo) {
        this.id_recurso_prot = id_recurso_prot;
        this.nombre_recurso = nombre_recurso;
        this.url_recurso = url_recurso;
        this.id_tipo_recurso = id_tipo_recurso;
        this.cod_prototipo = cod_prototipo;
    }
    public Recursos() {
    }
}
