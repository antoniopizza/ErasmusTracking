package main.java.it.unisa.ErasmusTracking.model.jpa;
import static org.junit.jupiter.api.Assertions.*;

import main.java.it.unisa.ErasmusTracking.bean.Ticket;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


class TicketManagerTest {

  private static TicketManager ticketManager;
  private static Ticket ticket;
  private static Integer id = 0;

  @BeforeAll
  static void setUp() throws SQLException {
    try {
      ticketManager = new TicketManager("", "", "");
    } catch(Exception e) {
      e.printStackTrace();
    }finally {
      ticketManager = new TicketManager("erasmusTracking","root","root");
    }

  }

  @Test
  void testDoSave() {
    System.out.println("doSave");

    ticket = new Ticket();
    ticket.setMittente(2);
    ticket.setDestinatario(1);
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    boolean ok = false;
    try {
      ticketManager.doSave(ticket);
      ok = true;
    } catch (Exception e) {
      ok  = false;
    }
    assertTrue(ok);

    List<Ticket> list = ticketManager.doRetrieveAll();
    Ticket bean = list.get(list.size()-1);
    ticketManager.doDelete(bean.getId());
  }

  @Test
  void testDoDelete() {
    System.out.println("doDelete");

    ticket = new Ticket();
    ticket.setMittente(2);
    ticket.setDestinatario(1);
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ticketManager.doSave(ticket);

    List<Ticket> list = ticketManager.doRetrieveAll();
    Ticket bean = list.get(list.size()-1);
    boolean ok = false;
    try {
      ticketManager.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);
  }

  @Test
  void testDoRetrieveAll() {
    System.out.println("doRetrieveAll");

    ticket = new Ticket();
    ticket.setMittente(2);
    ticket.setDestinatario(1);
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ticketManager.doSave(ticket);

    List<Ticket> list = ticketManager.doRetrieveAll();
    assertNotEquals(0,list.size());

    Ticket bean = list.get(list.size()-1);
    ticketManager.doDelete(bean.getId());
  }

  @Test
  void testDoRetrieveById() {
    System.out.println("doRetrieveById");
    ticket = ticketManager.doRetrieveById(id);
    assertEquals(0, ticket.getId());
  }

  @Test
  void testDoClose() {
    System.out.println("doClose");

    ticket = new Ticket();
    ticket.setMittente(2);
    ticket.setDestinatario(1);
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ticketManager.doSave(ticket);

    List<Ticket> list = ticketManager.doRetrieveAll();

    ticket = list.get(list.size()-1);
    boolean ok = false;
    try{
      ticketManager.doClose(ticket.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);
  }

  @Test
  void testDoRetrieveByIdCoordinatore() {
    System.out.println("doRetrieveByIdCoordinatore");

    ticket = new Ticket();
    ticket.setMittente(2);
    ticket.setDestinatario(1);
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ticketManager.doSave(ticket);

    List<Ticket> list = ticketManager.doRetrieveByIdCoordinatore(ticket.getDestinatario());
    Iterator<Ticket> i = list.iterator();
    boolean ok = true;
    while (i.hasNext()) {
      Ticket bean = i.next();
      if (bean.getDestinatario() != 1) {
        ok = false;
      }
    }
    assertTrue(ok);
  }

  @Test
  void doRetrieveByIdStudente() {
    System.out.println("doRetrieveByIdStudente");

    ticket = new Ticket();
    ticket.setMittente(2);
    ticket.setDestinatario(1);
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ticketManager.doSave(ticket);

    List<Ticket> list = ticketManager.doRetrieveByIdStudente(ticket.getMittente());
    Iterator<Ticket> i = list.iterator();
    boolean ok = true;
    while (i.hasNext()) {
      Ticket bean = i.next();
      if (bean.getMittente() != 2) {
        ok = false;
      }
    }
    assertTrue(ok);

    Ticket bean = list.get(list.size()-1);
    ticketManager.doDelete(bean.getId());

  }
}