package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    @Test
    void testSetandGet()  {
        Ticket tepk = new Ticket();
         int id=12345;
         String object="oggetto del ticket";
         int mittente=2345;
         int destinatario=654;
         String stato="Aperto";
         String datacreazione="1234567";
         String nomeMittente="ret";
         String nomeDestinatario="arded";

        assertNotNull(tepk);

        tepk.setId(id);
        assertEquals(id, tepk.getId());

        tepk.setObject(object);
        assertEquals(object, tepk.getObject());

        tepk.setMittente(mittente);
        assertEquals(mittente,tepk.getMittente());

        tepk.setStato(stato);
        assertEquals(stato,tepk.getStato());

        tepk.setDatacreazione(datacreazione);
        assertEquals(datacreazione,tepk.getDataCreazione());

        tepk.setDestinatario(destinatario);
        assertEquals(destinatario,tepk.getDestinatario());



        tepk.setNomeMittente(nomeMittente);
        assertEquals(nomeMittente,tepk.getNomeMittente());

        tepk.setNomeDestinatario(nomeDestinatario);
        assertEquals(nomeDestinatario,tepk.getNomeDestinatario());

    }
}