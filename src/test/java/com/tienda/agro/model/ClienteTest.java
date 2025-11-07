package com.tienda.agro.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ClienteTest {

    @Test
    void clienteAgregaFacturaAlHistorial() {
        Cliente c = new Cliente("Juan", "12345678");
        Factura f = new Factura(LocalDate.now());
        c.agregarFactura(f);
        assertEquals(1, c.getHistorial().size());
        assertSame(f, c.getHistorial().get(0));
    }

    @Test
    void crearClienteSinCedulaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new Cliente("Ana", ""));
    }
}
