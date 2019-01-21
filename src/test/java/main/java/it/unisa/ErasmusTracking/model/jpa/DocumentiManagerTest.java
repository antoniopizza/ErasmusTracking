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
    private static DocumentiManager documentiManager = new DocumentiManager("erasmustracking", "root", "root1234");
    private static Documenti documento;
    private static AccountManager driverclassTest = new AccountManager("erasmustracking", "root", "root1234");
    private static Account beanaccount;
    private static Integer id=123;
    @Test
    void doSave() {
        System.out.println("doSave");

        beanaccount = new Account();

        beanaccount.setEmail("darioscola015@gmail.com");
        beanaccount.setNome("Dario");
        beanaccount.setCognome("Scola");
        beanaccount.setPassword("scemoscemo");
        beanaccount.setRuolo("Studente");
        driverclassTest.doSave(beanaccount);
        documento = new Documenti();
        InputStream x = new ByteArrayInputStream(new String("1234567890").getBytes());
        documento.setInputStream(x);
        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(id);


        boolean ok = false;
        try {
            documentiManager.doSave(documento);
            ok = true;
        } catch (Exception e){
            ok = false;
        }
        assertTrue(ok);

        List<Documenti> list = documentiManager.doRetrieveAll();

        documentiManager.doDelete(documento.getId());

        driverclassTest.doDelete(beanaccount.getId());
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
        InputStream x = new ByteArrayInputStream(new String("1234567890").getBytes());
        documento.setInputStream(x);
        driverclassTest.doSave(beanaccount);
        documento = new Documenti();

        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(765);



        documentiManager.doSave(documento);

        List<Documenti> list = documentiManager.doRetrieveAll();

        boolean ok = false;
        try {
            documentiManager.doDelete(documento.getId());
            ok = true;
        } catch (Exception e) {
            ok = false;
        }

        assertTrue(ok);
        driverclassTest.doDelete(beanaccount.getId());
    }

    @Test
    void doRetrieveById() {
        beanaccount = new Account();

        beanaccount.setEmail("darioscola015@gmail.com");
        beanaccount.setNome("Dario");
        beanaccount.setCognome("Scola");
        beanaccount.setPassword("scemoscemo");
        beanaccount.setRuolo("Studente");
        InputStream x = new ByteArrayInputStream(new String("1234567890").getBytes());
        documento.setInputStream(x);
        driverclassTest.doSave(beanaccount);
        documento = new Documenti();


        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(765);


        System.out.println("doRetrieveById");
        documento = documentiManager.doRetrieveById(id);
        assertEquals(0, documento.getId());
        driverclassTest.doDelete(beanaccount.getId());
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
        InputStream x = new ByteArrayInputStream(new String("1234567890").getBytes());
        documento.setInputStream(x);
        driverclassTest.doSave(beanaccount);


        documento = new Documenti();

        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(765);


        documentiManager.doSave(documento);

        List<Documenti> list = documentiManager.doRetrieveAll();
        assertNotEquals(0,list.size());

        documentiManager.doDelete(list.size() - 1);
        driverclassTest.doDelete(beanaccount.getId());
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
        InputStream x = new ByteArrayInputStream(new String("1234567890").getBytes());
        documento.setInputStream(x);
        driverclassTest.doSave(beanaccount);

        documento = new Documenti();


        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");



      documentiManager.doSave(documento);


        List<Documenti> list = documentiManager.doRetrieveByIdAccount(documento.getId());
        Iterator<Documenti> i = list.iterator();
        boolean ok = true;
            while (i.hasNext()) {
                Documenti bean = i.next();
            if (bean.getId() != 1) {
                    ok = false;
                }
            }
            assertTrue(ok);
        driverclassTest.doDelete(beanaccount.getId());
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
        InputStream x = new ByteArrayInputStream(new String("1234567890").getBytes());
        documento.setInputStream(x);

        driverclassTest.doSave(beanaccount);


        documento = new Documenti();
        InputStream inputStream = null;

        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(id);
        documento.setInputStream(inputStream);


        documentiManager.doSave(documento);

        List<Documenti> list = documentiManager.doRetrieveByUsernameStudent(beanaccount.getEmail());
        Iterator<Documenti> i = list.iterator();
        boolean ok = true;
        while (i.hasNext()) {
            Documenti bean = i.next();
        if (documento.getId() != 1) {
                ok = false;
            }
        }
        assertTrue(ok);
        driverclassTest.doDelete(beanaccount.getId());
    }



}

