package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.bean.Localita;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CoordinatoriManagerTest {
  private static CoordinatoriManager classUnderTest;
  private static AccountManager m;
  private static Coordinatore bean;


  @BeforeAll
  static void setUp() throws SQLException {
    classUnderTest = new CoordinatoriManager("erasmustracking","root","root");
    m =new AccountManager("erasmusTracking","root","root");
  }

  @Test
  void testDoSave() throws SQLException{
    System.out.println("doSave");

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(1);

    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    assertTrue(ok);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    assertNotEquals(0, list.size());
    ok = false;


    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }

    assertTrue(ok);

  }

  @Test
  void testDoDelete() throws SQLException {
    System.out.println("doDelete");

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(1);

    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    assertNotEquals(0, list.size());


    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }

    assertTrue(ok);

    /**bean vuoto*/
    ok= false;
    bean = new Coordinatore();
    try{
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }
  }

  @Test
  void testDoRetrieveById() throws SQLException {
    System.out.println("doRetrieveById");

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(1);

    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    assertNotEquals(0, list.size());


    Coordinatore ris = new Coordinatore();
    ok = false;
    try{
      ris = classUnderTest.doRetrieveById(bean.getId());
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);




    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }

    assertTrue(ok);


  }

  @Test
  void testDoRetrieveAll() throws SQLException {
    System.out.println("doRetrieveAll");

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(1);

    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    try{

      assertEquals(2,list.size());

    }catch(Exception e){
      e.printStackTrace();
    }


    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }

    assertTrue(ok);

  }


  @Test
  void testDoRetrieveByEmail() throws SQLException {
    System.out.println("doRetrieveByEmail");

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(1);

    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok=false;
      e.printStackTrace();
    }

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    assertNotEquals(0, list.size());


    Coordinatore ris = new Coordinatore();
    ok = false;
    try{
      ris = classUnderTest.doRetrieveByEmail(m.doRetrieveById(bean.getId()).getEmail());

      System.out.println(ris);
      ok = true;
    }catch (Exception e){
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);

    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void testDoUpdate() throws SQLException {
    System.out.println("doRetrieveByEmail");

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(1);

    boolean ok = false;
    try {
      classUnderTest.doSave(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
      e.printStackTrace();
    }

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size() - 1);
    assertNotEquals(0, list.size());


    bean.setSendingInstitute(2);
    ok = false;
    try {
      classUnderTest.doUpdate(bean);
      System.out.println(bean);
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
    assertTrue(ok);

    ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      ok = true;
    } catch (Exception e) {
      ok = false;



    }
  }
}