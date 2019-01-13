package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalitaTest {


    Localita tepk = new Localita();
         @Test
         void testsetandget() {
        assertNotNull(tepk);

        tepk.setNome("Dario");
        assertEquals("Dario", tepk.getNome());

        tepk.setCitta("Salerno");
        assertEquals("Salerno", tepk.getCitta());

        tepk.setNazione("CAMPIONE");
        assertEquals("CAMPIONE", tepk.getNazione());

        tepk.setId(12);
        assertEquals(12, tepk.getId());

        tepk.setCodiceErasmus("darioscola015@gmail.com");
        assertEquals("darioscola015@gmail.com", tepk.getCodiceErasmus());

        tepk.setCoordinatore(12);
        assertEquals(12, tepk.getCoordinatore());

    }
}