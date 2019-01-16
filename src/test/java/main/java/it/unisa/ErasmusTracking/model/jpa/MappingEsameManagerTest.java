package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.Esame;
import main.java.it.unisa.ErasmusTracking.bean.MappingEsame;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MappingEsameManagerTest {
    private static MappingEsameManager classUnderTest;
    private static Integer id;
    private static MappingEsame bean;
    @BeforeAll
    static void setUp() throws Exception {
        classUnderTest = new MappingEsameManager("erasmustracking",  "root", "root");
        id = 5;
    }

    @Test
    void testDoSave() {
        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");
        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        bean.setLearningAgreement(1);
        bean.setStato("compilato");
        bean.setLingua("spagnolo");
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);

        boolean ok = false;

        try {
            classUnderTest.doSave(bean);
            ok = true;
        } catch (Exception e){
            ok = false;
            e.printStackTrace();
        }

        assertTrue(ok);

        List<MappingEsame> list = classUnderTest.doRetrieveAll();
        MappingEsame bean = list.get(list.size() - 1);
        classUnderTest.doDelete(bean.getId());
    }

    @Test
    void testDoDelete() {
        System.out.println("doDelete");

        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");
        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        bean = new MappingEsame();
        bean.setLearningAgreement(1);
        bean.setStato("passato");
        bean.setLingua("spagnolo");
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);


        classUnderTest.doSave(bean);

        List<MappingEsame> list = classUnderTest.doRetrieveAll();
        MappingEsame bean = list.get(list.size()-1);

        boolean ok = false;

        try {
            classUnderTest.doDelete(bean.getId());
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
    void testDoRetrieveByLearningAgreement() {
        System.out.println("doRetrieveByIdCoordinatore");

        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");
        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        bean = new MappingEsame();
        bean.setLearningAgreement(1);
        bean.setStato("passato");
        bean.setLingua("spagnolo");
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);

        classUnderTest.doSave(bean);

        List<MappingEsame> list = classUnderTest.doRetrieveByLearningAgreement(bean.getLearningAgreement());
        Iterator<MappingEsame> i = list.iterator();
        boolean ok = true;
        while (i.hasNext()) {
            MappingEsame bean = i.next();
            if (bean.getLearningAgreement() != 1) {
                ok = false;
            }
        }
        assertTrue(ok);
    }

    @Test
    void testDoRetrieveAll() {
        System.out.println("doDelete");

        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");
        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        bean = new MappingEsame();
        bean.setLearningAgreement(1);
        bean.setStato("passato");
        bean.setLingua("spagnolo");
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);


        classUnderTest.doSave(bean);

        List<MappingEsame> list = classUnderTest.doRetrieveAll();
        assertNotEquals(0,list.size());


        MappingEsame bean = list.get(list.size()-1);
        classUnderTest.doDelete(bean.getId());
    }
    @Test
    void testDoUpdate() {
        System.out.println("doUpdate");

        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");
        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        bean = new MappingEsame();
        bean.setLearningAgreement(1);
        bean.setStato("passato");
        bean.setLingua("spagnolo");
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);


        classUnderTest.doSave(bean);

        List<MappingEsame> list = classUnderTest.doRetrieveAll();
        MappingEsame mappingEsame = list.get(list.size()-1);

        mappingEsame.setStato("annullato");

        boolean ok = false;

        try {
            classUnderTest.doUpdate(mappingEsame);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }
}