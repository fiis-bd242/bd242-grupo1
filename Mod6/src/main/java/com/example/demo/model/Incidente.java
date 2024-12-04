package com.example.demo.model;


public class Incidente {
    
    private Long cod_incidente;
    private String mensaje_incidente;
    private String categoria;
    private Long cod_ticket_inc;

    // Getters y setters
    public Long getCodIncidente() {
        return cod_incidente;
    }

    public void setCodIncidente(Long codIncidente) {
        this.cod_incidente = codIncidente;
    }

    public String getMensajeIncidente() {
        return mensaje_incidente;
    }

    public void setMensajeIncidente(String mensajeIncidente) {
        this.mensaje_incidente = mensajeIncidente;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Long getCodTicketInc() {
        return cod_ticket_inc;
    }

    public void setCodTicketInc(Long codTicketInc) {
        this.cod_ticket_inc = codTicketInc;
    }
}
