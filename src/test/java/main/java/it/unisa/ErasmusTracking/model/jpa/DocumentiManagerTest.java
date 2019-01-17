package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DocumentiManagerTest {
    private static DocumentiManager documentiManager = new DocumentiManager("erasmustracking", "root", "root1234");;
    private static Documenti documento;
    private static Integer id=123;
    @Test
    void doSave() {
        System.out.println("doSave");

        documento = new Documenti();
        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(id);
        documento.setUrl("www.iloveu.it");

        boolean ok = false;
        try {
            documentiManager.doSave(documento);
            ok = true;
        } catch (Exception e) {
            ok = false;
        }
        assertTrue(ok);

        List<Documenti> list = documentiManager.doRetrieveAll();

        documentiManager.doDelete(list.size() - 1);
    }

    @Test
    void doDelete() {
        System.out.println("doDelete");

        documento = new Documenti();

        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(765);
        documento.setUrl("www.iloveu.it");

        documentiManager.doSave(documento);

        List<Documenti> list = documentiManager.doRetrieveAll();

        boolean ok = false;
        try {
            documentiManager.doDelete(list.size() - 1);
            ok = true;
        } catch (Exception e) {
            ok = false;
        }
        assertTrue(ok);

    }

    @Test
    void doRetrieveById() {
        documento = new Documenti();


        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(765);
        documento.setUrl("www.iloveu.it");

        System.out.println("doRetrieveById");
        documento = documentiManager.doRetrieveById(id);
        assertEquals(0, documento.getId());
    }


    @Test
    void doRetrieveAll() {
        System.out.println("doRetrieveAll");


        documento = new Documenti();

        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(765);
        documento.setUrl("www.iloveu.it");

        documentiManager.doSave(documento);

        List<Documenti> list = documentiManager.doRetrieveAll();
        assertNotEquals(0, list.size());

        documentiManager.doDelete(list.size() - 1);
    }
/** MODIFICARE IN ID ACCOUNT L'INTERNO */
    @Test
    void doRetrieveByIdAccount() {
        System.out.println("doRetrieveByIdAccount");
        documento = new Documenti();


        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(765);
        documento.setUrl("www.iloveu.it");

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
    }


    @Test
    void doRetrieveByUsernameStudent() {
        System.out.println("doRetrieveByNomeStudente");

        documento = new Documenti();
        documento.setDataCaricamento("Roma");
        documento.setProprietario(1);
        documento.setNome("Test");
        documento.setId(id);
        documento.setUrl("www.iloveu.it");

        documentiManager.doSave(documento);

        List<Documenti> list = documentiManager.doRetrieveByUsernameStudent(documento.getNome());
    Iterator<Documenti> i = list.iterator();
    boolean ok = true;
    while (i.hasNext()) {
    Documenti bean = i.next();
    if (bean.getProprietario() != 123) {
    ok = false;
    }
    }
    assertTrue(ok);
    }

    }