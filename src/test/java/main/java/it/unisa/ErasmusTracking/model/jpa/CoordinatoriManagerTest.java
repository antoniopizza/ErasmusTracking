package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class CoordinatoriManagerTest {
  private static CoordinatoriManager classUnderTest;
  private static Integer id;
  private static Coordinatore bean;

  @BeforeAll
  static void setUp() throws Exception {
    classUnderTest = new CoordinatoriManager("erasmustracking","root","root");
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
    bean.setNome("Fabrizio");
    bean.setCognome("Bellucci");
    bean.setEmail("bellucci92@hotmail.it");
    bean.setPassword("adenoidi");
    bean.setRuolo("");

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
  }

  @Test
  void testDoRetrieveByIdAccount() {
  }

  @Test
  void testDoRetrieveAll() {
  }

  @Test
  void testDoRetrieveByEmail() {
  }
}