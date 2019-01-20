package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Localita;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LocalitaManagerTest {

  private static LocalitaManager localitaManager = new LocalitaManager("erasmustracking", "root", "root1234");
  private static Localita localita;
  private static Integer id = 2;
  private static AccountManager accountManager = new AccountManager("erasmustracking", "root", "root1234");
  private static Account account;

  @Test
  void testDoSave() {
    System.out.println("doSave");

    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(1);

    boolean ok = false;
    try {
      localitaManager.doSave(localita);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);

    List<Localita> list = localitaManager.doRetrieveAll();

    localitaManager.doDelete(list.size() - 1);
  }

  @Test
  void testDoDelete() {
    System.out.println("doDelete");

    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(1);

    localitaManager.doSave(localita);

    List<Localita> list = localitaManager.doRetrieveAll();

    boolean ok = false;
    try {
      localitaManager.doDelete(list.size() - 1);
      ok = true;
    } catch (Exception e) {
      ok = false;
    }
    assertTrue(ok);
  }

  @Test
  void testDoRetrieveAll() {
    System.out.println("doRetrieveAll");

    localita = new Localita();
    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(1);

    localitaManager.doSave(localita);

    List<Localita> list = localitaManager.doRetrieveAll();
    assertNotEquals(0, list.size());

    localitaManager.doDelete(list.size() - 1);
  }


  @Test
  void testDoRetrieveByCity() {
    System.out.println("doRetrieveByIdCoordinatore");

    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(1);

    localitaManager.doSave(localita);

    List<Localita> list = localitaManager.doRetrieveByCity(localita.getCitta());
    Iterator<Localita> i = list.iterator();
    boolean ok = true;
    while (i.hasNext()) {
      Localita bean = i.next();
      if (!bean.getCitta().equals("Roma")) {
        ok = false;
      }
    }
    assertTrue(ok);
  }


  @Test
  void testDoRetrieveById() {
    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(1);
    System.out.println("doRetrieveById");
    localita = localitaManager.doRetrieveById(id);
    //assertEquals(2, localita.getId());
  }
    /**
     *  BY CODICE ERASMUS
     *  */

  @Test
  void testDoRetrieveByCodiceErasmus() {
    System.out.println("doRetrieveByCodiceErasmus");

    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(1);

    localitaManager.doSave(localita);

    List<Localita> list = localitaManager.doRetrieveByCodiceErasmus(localita.getCodiceErasmus());
    Iterator<Localita> i = list.iterator();
    boolean ok = true;
    while (i.hasNext()) {
      Localita bean = i.next();
      if (!bean.getCodiceErasmus().equals("aperto")) {
        ok = false;
      }
    }
    assertTrue(ok);
  }

  /** BY NOME*/

  @Test
  void testDoRetrieveByNome() {
    System.out.println("doRetrieveByNome");

    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
      localita.setCoordinatore(1);

    localitaManager.doSave(localita);

    List<Localita> list = localitaManager.doRetrieveByNome(localita.getNome());
    Iterator<Localita> i = list.iterator();
    boolean ok = true;
    while (i.hasNext()) {
      Localita bean = i.next();
      if (!bean.getNome().equals("prova")) {
        ok = false;
      }
    }
    assertTrue(ok);
  }

  @Test
  void testDoRetrieveByIdCoordinatore() {
    System.out.println("doRetrieveByIdCoordinatore");

    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(1);

    localitaManager.doSave(localita);

    List<Localita> list = localitaManager.doRetrieveByIdCoordinatore(localita.getCoordinatore());
    Iterator<Localita> i = list.iterator();
    boolean ok = true;
    while (i.hasNext()) {
      Localita bean = i.next();
      if (bean.getCoordinatore() != 1) {
        ok = false;
      }
    }
    assertTrue(ok);
  }

  @Test
  void testDoRetrieveByNation() {
    System.out.println("doRetrieveByNation");
    account = new Account();
    account.setRuolo("Coordinatore");
    account.setPassword("1234");
    account.setCognome("Scola");
    account.setNome("Dario");
    account.setEmail("darioscola015@gmail.com");

    accountManager.doSave(account);


    localita = new Localita();
    localita.setCitta("Roma");
    localita.setNazione("Italia");
    localita.setNome("prova");
    localita.setCodiceErasmus("aperto");
    localita.setCoordinatore(1);

    localitaManager.doSave(localita);

    List<Localita> list = localitaManager.doRetrieveByNation(localita.getNazione());
    Iterator<Localita> i = list.iterator();
    boolean ok = true;
    while (i.hasNext()) {
      Localita bean = i.next();
      if (!bean.getCodiceErasmus().equals("aperto")) {
        ok = false;
      }
    }
    assertTrue(ok);
    accountManager.doDelete(account.getId());
  }

}
