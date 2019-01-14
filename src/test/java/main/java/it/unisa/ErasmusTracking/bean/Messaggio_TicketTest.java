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

    tepk.setId_messaggio(id_messaggio);
    assertEquals(id_messaggio, tepk.getId_messaggio());

    tepk.setContenuto(contenuto);
    assertEquals(contenuto, tepk.getContenuto());

    tepk.setData_invio(datainvio);
    assertEquals(datainvio,tepk.getDataInvio());

    tepk.setOra_invio(orainvio);
    assertEquals(orainvio,tepk.getOraInvio());

    tepk.setTicket_id(ticketid);
    assertEquals(ticketid,tepk.getTicket_id());

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
        tepk.setId_messaggio(id_messaggio);
        other.setId_messaggio(id_messaggio);

        tepk.setContenuto(contenuto);
        other.setContenuto(contenuto);

        tepk.setData_invio(datainvio);
        other.setData_invio(datainvio);

        tepk.setOra_invio(orainvio);
        other.setOra_invio(orainvio);

        tepk.setTicket_id(ticketid);
        other.setTicket_id(ticketid);

        tepk.setProprietario(proprietario);
        other.setProprietario(proprietario);


        //uno not null
        tepk.setId_messaggio(0);
        other.setId_messaggio(id_messaggio);

        tepk.setContenuto(null);
        tepk.setContenuto(contenuto);

        tepk.setData_invio(null);
        other.setData_invio(datainvio);

        tepk.setOra_invio(null);
        other.setOra_invio(orainvio);

        tepk.setTicket_id(0);
        other.setTicket_id(ticketid);

        tepk.setProprietario(0);
        other.setProprietario(proprietario);


        assertFalse(tepk.equals(other));
        //entrambi null
        tepk.setId_messaggio(0);
        other.setId_messaggio(0);

        tepk.setContenuto(null);
        other.setContenuto(null);

        tepk.setData_invio(null);
        other.setData_invio(null);

        tepk.setOra_invio(null);
        other.setOra_invio(null);

        tepk.setTicket_id(0);
        other.setTicket_id(0);

        tepk.setProprietario(0);
        other.setProprietario(0);


        assertFalse(tepk.equals(other));



    }

}