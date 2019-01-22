package main.java.it.unisa.ErasmusTracking.model.jpa;
import static org.junit.jupiter.api.Assertions.*;

import main.java.it.unisa.ErasmusTracking.bean.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


class TicketManagerTest {

  private static TicketManager ticketManager;
  private static AccountManager accountManager;
  private static CoordinatoriManager coordinatoreManager;
  private static SendingInstituteManager sendingInstituteManager;
  private static StudenteManager studenteManager;

  /**
   * Variabili d'istanza necessari per i test
   */
  private static Coordinatore coordinatore;
  private static SendingInstitute sendingInstitute;
  private static Studente studente;
  private static Ticket ticket;

  @BeforeAll
  static void setUp() throws SQLException {
    try {
      ticketManager = new TicketManager("", "", "");
      accountManager = new AccountManager("", "", "");
      coordinatoreManager = new CoordinatoriManager("", "", "");
      sendingInstituteManager = new SendingInstituteManager("", "", "");
      studenteManager = new StudenteManager("", "", "");
    } catch(Exception e) {
      e.printStackTrace();
    }finally {
      ticketManager = new TicketManager("erasmusTracking","root","root");
      accountManager = new AccountManager("erasmusTracking","root","root");
      coordinatoreManager = new CoordinatoriManager("erasmusTracking","root","root");
      sendingInstituteManager = new SendingInstituteManager("erasmusTracking","root","root");
      studenteManager = new StudenteManager("erasmusTracking","root","root");
    }

  }

