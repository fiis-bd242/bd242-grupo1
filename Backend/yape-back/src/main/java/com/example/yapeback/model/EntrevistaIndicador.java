package com.example.yapeback.model;

public class EntrevistaIndicador {
    private Long id_indicador;
    private String nombre;
    private Integer puntaje;

    // Getters y Setters

    public Long getId_indicador() {
        return id_indicador;
    }

    public void setId_indicador(Long id_indicador) {
        this.id_indicador = id_indicador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }
}