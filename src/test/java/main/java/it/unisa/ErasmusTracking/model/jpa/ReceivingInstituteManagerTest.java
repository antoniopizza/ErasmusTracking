package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceivingInstituteManagerTest {
  /**Manager necessari*/
  private static ReceivingInstituteManager manager;
  private static SendingInstituteManager sendingInstituteManager;
  private static StudenteManager studenteManager;
  private static CoordinatoriManager coordinatoreManager;
  private static LocalitaManager localitaManager;
  private static AccountManager m;

  /**Variabili d'istanza*/
  private static ReceivingInstitute receivingInstitute;
  private static Studente studente;
  private static Coordinatore coordinatore;
  private static Localita localita;
  private static SendingInstitute sendingInstitute;

  @BeforeAll
  static void setUp() throws SQLException {
    try {
      manager = new ReceivingInstituteManager("", "", "");
      coordinatoreManager = new CoordinatoriManager("", "", "");
      sendingInstituteManager = new SendingInstituteManager("", "", "");
      m = new AccountManager("", "", "");
      studenteManager = new StudenteManager("", "", "");
      localitaManager = new LocalitaManager("", "", "");
    } catch(Exception e) {
      e.printStackTrace();
    }finally {
      manager = new ReceivingInstituteManager("erasmusTracking","root","root");
      coordinatoreManager = new CoordinatoriManager("erasmusTracking", "root", "root");
      sendingInstituteManager = new SendingInstituteManager("erasmusTracking", "root", "root");
      m = new AccountManager("erasmusTracking", "root", "root");
      studenteManager = new StudenteManager("erasmusTracking", "root", "root");
      localitaManager = new LocalitaManager("erasmusTracking", "root", "root");
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


    /**4-Set Localita*/
    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(coordinatore.getId());

    ok = false;
    try {
      localitaManager.doSave(localita);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    List<Localita> listLocalita = localitaManager.doRetrieveAll();
    localita = listLocalita.get(listLocalita.size() - 1);
    assertNotEquals(0, listLocalita.size());
    /**5-Set Receiving Institute*/

    receivingInstitute = new ReceivingInstitute();
    receivingInstitute.setEmailContatto("gio@gmail.com");
    receivingInstitute.setNomeContatto("giorgio");
    receivingInstitute.setEmailMentore("fra@gmail.com");
    receivingInstitute.setNomeMentore("franco");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setLocalita(localita.getId());
    receivingInstitute.setWebsite("www.unisa.it");

    ok = false;
    try {
      manager.doSave(receivingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    List<ReceivingInstitute> listReceiving = manager.doRetrieveAll();
    receivingInstitute = listReceiving.get(listReceiving.size() - 1);
    assertNotEquals(0, listReceiving.size());

    ok = false;
    try {
      manager.doDelete(receivingInstitute.getId());
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      m.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoSave1(){
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


    /**4-Set Localita*/
    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(coordinatore.getId());

    ok = false;
    try {
      localitaManager.doSave(localita);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    List<Localita> listLocalita = localitaManager.doRetrieveAll();
    localita = listLocalita.get(listLocalita.size() - 1);
    assertNotEquals(0, listLocalita.size());
    /**5-Set Receiving Institute*/

    receivingInstitute = new ReceivingInstitute();
    receivingInstitute.setNomeMentore("franco");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setLocalita(localita.getId());
    receivingInstitute.setWebsite("www.unisa.it");

    ok = false;
    try {
      manager.doSave(receivingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    List<ReceivingInstitute> listReceiving = manager.doRetrieveAll();
    receivingInstitute = listReceiving.get(listReceiving.size() - 1);
    assertNotEquals(0, listReceiving.size());

    ok = false;
    try {
      manager.doDelete(receivingInstitute.getId());
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      m.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoSaveError() throws SQLException{
    System.out.println("doSave");

    /**1-Set Sending Institute*/

    sendingInstitute = new SendingInstitute();

    boolean ok = false;
    try {
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    try{
      List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
      sendingInstitute = listSending.get(listSending.size() - 1);
    }catch (Exception e){
      e.printStackTrace();
    }

    /**2-Set Coordinatore*/

    coordinatore = new Coordinatore();

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    try {
      ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
      assertNotEquals(0, listCoordinatori.size());
      coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);
    }catch (Exception e){
      e.printStackTrace();
    }
    /**3-Set Studente*/

    studente = new Studente();

    ok = false;
    try {
      studenteManager.doSave(studente);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    try {
      ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
      studente = listStudenti.get(listStudenti.size() - 1);
      assertNotEquals(0, listStudenti.size());
    }catch (Exception e){
      e.printStackTrace();
    }

    /**4-Set Localita*/
    localita = new Localita();

    ok = false;
    try {
      localitaManager.doSave(localita);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    try {
      List<Localita> listLocalita = localitaManager.doRetrieveAll();
      localita = listLocalita.get(listLocalita.size() - 1);
      assertNotEquals(0, listLocalita.size());
    }catch (Exception e){
      e.printStackTrace();
    }
    /**5-Set Receiving Institute*/

    receivingInstitute = new ReceivingInstitute();

    ok = false;
    try {
      manager.doSave(receivingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    try {
      List<ReceivingInstitute> listReceiving = manager.doRetrieveAll();
      receivingInstitute = listReceiving.get(listReceiving.size() - 1);
      assertNotEquals(0, listReceiving.size());
    }catch (Exception e){
      e.printStackTrace();
    }
    ok = false;
    try {
      manager.doDelete(receivingInstitute.getId());
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      m.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoDelete() {
    System.out.println("doDelete");

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


    /**4-Set Localita*/
    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(coordinatore.getId());

    ok = false;
    try {
      localitaManager.doSave(localita);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    List<Localita> listLocalita = localitaManager.doRetrieveAll();
    localita = listLocalita.get(listLocalita.size() - 1);
    assertNotEquals(0, listLocalita.size());
    /**5-Set Receiving Institute*/

    receivingInstitute = new ReceivingInstitute();
    receivingInstitute.setEmailContatto("gio@gmail.com");
    receivingInstitute.setNomeContatto("giorgio");
    receivingInstitute.setEmailMentore("fra@gmail.com");
    receivingInstitute.setNomeMentore("franco");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setLocalita(localita.getId());
    receivingInstitute.setWebsite("www.unisa.it");

    ok = false;
    try {
      manager.doSave(receivingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    List<ReceivingInstitute> listReceiving = manager.doRetrieveAll();
    receivingInstitute = listReceiving.get(listReceiving.size() - 1);
    assertNotEquals(0, listReceiving.size());

    ok = false;
    try {
      manager.doDelete(receivingInstitute.getId());
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      m.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

    receivingInstitute = new ReceivingInstitute();
    ok=false;
    try{
      manager.doDelete(receivingInstitute.getId());
    }catch(Exception e){
      e.printStackTrace();
    }


  }

  @Test
  void testDoDeleteError() throws SQLException{
    System.out.println("doSave");

    /**1-Set Sending Institute*/

    sendingInstitute = new SendingInstitute();

    boolean ok = false;
    try {
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    try{
      List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
      sendingInstitute = listSending.get(listSending.size() - 1);
    }catch (Exception e){
      e.printStackTrace();
    }

    /**2-Set Coordinatore*/

    coordinatore = new Coordinatore();

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    try {
      ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
      assertNotEquals(0, listCoordinatori.size());
      coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);
    }catch (Exception e){
      e.printStackTrace();
    }
    /**3-Set Studente*/

    studente = new Studente();

    ok = false;
    try {
      studenteManager.doSave(studente);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    try {
      ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
      studente = listStudenti.get(listStudenti.size() - 1);
      assertNotEquals(0, listStudenti.size());
    }catch (Exception e){
      e.printStackTrace();
    }

    /**4-Set Localita*/
    localita = new Localita();

    ok = false;
    try {
      localitaManager.doSave(localita);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    try {
      List<Localita> listLocalita = localitaManager.doRetrieveAll();
      localita = listLocalita.get(listLocalita.size() - 1);
      assertNotEquals(0, listLocalita.size());
    }catch (Exception e){
      e.printStackTrace();
    }
    /**5-Set Receiving Institute*/

    receivingInstitute = new ReceivingInstitute();

    ok = false;
    try {
      manager.doSave(receivingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    try {
      List<ReceivingInstitute> listReceiving = manager.doRetrieveAll();
      receivingInstitute = listReceiving.get(listReceiving.size() - 1);
      assertNotEquals(0, listReceiving.size());
    }catch (Exception e){
      e.printStackTrace();
    }
    ok = false;
    try {
      manager.doDelete(receivingInstitute.getId());
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      m.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoRetrieveById() {
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


    /**4-Set Localita*/
    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(coordinatore.getId());

    ok = false;
    try {
      localitaManager.doSave(localita);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    List<Localita> listLocalita = localitaManager.doRetrieveAll();
    localita = listLocalita.get(listLocalita.size() - 1);
    assertNotEquals(0, listLocalita.size());
    /**5-Set Receiving Institute*/

    receivingInstitute = new ReceivingInstitute();
    receivingInstitute.setEmailContatto("gio@gmail.com");
    receivingInstitute.setNomeContatto("giorgio");
    receivingInstitute.setEmailMentore("fra@gmail.com");
    receivingInstitute.setNomeMentore("franco");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setLocalita(localita.getId());
    receivingInstitute.setWebsite("www.unisa.it");

    ok = false;
    try {
      manager.doSave(receivingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    List<ReceivingInstitute> listReceiving = manager.doRetrieveAll();
    receivingInstitute = listReceiving.get(listReceiving.size() - 1);
    assertNotEquals(0, listReceiving.size());

    ReceivingInstitute ris = (ReceivingInstitute) manager.doRetrieveById(receivingInstitute.getId());
    assertNotEquals(null,ris);

    ok = false;
    try {
      manager.doDelete(receivingInstitute.getId());
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      m.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);


  }

  @Test
  void testDoRetrieveAll() {
    System.out.println("doRetrieveAll");

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


    /**4-Set Localita*/
    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(coordinatore.getId());

    ok = false;
    try {
      localitaManager.doSave(localita);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    List<Localita> listLocalita = localitaManager.doRetrieveAll();
    localita = listLocalita.get(listLocalita.size() - 1);
    assertNotEquals(0, listLocalita.size());
    /**5-Set Receiving Institute*/

    receivingInstitute = new ReceivingInstitute();
    receivingInstitute.setEmailContatto("gio@gmail.com");
    receivingInstitute.setNomeContatto("giorgio");
    receivingInstitute.setEmailMentore("fra@gmail.com");
    receivingInstitute.setNomeMentore("franco");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setLocalita(localita.getId());
    receivingInstitute.setWebsite("www.unisa.it");

    ok = false;
    try {
      manager.doSave(receivingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    List<ReceivingInstitute> listReceiving = manager.doRetrieveAll();
    receivingInstitute = listReceiving.get(listReceiving.size() - 1);
    assertNotEquals(0, listReceiving.size());

    ok = false;
    try {
      manager.doDelete(receivingInstitute.getId());
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      m.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);



  }

  @Test
  void testDoUpdate() {
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


    /**4-Set Localita*/
    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(coordinatore.getId());

    ok = false;
    try {
      localitaManager.doSave(localita);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    List<Localita> listLocalita = localitaManager.doRetrieveAll();
    localita = listLocalita.get(listLocalita.size() - 1);
    assertNotEquals(0, listLocalita.size());
    /**5-Set Receiving Institute*/

    receivingInstitute = new ReceivingInstitute();
    receivingInstitute.setEmailContatto("gio@gmail.com");
    receivingInstitute.setNomeContatto("giorgio");
    receivingInstitute.setEmailMentore("fra@gmail.com");
    receivingInstitute.setNomeMentore("franco");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setLocalita(localita.getId());
    receivingInstitute.setWebsite("www.unisa.it");

    ok = false;
    try {
      manager.doSave(receivingInstitute);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    List<ReceivingInstitute> listReceiving = manager.doRetrieveAll();
    receivingInstitute = listReceiving.get(listReceiving.size() - 1);
    assertNotEquals(0, listReceiving.size());

    receivingInstitute.setNomeContatto("tizio");
    receivingInstitute.setEmailContatto("tizio@ciao.it");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setNomeMentore("Carlo");
    receivingInstitute.setEmailMentore("chardido@gmail.com");
    receivingInstitute.setWebsite("www.unisa.it");
    receivingInstitute.setLocalita(localitaManager.doRetrieveById(localita.getId()).getId());

    ok = false;
    try{
      manager.doUpdate(receivingInstitute);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

    ok = false;
    try {
      manager.doDelete(receivingInstitute.getId());
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      m.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);


  }


}