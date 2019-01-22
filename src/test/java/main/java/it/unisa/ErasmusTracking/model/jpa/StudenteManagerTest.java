package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudenteManagerTest {
  private static StudenteManager classUnderTest;
  private static CoordinatoriManager coordinatoreManager;
  private static SendingInstituteManager sendingInstituteManager;
  private static AccountManager m;

  private static SendingInstitute sendingInstitute;
  private static Coordinatore coordinatore;
  private static Studente bean;


  @BeforeAll
  static void setUp() throws Exception {
    classUnderTest = new StudenteManager("erasmustracking","root","root");
    coordinatoreManager = new CoordinatoriManager("erasmustracking","root","root");
    m = new AccountManager("erasmusTracking","root","root");
    sendingInstituteManager = new SendingInstituteManager("erasmusTracking","root","root");
  }

  @Test
  void doSave() throws SQLException {
    System.out.println("doSave");
    /**1-Set Sending Institute*/
    SendingInstitute sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try{
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
    sendingInstitute = listSending.get(listSending.size() - 1);

    /**2-Set Coordinatore*/

    Coordinatore coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());
    coordinatore = list.get(list.size()-1);

    /**3-Set Studente*/

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list1.size());


    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);


    //bean con data nascita e luogo nascita null

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    ok = false;
    try{
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
    sendingInstitute = listSending.get(listSending.size() - 1);


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
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());
    coordinatore = list.get(list.size()-1);

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(1);

     ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    assertTrue(ok);

    list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, list1.size());
    bean = list1.get(list1.size()-1);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void doDelete() {
    System.out.println("doDelete");
    /**1-Set Sending Institute*/
    SendingInstitute sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try{
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);
    List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
    sendingInstitute = listSending.get(listSending.size() - 1);

    /**2-Set Coordinatore*/

    Coordinatore coordinatore = new Coordinatore();
    coordinatore.setNome("Alessandro");
    coordinatore.setCognome("Rigido");
    coordinatore.setPassword("root");
    coordinatore.setEmail("a.rigido1@studenti.unisa.it");
    coordinatore.setRuolo("coordinatore");
    coordinatore.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      coordinatoreManager.doSave(coordinatore);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());
    coordinatore = list.get(list.size()-1);

    /**3-Set Studente*/

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list1.size());


    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void doRetrieveAll() {
    System.out.println("doRetrieveAll");

    /**1-Set Sending Institute*/
    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try{
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    }catch(Exception e){
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
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());
    coordinatore = list.get(list.size()-1);

    /**3-Set Studente*/

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list1.size());

    List<Studente> ris = classUnderTest.doRetrieveAll();
    assertNotEquals(0,ris.size());

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void doRetrieveById() {
    System.out.println("doRetrieveAll");

    /**1-Set Sending Institute*/
    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try{
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    }catch(Exception e){
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
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());
    coordinatore = list.get(list.size()-1);

    /**3-Set Studente*/

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list1.size());

    Studente ris = classUnderTest.doRetrieveById(bean.getId());
    assertNotEquals(null,ris);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void doRetrieveByIdCoordinatore() {
    System.out.println("doRetrieveAll");

    /**1-Set Sending Institute*/
    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try{
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    }catch(Exception e){
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
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());
    coordinatore = list.get(list.size()-1);

    /**3-Set Studente*/

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list1.size());

    List<Studente> ris = classUnderTest.doRetrieveByCoordinatore(coordinatore.getId());
    assertNotEquals(0,ris);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void doRetrieveByMatricola() {
    System.out.println("doRetrieveAll");

    /**1-Set Sending Institute*/
    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try{
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    }catch(Exception e){
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
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());
    coordinatore = list.get(list.size()-1);

    /**3-Set Studente*/

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list1.size());

    Studente ris = classUnderTest.doRetrieveByMatricola(bean.getMatricola());
    assertNotEquals(null,ris);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void doRetrieveByEmail() {
    System.out.println("doRetrieveAll");

    /**1-Set Sending Institute*/
    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try{
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    }catch(Exception e){
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
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());
    coordinatore = list.get(list.size()-1);

    /**3-Set Studente*/

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Studente> list1 = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list1.size());

    Studente ris = classUnderTest.doRetrieveByEmail(bean.getEmail());
    assertNotEquals(null,ris);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void testDoUpdate() {
    System.out.println("doRetrieveAll");

    /**1-Set Sending Institute*/
    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try{
      sendingInstituteManager.doSave(sendingInstitute);
      ok = true;
    }catch(Exception e){
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
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
    assertNotEquals(0, list.size());
    coordinatore = list.get(list.size()-1);

    /**3-Set Studente*/

    bean = new Studente();
    bean.setAnnoAccademico(1);
    bean.setDataDiNascita("12/12/2018");
    bean.setLuogoDiNascita("Caserta");
    bean.setTelefono("3012322297");
    bean.setCicloDiStudi("1-triennale");
    bean.setNazionalita("Italia");
    bean.setSesso("M");
    bean.setEmail("aleoale@live.it");
    bean.setCognome("Poldo");
    bean.setMatricola("0215456332");
    bean.setNome("alessandro");
    bean.setPassword("root");
    bean.setRuolo("studente");
    bean.setIdCoordinatore(coordinatore.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok =true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }
    assertTrue(ok);

    List<Studente> list1 =  classUnderTest.doRetrieveAll();
    bean = list1.get(list1.size()-1);
    assertNotEquals(0,list1.size());

    bean.setMatricola("12390g");
    bean.setDataDiNascita("22/12/1991");
    bean.setLuogoDiNascita("Novi Velia");
    bean.setSesso("M");
    bean.setNazionalita("Italiana");
    bean.setTelefono("68909876556789");
    bean.setCicloDiStudi("triennale");
    bean.setAnnoAccademico(2019);

    ok = false;
    try{
      classUnderTest.doUpdate(bean);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);

    ok = false;
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      coordinatoreManager.doDelete(coordinatore.getId());
      m.doDelete(coordinatore.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }
}