  @Test
  void testDoSave() {
    System.out.println("doSave");

    /**1-Set Sending Institute*/

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try {
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
    sendingInstitute = listSending.get(listSending.size() - 1);

    /**2-Set Coordinatore*/

    coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

    /**3-Set Studente*/

    studente = new Studente();
    studente.setAnnoAccademico(1);
    studente.setDataDiNascita("12/12/2018");
    studente.setLuogoDiNascita("Caserta");
    studente.setTelefono("3012322297");
    studente.setCicloDiStudi("1-triennale");
    studente.setNazionalita("Italia");
    studente.setSesso("M");
    studente.setEmail("aleoale@live.it");
    studente.setCognome("Poldo");
    studente.setMatricola("0215456332");
    studente.setNome("alessandro");
    studente.setPassword("root");
    studente.setRuolo("studente");
    studente.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      studenteManager.doSave(studente);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
    studente = listStudenti.get(listStudenti.size() - 1);
    assertNotEquals(0, listStudenti.size());

    /**4-Set Ticket*/
    ticket = new Ticket();
    ticket.setMittente(studente.getId());
    ticket.setDestinatario(coordinatore.getId());
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ok = false;
    try {
      ticketManager.doSave(ticket);
      ok = true;
    } catch (Exception e) {
      ok  = false;
    }
    assertTrue(ok);

    List<Ticket> listTicket = ticketManager.doRetrieveAll();
    ticket = listTicket.get(listTicket.size()-1);

    ok = false;
    try {
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ticketManager.doDelete(ticket.getId());

      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoDelete()  {
    System.out.println("doSave");

    /**1-Set Sending Institute*/

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try {
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
    sendingInstitute = listSending.get(listSending.size() - 1);

    /**2-Set Coordinatore*/

    coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

    /**3-Set Studente*/

    studente = new Studente();
    studente.setAnnoAccademico(1);
    studente.setDataDiNascita("12/12/2018");
    studente.setLuogoDiNascita("Caserta");
    studente.setTelefono("3012322297");
    studente.setCicloDiStudi("1-triennale");
    studente.setNazionalita("Italia");
    studente.setSesso("M");
    studente.setEmail("aleoale@live.it");
    studente.setCognome("Poldo");
    studente.setMatricola("0215456332");
    studente.setNome("alessandro");
    studente.setPassword("root");
    studente.setRuolo("studente");
    studente.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      studenteManager.doSave(studente);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
    studente = listStudenti.get(listStudenti.size() - 1);
    assertNotEquals(0, listStudenti.size());

    /**4-Set Ticket*/
    ticket = new Ticket();
    ticket.setMittente(studente.getId());
    ticket.setDestinatario(coordinatore.getId());
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ok = false;
    try {
      ticketManager.doSave(ticket);
      ok = true;
    } catch (Exception e) {
      ok  = false;
    }
    assertTrue(ok);

    List<Ticket> listTicket = ticketManager.doRetrieveAll();
    ticket = listTicket.get(listTicket.size()-1);

    ok = false;
    try {
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ticketManager.doDelete(ticket.getId());

      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoRetrieveAll() {
    System.out.println("doSave");

    /**1-Set Sending Institute*/

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try {
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
    sendingInstitute = listSending.get(listSending.size() - 1);

    /**2-Set Coordinatore*/

    coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

    /**3-Set Studente*/

    studente = new Studente();
    studente.setAnnoAccademico(1);
    studente.setDataDiNascita("12/12/2018");
    studente.setLuogoDiNascita("Caserta");
    studente.setTelefono("3012322297");
    studente.setCicloDiStudi("1-triennale");
    studente.setNazionalita("Italia");
    studente.setSesso("M");
    studente.setEmail("aleoale@live.it");
    studente.setCognome("Poldo");
    studente.setMatricola("0215456332");
    studente.setNome("alessandro");
    studente.setPassword("root");
    studente.setRuolo("studente");
    studente.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      studenteManager.doSave(studente);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
    studente = listStudenti.get(listStudenti.size() - 1);
    assertNotEquals(0, listStudenti.size());

    /**4-Set Ticket*/
    ticket = new Ticket();
    ticket.setMittente(studente.getId());
    ticket.setDestinatario(coordinatore.getId());
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ok = false;
    try {
      ticketManager.doSave(ticket);
      ok = true;
    } catch (Exception e) {
      ok  = false;
    }
    assertTrue(ok);

    List<Ticket> listTicket = ticketManager.doRetrieveAll();
    ticket = listTicket.get(listTicket.size()-1);

    ok = false;
    try {
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ticketManager.doDelete(ticket.getId());

      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoRetrieveById() {
    System.out.println("doSave");

    /**1-Set Sending Institute*/

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try {
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
    sendingInstitute = listSending.get(listSending.size() - 1);

    /**2-Set Coordinatore*/

    coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

    /**3-Set Studente*/

    studente = new Studente();
    studente.setAnnoAccademico(1);
    studente.setDataDiNascita("12/12/2018");
    studente.setLuogoDiNascita("Caserta");
    studente.setTelefono("3012322297");
    studente.setCicloDiStudi("1-triennale");
    studente.setNazionalita("Italia");
    studente.setSesso("M");
    studente.setEmail("aleoale@live.it");
    studente.setCognome("Poldo");
    studente.setMatricola("0215456332");
    studente.setNome("alessandro");
    studente.setPassword("root");
    studente.setRuolo("studente");
    studente.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      studenteManager.doSave(studente);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
    studente = listStudenti.get(listStudenti.size() - 1);
    assertNotEquals(0, listStudenti.size());

    /**4-Set Ticket*/
    ticket = new Ticket();
    ticket.setMittente(studente.getId());
    ticket.setDestinatario(coordinatore.getId());
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ok = false;
    try {
      ticketManager.doSave(ticket);
      ok = true;
    } catch (Exception e) {
      ok  = false;
    }
    assertTrue(ok);

    List<Ticket> listTicket = ticketManager.doRetrieveAll();
    ticket = listTicket.get(listTicket.size()-1);

    ok = false;
    try {
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ticketManager.doDelete(ticket.getId());

      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoClose() {
    System.out.println("doSave");

    /**1-Set Sending Institute*/

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try {
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
    sendingInstitute = listSending.get(listSending.size() - 1);

    /**2-Set Coordinatore*/

    coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

    /**3-Set Studente*/

    studente = new Studente();
    studente.setAnnoAccademico(1);
    studente.setDataDiNascita("12/12/2018");
    studente.setLuogoDiNascita("Caserta");
    studente.setTelefono("3012322297");
    studente.setCicloDiStudi("1-triennale");
    studente.setNazionalita("Italia");
    studente.setSesso("M");
    studente.setEmail("aleoale@live.it");
    studente.setCognome("Poldo");
    studente.setMatricola("0215456332");
    studente.setNome("alessandro");
    studente.setPassword("root");
    studente.setRuolo("studente");
    studente.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      studenteManager.doSave(studente);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
    studente = listStudenti.get(listStudenti.size() - 1);
    assertNotEquals(0, listStudenti.size());

    /**4-Set Ticket*/
    ticket = new Ticket();
    ticket.setMittente(studente.getId());
    ticket.setDestinatario(coordinatore.getId());
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ok = false;
    try {
      ticketManager.doSave(ticket);
      ok = true;
    } catch (Exception e) {
      ok  = false;
    }
    assertTrue(ok);

    List<Ticket> listTicket = ticketManager.doRetrieveAll();
    ticket = listTicket.get(listTicket.size()-1);
    assertNotEquals(0,listTicket);

    ok = false;
    try{
      ticketManager.doClose(ticket.getId());
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);


    ok = false;
    try {
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ticketManager.doDelete(ticket.getId());

      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoRetrieveByIdCoordinatore() {
    System.out.println("doSave");

    /**1-Set Sending Institute*/

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try {
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
    sendingInstitute = listSending.get(listSending.size() - 1);

    /**2-Set Coordinatore*/

    coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

    /**3-Set Studente*/

    studente = new Studente();
    studente.setAnnoAccademico(1);
    studente.setDataDiNascita("12/12/2018");
    studente.setLuogoDiNascita("Caserta");
    studente.setTelefono("3012322297");
    studente.setCicloDiStudi("1-triennale");
    studente.setNazionalita("Italia");
    studente.setSesso("M");
    studente.setEmail("aleoale@live.it");
    studente.setCognome("Poldo");
    studente.setMatricola("0215456332");
    studente.setNome("alessandro");
    studente.setPassword("root");
    studente.setRuolo("studente");
    studente.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      studenteManager.doSave(studente);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
    studente = listStudenti.get(listStudenti.size() - 1);
    assertNotEquals(0, listStudenti.size());

    /**4-Set Ticket*/
    ticket = new Ticket();
    ticket.setMittente(studente.getId());
    ticket.setDestinatario(coordinatore.getId());
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ok = false;
    try {
      ticketManager.doSave(ticket);
      ok = true;
    } catch (Exception e) {
      ok  = false;
    }
    assertTrue(ok);

    List<Ticket> listTicket = ticketManager.doRetrieveAll();
    ticket = listTicket.get(listTicket.size()-1);
    assertNotEquals(0,listTicket);

    List<Ticket> ris = ticketManager.doRetrieveByIdCoordinatore(coordinatore.getId());
    assertNotEquals(0,ris);

    ok = false;
    try {
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ticketManager.doDelete(ticket.getId());

      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void doRetrieveByIdStudente() {
    System.out.println("doSave");

    /**1-Set Sending Institute*/

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try {
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
    sendingInstitute = listSending.get(listSending.size() - 1);

    /**2-Set Coordinatore*/

    coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

    /**3-Set Studente*/

    studente = new Studente();
    studente.setAnnoAccademico(1);
    studente.setDataDiNascita("12/12/2018");
    studente.setLuogoDiNascita("Caserta");
    studente.setTelefono("3012322297");
    studente.setCicloDiStudi("1-triennale");
    studente.setNazionalita("Italia");
    studente.setSesso("M");
    studente.setEmail("aleoale@live.it");
    studente.setCognome("Poldo");
    studente.setMatricola("0215456332");
    studente.setNome("alessandro");
    studente.setPassword("root");
    studente.setRuolo("studente");
    studente.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      studenteManager.doSave(studente);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
    studente = listStudenti.get(listStudenti.size() - 1);
    assertNotEquals(0, listStudenti.size());

    /**4-Set Ticket*/
    ticket = new Ticket();
    ticket.setMittente(studente.getId());
    ticket.setDestinatario(coordinatore.getId());
    ticket.setObject("Richiesta erasmus");
    ticket.setStato("aperto");
    ticket.setDatacreazione("15/02/2018");

    ok = false;
    try {
      ticketManager.doSave(ticket);
      ok = true;
    } catch (Exception e) {
      ok  = false;
    }
    assertTrue(ok);

    List<Ticket> listTicket = ticketManager.doRetrieveAll();
    ticket = listTicket.get(listTicket.size()-1);
    assertNotEquals(0,listTicket);

    List<Ticket> ris = ticketManager.doRetrieveByIdStudente(studente.getId());
    assertNotEquals(0,ris);


    ok = false;
    try {
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ticketManager.doDelete(ticket.getId());

      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }
}