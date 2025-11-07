package com.tienda.agro.model;

public class ControlPlagas extends ProductoControl {
    private final int periodoCarenciaDias;

    public ControlPlagas(String nombre, double precio, String registroICA, int frecuenciaDias, int periodoCarenciaDias) {
        super(nombre, precio, registroICA, frecuenciaDias);
        if (periodoCarenciaDias < 0) throw new IllegalArgumentException("Periodo de carencia no puede ser negativo");
        this.periodoCarenciaDias = periodoCarenciaDias;
    }

    public int getPeriodoCarenciaDias() { return periodoCarenciaDias; }
}
