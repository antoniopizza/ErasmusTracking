package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SendingInstituteManagerTest {

  private static SendingInstituteManager manager = new SendingInstituteManager("erasmustracking", "root", "root");
  private static SendingInstitute sendingInstitute;
  private static Integer id = 0;

  @BeforeAll
  static void setUp() throws SQLException {
    try {
      manager = new SendingInstituteManager("", "", "");
    } catch(Exception e) {
      e.printStackTrace();
    }finally {
      manager = new SendingInstituteManager("erasmusTracking","root","root");
    }

  }

  @Test
  void testDoSave() {
    System.out.println("doSave");

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try {
      manager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    List<SendingInstitute>  list = (ArrayList<SendingInstitute>) manager.doRetrieveAll();
    SendingInstitute bean = list.get(list.size() - 1);
    manager.doDelete(bean.getId());
  }

  @Test
  void testDoSave1() {
    System.out.println("doSave con campi nulli");

    sendingInstitute = new SendingInstitute();

    boolean ok = false;
    try {
      manager.doSave(sendingInstitute);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    List<SendingInstitute>  list = (ArrayList<SendingInstitute>) manager.doRetrieveAll();
    SendingInstitute bean = list.get(list.size() - 1);
    manager.doDelete(bean.getId());

  }

  @Test
  void testDoDelete() {
    System.out.println("doDelete");

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    manager.doSave(sendingInstitute);

    List<SendingInstitute> list = (ArrayList<SendingInstitute>) manager.doRetrieveAll();
    SendingInstitute bean = list.get(list.size() - 1);

    boolean ok = false;
    try {
      manager.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

  }

  @Test
  void testDoRetrieveAll() {
    System.out.println("doRetrieveAll");

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    manager.doSave(sendingInstitute);

    List<SendingInstitute> list = (ArrayList<SendingInstitute>) manager.doRetrieveAll();
    assertNotEquals(0, list.size());

    SendingInstitute bean = list.get(list.size() - 1);
    manager.doDelete(bean.getId());

  }

  @Test
  void testDoRetrieveById() {
    System.out.println("doRetrieveById");
    sendingInstitute = manager.doRetrieveById(id);
    assertEquals(0, sendingInstitute.getId());

  }
}