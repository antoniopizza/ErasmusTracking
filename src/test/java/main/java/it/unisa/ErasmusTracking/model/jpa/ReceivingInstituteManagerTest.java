package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.ReceivingInstitute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceivingInstituteManagerTest {

  private static ReceivingInstituteManager manager;
  private static ReceivingInstitute receivingInstitute;
  private static Integer id = 1;

  @BeforeAll
  static void setUp() throws SQLException {
    try {
      manager = new ReceivingInstituteManager("", "", "");
    } catch(Exception e) {
      e.printStackTrace();
    }finally {
      manager = new ReceivingInstituteManager("erasmusTracking","root","root");
    }

  }

  @Test
  void testDoSave() {
    System.out.println("doSave");

    receivingInstitute = new ReceivingInstitute();
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
  void testDoSave1() {
    System.out.println("doSave con campi nulli");

    receivingInstitute = new ReceivingInstitute();

    receivingInstitute.setLocalita(1);

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
    assertEquals(1, receivingInstitute.getId());
  }

  @Test
  void testDoRetrieveAll() {
    System.out.println("doRetrieveAll");

    receivingInstitute = new ReceivingInstitute();
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