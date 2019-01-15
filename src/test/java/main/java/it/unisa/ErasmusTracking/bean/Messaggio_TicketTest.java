package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Messaggio_TicketTest {
@Test
void testSetandGet()  {

    MessaggioTicket tepk = new MessaggioTicket();
    int id_messaggio=12;
    String contenuto="Ciao";
    String datainvio="12345676";
    String orainvio="1456";
    int ticketid=1234;
    int proprietario=7654;



    assertNotNull(tepk);

    tepk.setIdMessaggio(id_messaggio);
    assertEquals(id_messaggio, tepk.getIdMessaggio());

    tepk.setContenuto(contenuto);
    assertEquals(contenuto, tepk.getContenuto());

    tepk.setDataInvio(datainvio);
    assertEquals(datainvio,tepk.getDataInvio());

    tepk.setOraInvio(orainvio);
    assertEquals(orainvio,tepk.getOraInvio());

    tepk.setTicketId(ticketid);
    assertEquals(ticketid,tepk.getTicketId());

    tepk.setProprietario(proprietario);
    assertEquals(proprietario,tepk.getProprietario());


}

    @Test
    public void equalstest(){
        MessaggioTicket tepk = new MessaggioTicket();
        int id_messaggio=12;
        String contenuto="Ciao";
        String datainvio="12345676";
        String orainvio="1456";
        int ticketid=1234;
        int proprietario=7654;


        assertFalse(tepk.equals(null));
        assertTrue(tepk.equals(tepk));
        Object o = new Object();
        assertFalse(tepk.equals(o));
        MessaggioTicket other = new MessaggioTicket();

        //entrambi not null check
        tepk.setIdMessaggio(id_messaggio);
        other.setIdMessaggio(id_messaggio);

        tepk.setContenuto(contenuto);
        other.setContenuto(contenuto);

        tepk.setDataInvio(datainvio);
        other.setDataInvio(datainvio);

        tepk.setOraInvio(orainvio);
        other.setOraInvio(orainvio);

        tepk.setTicketId(ticketid);
        other.setTicketId(ticketid);

        tepk.setProprietario(proprietario);
        other.setProprietario(proprietario);


        //uno not null
        tepk.setIdMessaggio(0);
        other.setIdMessaggio(id_messaggio);

        tepk.setContenuto(null);
        tepk.setContenuto(contenuto);

        tepk.setDataInvio(null);
        other.setDataInvio(datainvio);

        tepk.setOraInvio(null);
        other.setOraInvio(orainvio);

        tepk.setTicketId(0);
        other.setTicketId(ticketid);

        tepk.setProprietario(0);
        other.setProprietario(proprietario);


        assertFalse(tepk.equals(other));
        //entrambi null
        tepk.setIdMessaggio(0);
        other.setIdMessaggio(0);

        tepk.setContenuto(null);
        other.setContenuto(null);

        tepk.setDataInvio(null);
        other.setDataInvio(null);

        tepk.setOraInvio(null);
        other.setOraInvio(null);

        tepk.setTicketId(0);
        other.setTicketId(0);

        tepk.setProprietario(0);
        other.setProprietario(0);


        assertFalse(tepk.equals(other));



    }

}