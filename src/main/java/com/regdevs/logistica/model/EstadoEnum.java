package com.regdevs.logistica.model;

public enum EstadoEnum {
    EN_TRANSITO("En tránsito"),
    EN_BODEGA("En bodega"),
    EN_REPARTO("En reparto"),
    ENTREGADO("Entregado"),
    RECHAZADO("Rechazado");

    private final String descripcion;

    EstadoEnum(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
