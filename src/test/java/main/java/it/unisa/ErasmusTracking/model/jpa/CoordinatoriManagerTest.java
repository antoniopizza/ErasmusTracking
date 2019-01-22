package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CoordinatoriManagerTest {
  private static CoordinatoriManager classUnderTest;
  private static SendingInstituteManager sendingInstituteManager;
  private static AccountManager m;


  private static Coordinatore bean;
  private static SendingInstitute sendingInstitute;


  @BeforeAll
  static void setUp() throws SQLException {
    classUnderTest = new CoordinatoriManager("erasmustracking","root","root");
    m =new AccountManager("erasmusTracking","root","root");
    sendingInstituteManager = new SendingInstituteManager("erasmusTracking","root","root");
  }

  @Test
  void testDoSave() throws SQLException{
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

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoDelete() throws SQLException {
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

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void testDoRetrieveById() throws SQLException {
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

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    Coordinatore ris = classUnderTest.doRetrieveById(bean.getId());
    assertNotEquals(null,ris);

    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void testDoRetrieveAll() throws SQLException {
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

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }


  @Test
  void testDoRetrieveByEmail() throws SQLException {
    System.out.println("doRetrieveByEmail");

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

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    Coordinatore ris = classUnderTest.doRetrieveByEmail(m.doRetrieveById(bean.getId()).getEmail());
    assertNotEquals(null,ris);

    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void testDoUpdate() throws SQLException {
    System.out.println("doRetrieveByEmail");

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

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    bean.setSendingInstitute(sendingInstitute.getId());

    ok = false;
    try{
      classUnderTest.doUpdate(bean);
      ok = true;

    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }

    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);
  }
}