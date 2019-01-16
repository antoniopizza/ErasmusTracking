package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Amministratore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AmministratoriManagerTest {
  private static AmministratoriManager classUnderTest;
  private static Integer id;
  private static Amministratore bean;

  @BeforeAll
  static void setUp() throws SQLException {
    try{
      AmministratoriManager x = new AmministratoriManager("","","");
    }catch(Exception e){
      e.printStackTrace();
    }finally {
      System.out.println("Initialising");
      classUnderTest = new AmministratoriManager("erasmusTracking", "root", "root");
      id = 0;
    }
  }

  @Test
  synchronized void testDoSave() {
    System.out.println("doSave");
    bean = new Amministratore();

    boolean ok = false;
    bean.setId(12);
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    try {
      classUnderTest.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }



  }

  @Test
  synchronized void doDelete() throws SQLException{
    System.out.println("doSave");
    bean = new Amministratore();
    boolean ok = false;

    bean.setId(13);

    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }

    assertTrue(ok);

    ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    try {
      classUnderTest.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }



  }

  @Test
  synchronized void doRetrieveById() {
    System.out.println("doRetrieveById");

    bean = new Amministratore();

    bean.setId(12);

    classUnderTest.doSave(bean);

    Account ris = (Account)classUnderTest.doRetrieveById(bean.getId());
    assertEquals(ris.getId(), bean.getId());

  }

  @Test
  synchronized void doRetrieveAll() {
    System.out.println("doRetrieveAll");
    bean = new Amministratore();

    boolean ok = false;
    bean.setId(13);
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0,list.size());

    bean = list.get(list.size()-1);
    try {
      classUnderTest.doDelete(bean.getId());

      ok = true;
    } catch (Exception e) {
      ok = false;
    }



  }


  @Test
  synchronized void testDoRetrieveByEmail(){
    System.out.println("doRetrieveByEmail");
    AccountManager x = new AccountManager("erasmusTracking","root","root");
    Account m = new Account();
    m = x.doRetrieveByEmail("ferrucci@unisa.it");

    bean = new Amministratore();

    bean.setId(m.getId());
    try{
      classUnderTest.doSave(bean);
    }catch (Exception e){
      e.printStackTrace();
    }

    Amministratore ris = bean;
    try{
      assertEquals(ris.getId(),bean.getId());

    }catch (Exception e){
      e.printStackTrace();
    }

    ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    try {
      classUnderTest.doDelete(bean.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  synchronized void testDoUpdate() throws SQLException{
    System.out.println("doUpdate");
    AccountManager x = new AccountManager("erasmusTracking","root","root");
    Account m = new Account();
    m = x.doRetrieveByEmail("ferrucci@unisa.it");
    bean = new Amministratore();
    bean.setId(m.getId());

    boolean ok = false;
    try{
      classUnderTest.doUpdate(bean);
      ok = true;
    } catch (Exception e){
      ok=false;
      e.printStackTrace();
    }finally{
      classUnderTest.doDelete(bean.getId());
      ok = true;
    }



    assertTrue(ok);


    try{
      classUnderTest.doDelete(bean.getId());
      ok = true;
    } catch (Exception e){
      ok=false;
      e.printStackTrace();
    }

    assertTrue(ok);
  }
}