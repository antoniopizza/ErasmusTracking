package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class CoordinatoreTest {
    ArrayList<Studente> studente = new ArrayList<>();
    ArrayList<Localita> località = new ArrayList<>();
    Studente studentesingolo = new Studente();
    Localita localitasingola = new Localita();
 Coordinatore tepk = new Coordinatore( "Dario", "Scola",località,
         "darioscola015@gmail.com", "12345", "Coordinatore",
     234, studentesingolo,12);

    @Test
    void testSetandGet() {

        ArrayList<Studente> studente = new ArrayList<>();
        ArrayList<Localita> località = new ArrayList<>();
        Studente studentesingolo = new Studente();
        Localita localitasingola = new Localita();


        assertNotNull(tepk);

        tepk.setNome("Dario");
        assertEquals("Dario", tepk.getNome());

        tepk.setCognome("Scola");
        assertEquals("Scola", tepk.getCognome());

        tepk.setLocalita(località);
        assertEquals(località, tepk.getLocalita());

        tepk.setSendingInstitute(12345);
        assertEquals(12345, tepk.getSendingInstitute());


        tepk.setRuolo("Coordinatore");
        assertEquals("Coordinatore", tepk.getRuolo());

        tepk.setEmail("darioscola015@gmail.com");
        assertEquals("darioscola015@gmail.com", tepk.getEmail());

        tepk.setPassword("bellissimo");
        assertEquals("bellissimo", tepk.getPassword());


        tepk.setStudente(studente);
        assertEquals(studente, tepk.getStudente());

        tepk.addStudente(studentesingolo);
        assertEquals(studente, tepk.getStudente());

        tepk.addLocalita(localitasingola);
        assertEquals(località, tepk.getLocalita());
    }

    }