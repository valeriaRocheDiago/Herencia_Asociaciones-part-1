package com.tienda.agro.model;

public class Antibiotico extends Producto {
    private final double dosisKg;
    private final TipoAnimal tipoAnimal;

    public Antibiotico(String nombre, double precio, double dosisKg, TipoAnimal tipoAnimal) {
        super(nombre, precio);
        if (tipoAnimal == null) throw new IllegalArgumentException("Tipo de animal obligatorio");
        if (dosisKg < 400 || dosisKg > 600) {
            throw new IllegalArgumentException("Dosis debe estar entre 400 y 600 Kg");
        }
        this.dosisKg = dosisKg;
        this.tipoAnimal = tipoAnimal;
    }

    public double getDosisKg() { return dosisKg; }
    public TipoAnimal getTipoAnimal() { return tipoAnimal; }
}
