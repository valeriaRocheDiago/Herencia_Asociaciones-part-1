package com.tienda.agro.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Producto> productos = new ArrayList<>();
    private static Factura facturaActual = null;
    private static Cliente clienteActual = null;

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE FACTURACIÓN AGRÍCOLA ===");
        boolean run = true;
        while (run) {
            mostrarMenu();
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1" -> crearProducto();
                case "2" -> crearCliente();
                case "3" -> crearFactura();
                case "4" -> agregarLineaDesdeProductos();
                case "5" -> mostrarTotalFactura();
                case "6" -> mostrarProductos();
                case "7" -> mostrarFacturasCliente();
                case "8" -> venderSoloProductosControl();
                case "9" -> buscar_por_cedula();
                case "10" -> {
                    System.out.println("Saliendo.");
                    run = false;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("\n1. Registrar producto");
        System.out.println("2. Registrar cliente");
        System.out.println("3. Crear factura");
        System.out.println("4. Agregar línea (elegir producto registrado)");
        System.out.println("5. Mostrar total factura actual");
        System.out.println("6. Listar productos registrados");
        System.out.println("7. Mostrar facturas de cliente");
        System.out.println("8. Vender solo Productos de Control");
        System.out.println("9. Buscar por cédula (mostrar facturas y productos vendidos)");

        System.out.println("10. Salir");
        System.out.print("\nOpción: ");
    }

    private static void crearCliente() {
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Cédula: ");
            String ced = sc.nextLine();
            Cliente c = new Cliente(nombre, ced);
            clientes.add(c);
            clienteActual = c;
            System.out.println("✅ Cliente creado y seleccionado: " + c.getNombre() + " - " + c.getCedula());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void crearProducto() {
        System.out.println("Ingrese tipo de producto:");
        System.out.println("1. Fertilizante");
        System.out.println("2. Control de Plagas");
        System.out.println("3. Antibiótico");
        System.out.print("> ");
        String t = sc.nextLine().trim();
        if (!t.equals("1") && !t.equals("2") && !t.equals("3")) {
            System.out.println("Tipo inválido.");
            return;
        }
        try {
            System.out.print("Nombre: ");
            String nombre = sc.nextLine();
            System.out.print("Precio (ej. 100.0): ");
            double precio = Double.parseDouble(sc.nextLine());
            System.out.print("Registro ICA: ");
            String ica = sc.nextLine();
            System.out.print("Frecuencia (días): ");
            int frecuencia = Integer.parseInt(sc.nextLine());

            if (t.equals("1")) {
                System.out.print("Fecha última aplicación (YYYY-MM-DD): ");
                String fecha = sc.nextLine();
                LocalDate f = LocalDate.parse(fecha);
                Fertilizante fert = new Fertilizante(nombre, precio, ica, frecuencia, f);
                productos.add(fert);
                System.out.println("✅ Fertilizante registrado con éxito.");
            } else if (t.equals("2")) {
                System.out.print("Periodo de carencia (días): ");
                int pc = Integer.parseInt(sc.nextLine());
                ControlPlagas cp = new ControlPlagas(nombre, precio, ica, frecuencia, pc);
                productos.add(cp);
                System.out.println("✅ Control de plagas registrado con éxito.");
            } else if (t.equals("3")) {
                System.out.print("Dosis (400-600): ");
                double dosis = Double.parseDouble(sc.nextLine());
                System.out.print("Tipo animal (BOVINO/CAPRINO/PORCINO): ");
                String tipo = sc.nextLine().toUpperCase();
                TipoAnimal ta = TipoAnimal.valueOf(tipo);
                Antibiotico a = new Antibiotico(nombre, precio, dosis, ta);
                productos.add(a);
                System.out.println("✅ Antibiótico registrado con éxito.");
            }
        } catch (Exception e) {
            System.out.println("Error registrando producto: " + e.getMessage());
        }
    }

    private static void crearFactura() {
        if (clienteActual == null) {
            System.out.println("No hay cliente seleccionado. Crea uno primero (opción 2).");
            return;
        }
        try {
            facturaActual = new Factura(LocalDate.now());
            clienteActual.agregarFactura(facturaActual);
            System.out.println("✅ Factura creada y asociada al cliente: " + clienteActual.getNombre());
        } catch (IllegalArgumentException e) {
            System.out.println("Error creando factura: " + e.getMessage());
        }
    }


    private static void agregarLineaDesdeProductos() {
        if (facturaActual == null) {
            System.out.println("Crea primero una factura (opción 3).");
            return;
        }
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados. Registra uno (opción 1).");
            return;
        }
        mostrarProductos();
        System.out.print("Elige índice de producto a agregar: ");
        try {
            int idx = Integer.parseInt(sc.nextLine());
            if (idx < 0 || idx >= productos.size()) {
                System.out.println("Índice inválido.");
                return;
            }
            Producto p = productos.get(idx);
            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(sc.nextLine());
            facturaActual.agregarLinea(new LineaFactura(p, cantidad));
            System.out.println("✅ Línea agregada: " + cantidad + " x " + p.getNombre());
        } catch (Exception e) {
            System.out.println("Error al agregar línea: " + e.getMessage());
        }
    }

    private static void mostrarTotalFactura() {
        if (facturaActual == null) {
            System.out.println("No hay factura activa.");
            return;
        }
        System.out.println("Total factura actual: " + facturaActual.calcularTotal());
    }

    private static void mostrarProductos() {
        System.out.println("Productos registrados:");
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.printf("[%d] %s - %.2f - %s%n", i, p.getNombre(), p.getPrecio(), p.getClass().getSimpleName());
        }
    }

    private static void mostrarFacturasCliente() {
        if (clienteActual == null) {
            System.out.println("No hay cliente seleccionado.");
            return;
        }
        System.out.println("Facturas de " + clienteActual.getNombre() + ":");
        List<Factura> h = clienteActual.getHistorial();
        if (h.isEmpty()) {
            System.out.println("Sin facturas.");
            return;
        }
        for (int i = 0; i < h.size(); i++) {
            Factura f = h.get(i);
            System.out.printf("[%d] Fecha: %s - Total: %.2f%n", i, f.getFecha(), f.calcularTotal());
        }
    }

    private static void venderSoloProductosControl() {
    if (facturaActual == null) {
        System.out.println("Crea primero una factura (opción 3).");
        return;
    }
    // Filtrar solo productos de tipo ProductoControl
    List<Producto> controls = new ArrayList<>();
    for (Producto p : productos) {
        if (p instanceof com.tienda.agro.model.ProductoControl) {
            controls.add(p);
        }
    }
    if (controls.isEmpty()) {
        System.out.println("No hay Productos de Control registrados (Fertilizante/ControlPlagas).");
        return;
    }

    System.out.println("Productos de Control disponibles:");
    for (int i = 0; i < controls.size(); i++) {
        Producto p = controls.get(i);
        System.out.printf("[%d] %s - %.2f - %s%n", i, p.getNombre(), p.getPrecio(), p.getClass().getSimpleName());
    }

    System.out.print("Elige índice de Producto de Control a vender: ");
    try {
        int sel = Integer.parseInt(sc.nextLine());
        if (sel < 0 || sel >= controls.size()) {
            System.out.println("Índice inválido.");
            return;
        }
        Producto elegido = controls.get(sel);
        System.out.print("Cantidad: ");
        int cantidad = Integer.parseInt(sc.nextLine());
        facturaActual.agregarLinea(new LineaFactura(elegido, cantidad));
        System.out.println("✅ Vendido: " + cantidad + " x " + elegido.getNombre());
    } catch (Exception e) {
        System.out.println("Error al vender: " + e.getMessage());
    }
}

