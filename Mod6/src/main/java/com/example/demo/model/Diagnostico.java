package com.example.demo.model;

import java.util.Date;

public class Diagnostico {

    private Long id;
    private String comentario;
    private Date fechaRealizacion;
    private Long codIncidente;
    private Long idEmpleado;
    private Long codProveedor;
    private Long codCausa;

    // Constructor sin ID
    public Diagnostico(String comentario, Date fechaRealizacion, Long codIncidente, Long idEmpleado, Long codProveedor, Long codCausa) {
        this.comentario = comentario;
        this.fechaRealizacion = fechaRealizacion;
        this.codIncidente = codIncidente;
        this.idEmpleado = idEmpleado;
        this.codProveedor = codProveedor;
        this.codCausa = codCausa;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    public Long getCodIncidente() {
        return codIncidente;
    }

    public void setCodIncidente(Long codIncidente) {
        this.codIncidente = codIncidente;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Long getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(Long codProveedor) {
        this.codProveedor = codProveedor;
    }

    public Long getCodCausa() {
        return codCausa;
    }

    public void setCodCausa(Long codCausa) {
        this.codCausa = codCausa;
    }
}
