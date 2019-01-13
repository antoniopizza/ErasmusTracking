package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MappingEsameTest {

    @Test
    void testSetandGet()  {
        MappingEsame tepk = new MappingEsame();
    Esame esameinternotest = new Esame();
Esame esameesternotest = new Esame();

        assertNotNull(tepk);

        tepk.setLingua("Italiano");
        assertEquals("Italiano", tepk.getLingua());

        tepk.setStato("Scola");
        assertEquals("Scola", tepk.getStato());

        tepk.setLearningAgreement(1234);
        assertEquals(1234,tepk.getLearningAgreement());

        tepk.setId(12);
        assertEquals(12,tepk.getId());

        tepk.setEsameInterno(esameinternotest);
        assertEquals(esameinternotest,tepk.getEsameInterno());

        tepk.setEsameEsterno(esameesternotest);
        assertEquals(esameesternotest,tepk.getEsameEsterno());



    }

}