package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Amministratore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


class AmministratoriManagerTest {
  private static AmministratoriManager classUnderTest,x;
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

    bean.setNome("Filomena");
    bean.setCognome("Ferrucci");
    bean.setEmail("ferrucci@unisa.it");
    bean.setRuolo("amministratore");
    bean.setPassword("adelaide");
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
      m.doDelete(bean.getId());
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

    bean.setNome("Filomena");
    bean.setCognome("Ferrucci");
    bean.setEmail("ferrucci@unisa.it");
    bean.setRuolo("amministratore");
    bean.setPassword("adelaide");

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
      m.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

    //bean vuoto
    bean = new Amministratore();
    try{
      classUnderTest.doDelete(bean.getId());

    }catch(Exception e){
      e.printStackTrace();
    }
  }

  @Test
  synchronized void testDoRetrieveById() {
    System.out.println("doRetrieveById");

    bean = new Amministratore();
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


    ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0,list.size());
    bean = list.get(list.size()-1);

    Amministratore ris = classUnderTest.doRetrieveById(bean.getId());
    assertEquals(ris.getId(),bean.getId());


    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
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
    bean.setNome("Filomena");
    bean.setCognome("Ferrucci");
    bean.setEmail("ferrucci@unisa.it");
    bean.setRuolo("amministratore");
    bean.setPassword("adelaide");
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
      m.doDelete(bean.getId());

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

    bean.setNome("Filomena");
    bean.setCognome("Ferrucci");
    bean.setEmail("ferrucci@unisa.it");
    bean.setRuolo("amministratore");
    bean.setPassword("adelaide");
    try{
      classUnderTest.doSave(bean);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }

    Amministratore ris;
    try{
      ris=classUnderTest.doRetrieveByEmail("ferrucci@unisa.it");
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
    }
    assertTrue(ok);

    ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    assertNotEquals(0,list.size());

    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  synchronized void testDoUpdate() throws SQLException{
    System.out.println("doUpdate");

    bean = new Amministratore();
    bean.setNome("Filomena");
    bean.setCognome("Ferrucci");
    bean.setEmail("ferrucci@unisa.it");
    bean.setRuolo("amministratore");
    bean.setPassword("adelaide");
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

    bean.setNome("Filomena");
    bean.setCognome("Ferrucci");
    bean.setEmail("ferrucci@unisa.it");
    bean.setRuolo("amministratore");
    bean.setPassword("adelaide");
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
      m.doDelete(bean.getId());
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