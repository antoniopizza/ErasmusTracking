package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Amministratore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class AmministratoriManagerTest {
  private static AmministratoriManager classUnderTest;
  private static AccountManager m;
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
      m =new AccountManager("erasmusTracking","root","root");
      classUnderTest = new AmministratoriManager("erasmusTracking", "root", "root");
      id = 0;
    }
  }

  @Test
  synchronized void testDoSave() {
    System.out.println("doSave");
    bean = new Amministratore();

    bean.setId(m.doRetrieveByEmail("chardido@gmail.com").getId());
    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    assertNotEquals(0,list.size());

    try {
      classUnderTest.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }



  }

  @Test
  synchronized void testDoDelete() throws SQLException{
    System.out.println("doDelete");
    bean = new Amministratore();
    boolean ok = false;

    bean.setId(m.doRetrieveByEmail("chardido@gmail.com").getId());


    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }

    assertTrue(ok);

    ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    assertNotEquals(0,list.size());

    try {
      classUnderTest.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

    //bean null;
    bean = new Amministratore();
    try {
      classUnderTest.doDelete(bean.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  @Test
  synchronized void testDoRetrieveById() {
    System.out.println("doRetrieveById");


    bean = new Amministratore();
    bean.setId(m.doRetrieveById(3).getId());

    boolean ok = false;
    try{
      classUnderTest.doSave(bean);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
    }

    Amministratore ris = classUnderTest.doRetrieveById(bean.getId());
    assertEquals(3, bean.getId());

    try{
      classUnderTest.doDelete(bean.getId());
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
    }

  }

  @Test
  synchronized void testDoRetrieveAll() {
    System.out.println("doRetrieveAll");
    bean = new Amministratore();

    boolean ok = false;
    bean.setId(3);
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

    bean = new Amministratore();
    boolean ok = false;

    bean.setId(m.doRetrieveByEmail("chardido@gmail.com").getId());

    try{
      classUnderTest.doSave(bean);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }

    Amministratore ris = classUnderTest.doRetrieveByEmail("chardido@gmail.com");
    assertEquals(ris.getId(),bean.getId());

    ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    assertNotEquals(0,list.size());

    try {
      classUnderTest.doDelete(bean.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }



  }

  @Test
  synchronized void testDoUpdate() throws SQLException{
    System.out.println("doUpdate");
    bean = new Amministratore();
    bean.setId(m.doRetrieveByEmail("chardido@gmail.com").getId());

    Amministratore ris =bean;

    boolean ok = false;
    try{
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e){
      ok=false;
      e.printStackTrace();
    }

    ArrayList<Amministratore> list = (ArrayList<Amministratore>)classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    assertNotEquals(0,list.size());

    bean.setId(9);

    try{
      classUnderTest.doUpdate(bean);
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
    }

    assertTrue(ok);
    assertFalse(ris.equals(bean));

    bean = list.get(list.size()-1);

    try{
      classUnderTest.doDelete(bean.getId());
      ok = true;
    } catch (Exception e){
      ok=false;
      e.printStackTrace();
    }

    assertTrue(ok);

    //bean null
    bean = new Amministratore();
    try {
      classUnderTest.doUpdate(bean);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}