package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import org.junit.jupiter.api.BeforeAll;
 import org.junit.jupiter.api.Test;


import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class AccountManagerTest{
  private static AccountManager classUnderTest;
  private static Integer id;
  private static Account bean;

  @BeforeAll
  static void setUp() throws SQLException {
    try {
      classUnderTest = new AccountManager("", "", "");
    } catch(Exception e) {
      e.printStackTrace();
    }finally {
      classUnderTest = new AccountManager("erasmusTracking","root","root");
      id = 0;
    }

  }

  @Test
  synchronized void testDoSave() throws SQLException{
    System.out.println("doSave");
    bean = new Account();
    bean.setId(12);
    bean.setNome("Filomena");
    bean.setCognome("Ferrucci");
    bean.setEmail("ferrucci@unisa.it");
    bean.setRuolo("amministratore");
    bean.setPassword("adelaide");
    boolean ok = false;
    try{
      classUnderTest.doSave(bean);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
    }

    ArrayList<Account>list = (ArrayList<Account>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    try {
      classUnderTest.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  synchronized void testDoDelete() throws SQLException {
    System.out.println("doSave");
    bean = new Account();

    boolean ok = false;

    bean.setNome("Fabrizio");
    bean.setCognome("Bellucci");
    bean.setEmail("bellucci92@hotmail.it");
    bean.setPassword("adenoidi");
    bean.setRuolo("studente");
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }

    assertTrue(ok);

    ArrayList<Account> list = (ArrayList<Account>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    bean.setId(list.get(list.size()-1).getId());
    assertNotEquals(0,list.size());
    System.out.println(list.get(list.size()-1).getId());
    try {
      classUnderTest.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }

  }

  @Test
  synchronized void testDoRetrieveById() throws SQLException {
    System.out.println("doRetrieveById");
    bean = classUnderTest.doRetrieveById(id);
    assertEquals(0, bean.getId());
  }

  @Test
  synchronized void testDoRetrieveAll() {
    System.out.println("doRetrieveByAll");
    List<Account> ris = classUnderTest.doRetrieveAll();
    assertEquals(ris, classUnderTest.doRetrieveAll());
  }

  @Test
  synchronized void testDoRetrieveByEmail() {
    System.out.println("doRetrieveByEmail");
    bean = new Account();
    bean.setId(classUnderTest.doRetrieveByEmail("bellucci92@hotmail.it").getId());
    bean.setNome("Fabrizio");
    bean.setCognome("Bellucci");
    bean.setEmail("bellucci92@hotmail.it");
    bean.setPassword("adenoidi");
    bean.setRuolo("studente");

    Account res = classUnderTest.doRetrieveByEmail(bean.getEmail());
    try{
      assertEquals(res.getId(),bean.getId());

    }catch (Exception e){
      e.printStackTrace();
    }

    ArrayList<Account> list = (ArrayList<Account>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    try {
      classUnderTest.doDelete(bean.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  synchronized void testDoUpdate() throws SQLException {
    System.out.println("doUpdate");
    bean = new Account();

    bean.setNome("Fabrizio");
    bean.setCognome("Bellucci");
    bean.setEmail("bellucci92@hotmail.it");
    bean.setPassword("adenoidi");
    bean.setRuolo("studente");

    System.out.println(bean.toString());

    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }

    assertTrue(ok);

    ArrayList<Account> list = (ArrayList<Account>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);

    System.out.println(bean.toString());

    bean.setNome("Dario");
    bean.setCognome("Scola");
    bean.setPassword("ogliarulo");

    System.out.println(bean.toString());
    try {
      classUnderTest.doUpdate(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }


    try {
      classUnderTest.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }

    assertTrue(ok);
  }

}