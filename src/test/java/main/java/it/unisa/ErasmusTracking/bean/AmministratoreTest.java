package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AmministratoreTest {
private Amministratore tepk = new Amministratore();

    @Test
    void testSetandGet()  {

        Documenti documento = new Documenti();
        ArrayList<Documenti> docs = new ArrayList<>();
        docs.add(documento);



        assertNotNull(tepk);

        tepk.setNome("Dario");
        assertEquals("Dario", tepk.getNome());

        tepk.setCognome("Scola");
        assertEquals("Scola", tepk.getCognome());

        tepk.setRuolo("Amministratore");
        assertEquals("Amministratore",tepk.getRuolo());

        tepk.setId(12);
        assertEquals(12,tepk.getId());

        tepk.setEmail("darioscola015@gmail.com");
        assertEquals("darioscola015@gmail.com",tepk.getEmail());

        tepk.setPassword("bellissimo");
        assertEquals("bellissimo",tepk.getPassword());

        tepk.setDoc(docs);
        assertEquals(docs,tepk.getDoc());

    }
   @Test
   public String tostringtest() {

       Amministratore tepk = new Amministratore(); // you didn't supply the object, so I guessed

       //riempo campo ruolo
       tepk.setNome("Dario");
       tepk.setCognome("Scola");
       tepk.setId(12);
       tepk.setEmail("darioscola015@gmail.com");
       tepk.setPassword("123456");
        tepk.setRuolo("Amministratore");

       // put the expected value here
       String expected = tepk.toString() +  "\n"
               +
               " Ruolo = Amministratore" + "\n";


       assertEquals(expected, tepk.toString());
       return tepk.toString();



   }
}

