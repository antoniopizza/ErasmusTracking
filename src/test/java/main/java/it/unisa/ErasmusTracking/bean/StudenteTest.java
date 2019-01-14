package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudenteTest {

    @Test
    void testSetandGet()  {
      Studente tepk = new Studente();
         String sesso="maschio";
         String dataDiNascita="12343444";
         String luogoDiNascita="Salerno";
         String nazionalita="italiana";
         String cicloDiStudi="mangiapregaama";
         String codiceMateria="prima";
         String telefono="iphonex";
         int annoAccademico=1;
         String matricola="0512109513";
         int idCoordinatore=123456789;


        assertNotNull(tepk);

        tepk.setNome("Dario");
        assertEquals("Dario", tepk.getNome());

        tepk.setCognome("Scola");
        assertEquals("Scola", tepk.getCognome());

        tepk.setRuolo("CAMPIONE");
        assertEquals("CAMPIONE",tepk.getRuolo());

        tepk.setId(12);
        assertEquals(12,tepk.getId());

        tepk.setEmail("darioscola015@gmail.com");
        assertEquals("darioscola015@gmail.com",tepk.getEmail());

        tepk.setPassword("bellissimo");
        assertEquals("bellissimo",tepk.getPassword());

        tepk.setDoc(docs);
        assertEquals(docs,tepk.getDoc());

    }

}