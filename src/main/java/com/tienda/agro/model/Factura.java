package com.tienda.agro.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Factura {
    private final LocalDate fecha;
    private final List<LineaFactura> items = new ArrayList<>();

    public Factura(LocalDate fecha) {
        if (fecha == null) throw new IllegalArgumentException("Fecha obligatoria");
        this.fecha = fecha;
    }

    public void agregarLinea(LineaFactura linea) {
        if (linea == null) throw new IllegalArgumentException("Línea nula");
        items.add(linea);
    }

    public double calcularTotal() {
        return items.stream().mapToDouble(LineaFactura::getSubtotal).sum();
    }

    public LocalDate getFecha() { return fecha; }
    public List<LineaFactura> getItems() { return List.copyOf(items); }
}
