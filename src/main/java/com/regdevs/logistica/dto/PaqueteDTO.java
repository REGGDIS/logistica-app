package com.regdevs.logistica.dto;

import com.regdevs.logistica.model.EstadoEnum;

public class PaqueteDTO {
    private Long id;
    private String descripcion;
    private String destinatario;
    private String direccionEntrega;
    private Long rutaId;
    private String rutaNombre;
    private EstadoEnum ultimoEstado;
    private String emailDestinatario;
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }
    public String getEmailDestinatario() { return emailDestinatario; }
    public void setEmailDestinatario(String emailDestinatario) { this.emailDestinatario = emailDestinatario; }
    public Long getRutaId() { return rutaId; }
    public void setRutaId(Long rutaId) { this.rutaId = rutaId; }
    public String getRutaNombre() { return rutaNombre; }
    public void setRutaNombre(String rutaNombre) { this.rutaNombre = rutaNombre; }
    public EstadoEnum getUltimoEstado() { return ultimoEstado; }
    public void setUltimoEstado(EstadoEnum ultimoEstado) { this.ultimoEstado = ultimoEstado; }
}
