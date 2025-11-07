package com.tienda.agro.model;

public abstract class ProductoControl extends Producto {
    private final String registroICA;
    private final int frecuenciaDias;

    public ProductoControl(String nombre, double precio, String registroICA, int frecuenciaDias) {
        super(nombre, precio);
        if (registroICA == null || registroICA.isBlank()) throw new IllegalArgumentException("ICA obligatorio");
        if (frecuenciaDias <= 0) throw new IllegalArgumentException("Frecuencia debe ser positiva");
        this.registroICA = registroICA;
        this.frecuenciaDias = frecuenciaDias;
    }

    public String getRegistroICA() { return registroICA; }
    public int getFrecuenciaDias() { return frecuenciaDias; }
}
