package com.tienda.agro.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private final String nombre;
    private final String cedula;
    private final List<Factura> historial = new ArrayList<>();

    public Cliente(String nombre, String cedula) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre obligatorio");
        if (cedula == null || cedula.isBlank()) throw new IllegalArgumentException("Cédula obligatoria");
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public void agregarFactura(Factura f) {
        if (f == null) throw new IllegalArgumentException("Factura nula");
        historial.add(f);
    }

    public List<Factura> getHistorial() { return List.copyOf(historial); }
    public String getNombre() { return nombre; }
    public String getCedula() { return cedula; }
}
