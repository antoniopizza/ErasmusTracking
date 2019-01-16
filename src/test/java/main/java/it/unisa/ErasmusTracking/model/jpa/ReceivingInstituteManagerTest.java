package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.ReceivingInstitute;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceivingInstituteManagerTest {

  ReceivingInstituteManager manager = new ReceivingInstituteManager("erasmustracking", "root", "root");
  ReceivingInstitute receivingInstitute;
  Integer id = 0;

  @Test
  void testDoSave() {
    System.out.println("doSave");

    receivingInstitute = new ReceivingInstitute();
    receivingInstitute.setCodiceErasmus("88478");
    receivingInstitute.setEmailContatto("gio@gmail.com");
    receivingInstitute.setNomeContatto("giorgio");
    receivingInstitute.setEmailMentore("fra@gmail.com");
    receivingInstitute.setNomeMentore("franco");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setLocalita(1);
    receivingInstitute.setWebsite("www.unisa.it");

    boolean ok = false;
    try {
      manager.doSave(receivingInstitute);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    List<ReceivingInstitute> list = manager.doRetrieveAll();
    ReceivingInstitute bean = list.get(list.size() - 1);
    manager.doDelete(bean.getId());
  }

  @Test
  void testDoDelete() {
    System.out.println("doDelete");

    receivingInstitute = new ReceivingInstitute();
    receivingInstitute.setCodiceErasmus("88478");
    receivingInstitute.setEmailContatto("gio@gmail.com");
    receivingInstitute.setNomeContatto("giorgio");
    receivingInstitute.setEmailMentore("fra@gmail.com");
    receivingInstitute.setNomeMentore("franco");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setLocalita(1);
    receivingInstitute.setWebsite("www.unisa.it");

    List<ReceivingInstitute> list = manager.doRetrieveAll();
    ReceivingInstitute bean = list.get(list.size() - 1);

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
  void testDoRetrieveById() {
    System.out.println("doRetrieveById");
    receivingInstitute = (ReceivingInstitute) manager.doRetrieveById(id);
    assertEquals(0, receivingInstitute.getId());
  }

  @Test
  void testDoRetrieveAll() {
    System.out.println("doRetrieveAll");

    receivingInstitute = new ReceivingInstitute();
    receivingInstitute.setCodiceErasmus("88478");
    receivingInstitute.setEmailContatto("gio@gmail.com");
    receivingInstitute.setNomeContatto("giorgio");
    receivingInstitute.setEmailMentore("fra@gmail.com");
    receivingInstitute.setNomeMentore("franco");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setLocalita(1);
    receivingInstitute.setWebsite("www.unisa.it");

    manager.doSave(receivingInstitute);

    List<ReceivingInstitute> list = manager.doRetrieveAll();
    assertNotEquals(0, list.size());

    ReceivingInstitute bean = list.get(list.size() - 1);
    manager.doDelete(bean.getId());

  }

  @Test
  void testDoUpdate() {
   System.out.println("doUpdate");

    receivingInstitute = new ReceivingInstitute();
    receivingInstitute.setCodiceErasmus("88478");
    receivingInstitute.setEmailContatto("gio@gmail.com");
    receivingInstitute.setNomeContatto("giorgio");
    receivingInstitute.setEmailMentore("fra@gmail.com");
    receivingInstitute.setNomeMentore("franco");
    receivingInstitute.setSizeOfEnterprise("media");
    receivingInstitute.setLocalita(1);
    receivingInstitute.setWebsite("www.unisa.it");

    manager.doSave(receivingInstitute);

    List<ReceivingInstitute> list = manager.doRetrieveAll();
    ReceivingInstitute bean = list.get(list.size() - 1);

    bean.setWebsite("www.unirm.it");
    bean.setSizeOfEnterprise("grande");

    boolean ok = false;
    try {
      manager.doUpdate(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);
 }
}