package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.bean.Localita;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CoordinatoriManagerTest {
  private static CoordinatoriManager classUnderTest;
  private static StudenteManager studenteManager;
  private static Integer id;
  private static AccountManager m;
  private static Coordinatore bean;
  private static Studente studente;
  private static Localita localita;


  @BeforeAll
  static void setUp() throws Exception {
    classUnderTest = new CoordinatoriManager("erasmustracking","root","root");
    studenteManager = new StudenteManager("erasmustracking","root","root");
    m =new AccountManager("erasmusTracking","root","root");
    id = 5;
  }

  @Test
  void testDoSave() {
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
    assertNotEquals(0, list.size());

    Integer id = 0;
    for (Coordinatore bean : list) {
      id++;
    }
    System.out.println(id);
    classUnderTest.doDelete(id);

  }

  @Test
  void testDoDelete() {
    System.out.println("delete");

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(1);

    classUnderTest.doSave(bean);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, list.size());

    Integer id = 0;
    for (Coordinatore bean : list) {
      id++;
    }
    System.out.println(id);
    boolean ok = false;
    try {
      classUnderTest.doDelete(id);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }

    assertTrue(ok);
  }

  @Test
  void testDoRetrieveById() {
    System.out.println("doRetrieveById");
    bean = classUnderTest.doRetrieveById(id);
    assertEquals(0, bean.getId());
  }

  @Test
  void testDoRetrieveAll() {
    System.out.println("doRetrieveAll");

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(1);

    classUnderTest.doSave(bean);

    List<Coordinatore> list = classUnderTest.doRetrieveAll();
    assertNotEquals(0,list.size());

    classUnderTest.doDelete(list.size()-1);
  }

  @Test
  void testDoRetrieveByEmail() {
    System.out.println("doRetrieveByEmail");

    bean = new Coordinatore();
    boolean ok = false;

    bean.setId(m.doRetrieveByEmail("a.rigido1@studenti.unisa.it").getId());

    try{
      classUnderTest.doSave(bean);
      ok = true;
    }catch(Exception e){
      e.printStackTrace();
      ok = false;
    }

    Coordinatore ris = classUnderTest.doRetrieveByEmail("a.rigido1@studenti.unisa.it");
    assertEquals(ris.getId(),bean.getId());

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    bean = list.get(list.size()-1);
    assertNotEquals(0,list.size());

    try {
      classUnderTest.doDelete(bean.getId());
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  @Test
  void testDoUpdate() {
    System.out.println("doUpdate");

    bean = new Coordinatore();
    bean.setNome("Alessandro");
    bean.setCognome("Rigido");
    bean.setPassword("root");
    bean.setEmail("a.rigido1@studenti.unisa.it");
    bean.setRuolo("coordinatore");
    bean.setSendingInstitute(1);

    classUnderTest.doSave(bean);

    ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    Coordinatore bean = list.get(list.size() - 1);

    bean.setCognome("RIjf");
    bean.setNome("Paolo");

    boolean ok = false;
    try {
      classUnderTest.doUpdate(bean);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);
  }
}