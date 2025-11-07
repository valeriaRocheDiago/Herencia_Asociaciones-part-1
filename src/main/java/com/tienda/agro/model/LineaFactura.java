package com.tienda.agro.model;

public class LineaFactura {
    private final Producto producto;
    private final int cantidad;

    public LineaFactura(Producto producto, int cantidad) {
        if (producto == null) throw new IllegalArgumentException("Producto obligatorio");
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser positiva");
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return producto.getPrecio() * cantidad; }
}
