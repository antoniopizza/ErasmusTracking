package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class StudenteManagerTest {
    private static StudenteManager classUnderTest;
    private static Integer id;
    private static Studente bean;

    @BeforeAll
    static void setUp() throws Exception {
        classUnderTest = new StudenteManager("erasmustracking","root","root");
        id = 5;
    }

    @Test
    void doSave() {
        bean = new Studente();
        bean.setAnnoAccademico(1);
        bean.setDataDiNascita("12/12/2018");
        bean.setLuogoDiNascita("Caserta");
        bean.setTelefono("3012322297");
        bean.setCicloDiStudi("1-triennale");
        bean.setNazionalita("Italia");
        bean.setSesso("M");
        bean.setEmail("aleoale@live.it");
        bean.setCognome("Poldo");
        bean.setMatricola("0215456332");
        bean.setNome("alessandro");
        bean.setPassword("root");
        bean.setRuolo("studente");
        bean.setIdCoordinatore(1);
        boolean ok = false;
        try {
            classUnderTest.doSave(bean);
            ok = true;
        } catch (Exception e) {
            ok=false;
            e.printStackTrace();
        }

        assertTrue(ok);

        ArrayList<Studente> list = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
        assertNotEquals(0, list.size());

        Integer id = 0;
        for (Studente bean : list) {
            id++;
        }
        System.out.println(id);
        classUnderTest.doDelete(id);



    }

    @Test
    void doDelete() {
    }

    @Test
    void doRetrieveAll() {
    }

    @Test
    void doRetrieveById() {
    }

    @Test
    void doRetrieveByIdStudente() {
    }

    @Test
    void doRetrieveByMatricola() {
    }

    @Test
    void doRetrieveByEmail() {
    }
}