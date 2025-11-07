package com.tienda.agro.model;

import java.time.LocalDate;

public class Fertilizante extends ProductoControl {
    private final LocalDate fechaUltimaAplicacion;

    public Fertilizante(String nombre, double precio, String registroICA, int frecuenciaDias, LocalDate fechaUltimaAplicacion) {
        super(nombre, precio, registroICA, frecuenciaDias);
        if (fechaUltimaAplicacion == null) throw new IllegalArgumentException("Fecha última aplicación obligatoria");
        this.fechaUltimaAplicacion = fechaUltimaAplicacion;
    }

    public java.time.LocalDate getFechaUltimaAplicacion() { return fechaUltimaAplicacion; }
}
