package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class SendingInstituteTest {

    @Test
    void testSetandGet()  {
       SendingInstitute tepk = new SendingInstitute();
         int id=2345;
         String codiceErasmus="arasmsinoindeciso";
         String indirizzo="via delle piovre";
         String dipartimento="NASA";

        assertNotNull(tepk);

        tepk.setId(id);
        assertEquals(id, tepk.getId());

        tepk.setCodiceErasmus(codiceErasmus);
        assertEquals(codiceErasmus, tepk.getCodiceErasmus());

        tepk.setIndirizzo(indirizzo);
        assertEquals(indirizzo,tepk.getIndirizzo());

        tepk.setDipartimento(dipartimento);
        assertEquals(dipartimento,tepk.getDipartimento());



    }
}