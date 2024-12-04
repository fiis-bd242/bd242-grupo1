package com.example.demo.model;

public class Solucion {

    private Long id;
    private String estadoSolucion;
    private String test;
    private Long codDiagnostico;

    // Constructor
    public Solucion(String estadoSolucion, String test, Long codDiagnostico) {
        this.estadoSolucion = estadoSolucion;
        this.test = test;
        this.codDiagnostico = codDiagnostico;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoSolucion() {
        return estadoSolucion;
    }

    public void setEstadoSolucion(String estadoSolucion) {
        this.estadoSolucion = estadoSolucion;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public Long getCodDiagnostico() {
        return codDiagnostico;
    }

    public void setCodDiagnostico(Long codDiagnostico) {
        this.codDiagnostico = codDiagnostico;
    }
}
