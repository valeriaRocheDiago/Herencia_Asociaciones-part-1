package com.tienda.agro.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AntibioticoTest {

    @Test
    void crearAntibioticoValido() {
        Antibiotico a = new Antibiotico("AntiX", 15000.0, 500, TipoAnimal.BOVINO);
        assertEquals(500, a.getDosisKg());
        assertEquals(TipoAnimal.BOVINO, a.getTipoAnimal());
        assertEquals("AntiX", a.getNombre());
    }

    @Test
    void crearAntibioticoDosisInvalidaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Antibiotico("AntiY", 12000.0, 700, TipoAnimal.PORCINO);
        });
    }
}
