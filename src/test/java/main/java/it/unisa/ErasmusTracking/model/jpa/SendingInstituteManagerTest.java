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

    List<SendingInstitute>  list = manager.doRetrieveAll();
    SendingInstitute bean = list.get(list.size() - 1);
    try{
      manager.doDelete(bean.getId());
    }catch(Exception e){
      e.printStackTrace();
    }

  }

  @Test
  void testDoDelete() {
    System.out.println("doDelete");

    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try{
      manager.doSave(sendingInstitute);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

    List<SendingInstitute> list = (ArrayList<SendingInstitute>) manager.doRetrieveAll();
    SendingInstitute bean = list.get(list.size() - 1);

    ok = false;
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

    boolean ok = false;
    try{
      manager.doSave(sendingInstitute);
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

    List<SendingInstitute> list = (ArrayList<SendingInstitute>) manager.doRetrieveAll();
    assertNotEquals(0, list.size());

    ok = false;
    sendingInstitute = list.get(list.size() - 1);
    try{
      manager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }

  }

  @Test
  void testDoRetrieveById() {
    sendingInstitute = new SendingInstitute();
    sendingInstitute.setCodiceErasmus("15478");
    sendingInstitute.setDipartimento("informatica");
    sendingInstitute.setIndirizzo("fisciano");

    boolean ok = false;
    try{
      manager.doSave(sendingInstitute);
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

    List<SendingInstitute> list = manager.doRetrieveAll();
    assertNotEquals(0, list.size());
    sendingInstitute = list.get(list.size() - 1);

    SendingInstitute ris = manager.doRetrieveById(sendingInstitute.getId());
    assertNotEquals(null,ris);

    ok = false;
    try{
      manager.doDelete(sendingInstitute.getId());
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }
  }
}