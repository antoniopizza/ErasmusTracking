package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocalitaManagerTest {

  private static LocalitaManager localitaManager;
  private static AccountManager accountManager;
  private static SendingInstituteManager sendingInstituteManager;
  private static CoordinatoriManager coordinatoreManager;
  private static StudenteManager studenteManager;
  private static Localita localita;
  private static SendingInstitute sendingInstitute;
  private static Coordinatore coordinatore;
  private static Studente studente;


  @BeforeAll
  static void setUp(){
    try{
      localitaManager = new LocalitaManager("","","");
      accountManager = new AccountManager("","","");
      coordinatoreManager = new CoordinatoriManager("","","");
      studenteManager = new StudenteManager("","","");
      sendingInstituteManager = new SendingInstituteManager("","","");
    }catch(Exception e){
      e.printStackTrace();
    }finally {
      localitaManager  = new LocalitaManager("erasmusTracking", "root", "root1234");
      accountManager = new AccountManager("erasmusTracking", "root", "root1234");
      sendingInstituteManager = new SendingInstituteManager("erasmusTracking", "root", "root1234");
      coordinatoreManager = new CoordinatoriManager("erasmusTracking", "root", "root1234");
      studenteManager = new StudenteManager("erasmusTracking", "root", "root1234");
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

    ok = false;
    try {
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
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

    ok = false;
    try {
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
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

    ok = false;
    try {
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoRetrieveByCity() {
    System.out.println("doRetrieveByCity");

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

    List<Localita> ris = localitaManager.doRetrieveByCity(localita.getCitta());
    assertNotEquals(0,ris);

    ok = false;
    try {
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
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
    System.out.println("doRetrieveById");

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

    Localita ris = localitaManager.doRetrieveById(localita.getId());
    assertNotEquals(null,ris);

    ok = false;
    try {
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }
    /**
     *  BY CODICE ERASMUS
     *  */

  @Test
  void testDoRetrieveByCodiceErasmus(){
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

    List<Localita> ris = localitaManager.doRetrieveByCodiceErasmus(localita.getCodiceErasmus());
    assertNotEquals(0,ris);

    ok = false;
    try {
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  /** BY NOME*/

  @Test
  void testDoRetrieveByNome() {
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

    List<Localita> ris = localitaManager.doRetrieveByNome(localita.getNome());
    assertNotEquals(0,ris);

    ok = false;
    try {
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
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

    List<Localita> ris = localitaManager.doRetrieveByIdCoordinatore(coordinatore.getId());
    assertNotEquals(0,ris);

    ok = false;
    try {
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoRetrieveByNation() {
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

    List<Localita> ris = localitaManager.doRetrieveByNation(localita.getNazione());
    assertNotEquals(0,ris);

    ok = false;
    try {
      localitaManager.doDelete(localita.getId());
      studenteManager.doDelete(studente.getId());
      accountManager.doDelete(studente.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      accountManager.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

}
