package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EsameTest {
    public String semestre = "primo";//driver

 Esame EsameTests = new Esame("Ingegneria del Software","1234",9,semestre);
    @Test
    void testSetandGet()  {



        Esame tepk = new Esame();

        assertNotNull(tepk);

        tepk.setNome("Dario");
        assertEquals("Dario", tepk.getNome());

        tepk.setId(12);
        assertEquals(12,tepk.getId());

        tepk.setCodice("CODE");
        assertEquals("CODE", tepk.getCodice());

        tepk.setCreditiFormativi(9);
        assertEquals(9,tepk.getCreditiFormativi());

        tepk.setSemestre(semestre);
        assertEquals(semestre,tepk.getSemestre());


    }
}