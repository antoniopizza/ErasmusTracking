package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudenteManagerTest {
    private static StudenteManager classUnderTest;
    private static CoordinatoriManager coordinatoreManager;
    private static Integer id;
    private static Studente bean;

    @BeforeAll
    static void setUp() throws Exception {
        classUnderTest = new StudenteManager("erasmustracking","root","root");
        coordinatoreManager = new CoordinatoriManager("erasmustracking","root","root");
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
        System.out.println("delete");

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

        classUnderTest.doSave(bean);

        ArrayList<Studente> list = (ArrayList<Studente>) classUnderTest.doRetrieveAll();
        assertNotEquals(0, list.size());

        Integer id = 0;
        for (Studente bean : list) {
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
    }

    @Test
    void doRetrieveAll() {
        System.out.println("doRetrieveAll");

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

        classUnderTest.doSave(bean);

        List<Studente> list = classUnderTest.doRetrieveAll();
        assertNotEquals(0,list.size());

        classUnderTest.doDelete(list.size()-1);
    }

    @Test
    void doRetrieveById() {
        System.out.println("doRetrieveById");
        bean = classUnderTest.doRetrieveById(id);
        assertEquals(0, bean.getId());
    }

    @Test
    void doRetrieveByIdCoordinatore() {

        Coordinatore coordinatore = new Coordinatore();
        coordinatore.setNome("Alessandro");
        coordinatore.setCognome("Rigido");
        coordinatore.setPassword("root");
        coordinatore.setEmail("a.rigido1@studenti.unisa.it");
        coordinatore.setRuolo("coordinatore");
        coordinatore.setSendingInstitute(1);

        boolean ok = false;
        try {
            coordinatoreManager.doSave(coordinatore);
            ok =true;
        } catch (Exception e) {
            ok=false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Coordinatore> list = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
        assertNotEquals(0, list.size());

        coordinatore = list.get(list.size()-1);


        System.out.println("doRetrieveByIdCoordninatore");

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
        bean.setIdCoordinatore(coordinatore.getId());

      classUnderTest.doSave(bean);

       List<Studente> list1 = (List<Studente>) classUnderTest.doRetrieveByCoordinatore(bean.getIdCoordinatore());
        Iterator<Studente> i = list1.iterator();

        ok = true;
        while (i.hasNext()) {
            Studente bean = i.next();
            if (bean.getIdCoordinatore() != coordinatore.getId()) {
                ok = false;
            }
        }
        assertTrue(ok);


    }

    @Test
    void doRetrieveByMatricola() {
    }

    @Test
    void doRetrieveByEmail() {
    }
}