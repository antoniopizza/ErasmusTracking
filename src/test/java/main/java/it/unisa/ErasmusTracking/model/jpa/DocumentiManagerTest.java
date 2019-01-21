package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentiManagerTest {
    private static DocumentiManager documentiManager = new DocumentiManager("erasmusTracking", "root", "root");
    private static Documenti documento;
    private static AccountManager driverclassTest = new AccountManager("erasmusTracking", "root", "root");
    private static Account beanaccount;
    @Test
    void doSave() {
        System.out.println("doSave");

        beanaccount = new Account();

        beanaccount.setEmail("darioscola015@gmail.com");
        beanaccount.setNome("Dario");
        beanaccount.setCognome("Scola");
        beanaccount.setPassword("scemoscemo");
        beanaccount.setRuolo("Studente");

        boolean ok = false;
        try {
            driverclassTest.doSave(beanaccount);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);

        List<Account> list = driverclassTest.doRetrieveAll();
        beanaccount = list.get(list.size()-1);

        documento = new Documenti();
        documento.setUrl("www.pizza.it/html.pdf");
        documento.setDataCaricamento("Roma");
        documento.setProprietario(beanaccount.getId());
        documento.setNome("Test");

        ok = false;
        try{
            documentiManager.doSave(documento);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);

        List<Documenti> list1 = documentiManager.doRetrieveAll();
        documento = list1.get(list1.size()-1);

        ok = false;
        try{
            documentiManager.doDelete(documento.getId());
            driverclassTest.doDelete(beanaccount.getId());
            ok = true;
        }catch (Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }


    @Test
    void doDelete() {
        System.out.println("doDelete");

        beanaccount = new Account();

        beanaccount.setEmail("darioscola015@gmail.com");
        beanaccount.setNome("Dario");
        beanaccount.setCognome("Scola");
        beanaccount.setPassword("scemoscemo");
        beanaccount.setRuolo("Studente");

        boolean ok = false;
        try {
            driverclassTest.doSave(beanaccount);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);

        List<Account> list = driverclassTest.doRetrieveAll();
        beanaccount = list.get(list.size()-1);

        documento = new Documenti();
        documento.setUrl("www.pizza.it/html.pdf");
        documento.setDataCaricamento("25/10/2018");
        documento.setProprietario(beanaccount.getId());
        documento.setNome("Test");

        ok = false;
        try{
            documentiManager.doSave(documento);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);

        List<Documenti> list1 = documentiManager.doRetrieveAll();
        documento = list1.get(list1.size()-1);

        ok = false;
        try{
            documentiManager.doDelete(documento.getId());
            driverclassTest.doDelete(beanaccount.getId());
            ok = true;
        }catch (Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);

        //campi entrambe vuoti
        beanaccount = new Account();
        documento = new Documenti();
        ok = false;
        try{
            documentiManager.doDelete(documento.getId());
            driverclassTest.doDelete(beanaccount.getId());
            ok = true;
        }catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }


    }

    @Test
    void doRetrieveById() {

        System.out.println("doRetrieveById");

        beanaccount = new Account();

        beanaccount.setEmail("darioscola015@gmail.com");
        beanaccount.setNome("Dario");
        beanaccount.setCognome("Scola");
        beanaccount.setPassword("scemoscemo");
        beanaccount.setRuolo("Studente");

        boolean ok = false;
        try {
            driverclassTest.doSave(beanaccount);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);

        List<Account> list = driverclassTest.doRetrieveAll();
        beanaccount = list.get(list.size()-1);

        documento = new Documenti();
        documento.setUrl("www.didomenico.it/html.pdf");
        documento.setDataCaricamento("25/10/2018");
        documento.setProprietario(beanaccount.getId());
        documento.setNome("Test");

        ok = false;
        try{
            documentiManager.doSave(documento);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);

        List<Documenti> list1 = documentiManager.doRetrieveAll();
        documento = list1.get(list1.size()-1);

        Documenti ris = documentiManager.doRetrieveById(documento.getId());
        assertEquals(ris.getId(),documento.getId());

        ok = false;
        try{
            documentiManager.doDelete(documento.getId());
            driverclassTest.doDelete(beanaccount.getId());
            ok = true;
        }catch (Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);

        //campi entrambe vuoti
        beanaccount = new Account();
        documento = new Documenti();
        ok = false;
        try{
            documentiManager.doDelete(documento.getId());
            driverclassTest.doDelete(beanaccount.getId());
            ok = true;
        }catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
    }


    @Test
    void doRetrieveAll() {
      System.out.println("doRetrieveAll");

      beanaccount = new Account();

      beanaccount.setEmail("darioscola015@gmail.com");
      beanaccount.setNome("Dario");
      beanaccount.setCognome("Scola");
      beanaccount.setPassword("scemoscemo");
      beanaccount.setRuolo("Studente");

      boolean ok = false;
      try {
        driverclassTest.doSave(beanaccount);
        ok = true;
      }catch(Exception e){
        e.printStackTrace();
        ok = false;
      }

      assertTrue(ok);

      List<Account> list = driverclassTest.doRetrieveAll();
      beanaccount = list.get(list.size()-1);

      documento = new Documenti();
      documento.setUrl("www.didomenico.it/html.pdf");
      documento.setDataCaricamento("25/10/2018");
      documento.setProprietario(beanaccount.getId());
      documento.setNome("Test");

      ok = false;
      try{
        documentiManager.doSave(documento);
        ok = true;
      }catch(Exception e){
        e.printStackTrace();
        ok = false;
      }

      assertTrue(ok);

      List<Documenti> list1 = documentiManager.doRetrieveAll();
      documento = list1.get(list1.size()-1);
      assertNotEquals(0,list1.size());

      Documenti ris = documentiManager.doRetrieveById(documento.getId());

      ok = false;
      try{
        documentiManager.doDelete(documento.getId());
        driverclassTest.doDelete(beanaccount.getId());
        ok = true;
      }catch (Exception e){
        e.printStackTrace();
        ok = false;
      }

      assertTrue(ok);

    }
/** MODIFICARE IN ID ACCOUNT L'INTERNO */
    @Test
    void doRetrieveByIdAccount() {
        System.out.println("doRetrieveByIdAccount");

      beanaccount = new Account();

      beanaccount.setEmail("darioscola015@gmail.com");
      beanaccount.setNome("Dario");
      beanaccount.setCognome("Scola");
      beanaccount.setPassword("scemoscemo");
      beanaccount.setRuolo("Studente");

      boolean ok = false;
      try {
        driverclassTest.doSave(beanaccount);
        ok = true;
      }catch(Exception e){
        e.printStackTrace();
        ok = false;
      }

      assertTrue(ok);

      List<Account> list = driverclassTest.doRetrieveAll();
      beanaccount = list.get(list.size()-1);

      documento = new Documenti();
      documento.setUrl("www.didomenico.it/html.pdf");
      documento.setDataCaricamento("25/10/2018");
      documento.setProprietario(beanaccount.getId());
      documento.setNome("Test");

      ok = false;
      try{
        documentiManager.doSave(documento);
        ok = true;
      }catch(Exception e){
        e.printStackTrace();
        ok = false;
      }

      assertTrue(ok);

      List<Documenti> list1 = documentiManager.doRetrieveAll();
      documento = list1.get(list1.size()-1);
      assertNotEquals(0,list1.size());

      List<Documenti> ris = documentiManager.doRetrieveByIdAccount(beanaccount.getId());
      assertNotEquals(0,ris.size());

      ok = false;
      try{
        documentiManager.doDelete(documento.getId());
        driverclassTest.doDelete(beanaccount.getId());
        ok = true;
      }catch (Exception e){
        e.printStackTrace();
        ok = false;
      }

      assertTrue(ok);
    }


    @Test
    void doRetrieveByUsernameStudent() {
      System.out.println("doRetrieveByNomeStudente");

      beanaccount = new Account();

      beanaccount.setEmail("darioscola015@gmail.com");
      beanaccount.setNome("Dario");
      beanaccount.setCognome("Scola");
      beanaccount.setPassword("scemoscemo");
      beanaccount.setRuolo("Studente");

      boolean ok = false;
      try {
        driverclassTest.doSave(beanaccount);
        ok = true;
      } catch (Exception e) {
        e.printStackTrace();
        ok = false;
      }

      assertTrue(ok);

      List<Account> list = driverclassTest.doRetrieveAll();
      beanaccount = list.get(list.size() - 1);

      documento = new Documenti();
      documento.setUrl("www.didomenico.it/html.pdf");
      documento.setDataCaricamento("25/10/2018");
      documento.setProprietario(beanaccount.getId());
      documento.setNome("Test");

      ok = false;
      try {
        documentiManager.doSave(documento);
        ok = true;
      } catch (Exception e) {
        e.printStackTrace();
        ok = false;
      }

      assertTrue(ok);

      List<Documenti> list1 = documentiManager.doRetrieveAll();
      documento = list1.get(list1.size() - 1);
      assertNotEquals(0, list1.size());

      List<Documenti> ris = documentiManager.doRetrieveByUsernameStudent(driverclassTest.doRetrieveById(beanaccount.getId()).getEmail());
      assertNotEquals(0,ris.size());

      ok = false;
      try {
        documentiManager.doDelete(documento.getId());
        driverclassTest.doDelete(beanaccount.getId());
        ok = true;
      } catch (Exception e) {
        e.printStackTrace();
        ok = false;
      }

      assertTrue(ok);
    }
}