private static void buscar_por_cedula() {
    System.out.print("Ingrese cédula a buscar: ");
    String ced = sc.nextLine().trim();
    if (ced.isEmpty()) {
        System.out.println("Cédula vacía.");
        return;
    }

    // Buscar cliente
    Cliente encontrado = null;
    for (Cliente c : clientes) {
        if (c.getCedula().equals(ced)) {
            encontrado = c;
            break;
        }
    }

    if (encontrado == null) {
        System.out.println("No se encontró ningún cliente con cédula: " + ced);
        return;
    }

    System.out.println("Cliente: " + encontrado.getNombre() + " - " + encontrado.getCedula());
    List<Factura> historial = encontrado.getHistorial();
    if (historial.isEmpty()) {
        System.out.println("No tiene facturas.");
        return;
    }

    for (int i = 0; i < historial.size(); i++) {
        Factura f = historial.get(i);
        System.out.printf("Factura %d — Fecha: %s — Total: %.2f%n", i+1, f.getFecha(), f.calcularTotal());
        List<LineaFactura> items = f.getItems();
        if (items.isEmpty()) {
            System.out.println("  (Sin líneas)");
        } else {
            for (int j = 0; j < items.size(); j++) {
                LineaFactura lf = items.get(j);
                Producto p = lf.getProducto();
                String tipo = p.getClass().getSimpleName();
                System.out.printf("  - %s | Tipo: %s | Cantidad: %d | Subtotal: %.2f%n",
                                  p.getNombre(), tipo, lf.getCantidad(), lf.getSubtotal());
            }
        }
    }
}


}
