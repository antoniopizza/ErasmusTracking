package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SendingInstituteManagerTest {

  SendingInstituteManager manager = new SendingInstituteManager("erasmustracking", "root", "root");
  SendingInstitute sendingInstitute;
  Integer id = 0;

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