package com.tienda.agro.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

public class ControlPlagasFertilizanteTest {

    @Test
    void controlPlagasPeriodoOk() {
        ControlPlagas cp = new ControlPlagas("InsectX", 50.0, "ICA123", 30, 10);
        assertEquals(10, cp.getPeriodoCarenciaDias());
    }

    @Test
    void controlPlagasPeriodoNegativoLanza() {
        assertThrows(IllegalArgumentException.class, () -> new ControlPlagas("InsectX", 50.0, "ICA123", 30, -1));
    }

    @Test
    void fertilizanteFechaObligatoria() {
        assertThrows(IllegalArgumentException.class, () -> new Fertilizante("FertiX", 80.0, "ICA999", 60, null));
    }

    @Test
    void fertilizanteValido() {
        Fertilizante f = new Fertilizante("FertiX", 80.0, "ICA999", 60, LocalDate.of(2025,1,1));
        assertEquals(LocalDate.of(2025,1,1), f.getFechaUltimaAplicacion());
    }
}
