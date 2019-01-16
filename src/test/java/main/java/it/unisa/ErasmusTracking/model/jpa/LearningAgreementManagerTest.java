package main.java.it.unisa.ErasmusTracking.model.jpa;

import com.sun.tools.javac.code.Type;
import main.java.it.unisa.ErasmusTracking.bean.Esame;
import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.bean.MappingEsame;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LearningAgreementManagerTest {
    private static LearningAgreementManager classUnderTest;
    private static Integer id;
    private static LearningAgreement bean;

    @BeforeAll
    static void setUp() throws Exception {
        classUnderTest = new LearningAgreementManager("erasmustracking",  "root", "root");
        id = 5;
    }

    @Test
    void testDoSave() {
        Studente studente = new Studente();
        studente.setCodiceMateria("01");
        studente.setEmail("root@gmail.com");
        studente.setAnnoAccademico(2);
        studente.setTelefono("08292332");
        studente.setLuogoDiNascita("Salerno");
        studente.setNome("Luca");
        studente.setCognome("Rossi");
        studente.setIdCoordinatore(1);
        studente.setSesso("M");
        studente.setNazionalita("Italiana");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setId(2);

        MappingEsame mappingEsameUno = new MappingEsame();
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

        mappingEsameUno.setLearningAgreement(1);
        mappingEsameUno.setStato("compilato");
        mappingEsameUno.setLingua("spagnolo");
        mappingEsameUno.setEsameEsterno(esameEsterno);
        mappingEsameUno.setEsameInterno(esameInterno);

        bean = new LearningAgreement();
        bean.setTipologiaErasmus("studio");
        bean.setConoscenzaLingua("A1");
        bean.setDataInizio("12/09/2019");
        bean.setDataFine("01/02/2020");
        bean.setMatricolaStudente("000510");
        bean.setStato("compilato");
        bean.setStudente(studente);

        boolean ok = false;

        try {
            classUnderTest.doSave(bean);
            ok = true;
        } catch (Exception e){
            ok = false;
            e.printStackTrace();
        }

        assertTrue(ok);

        List<LearningAgreement> list = (List<LearningAgreement>) classUnderTest.doRetrieveAll();
        LearningAgreement bean = list.get(list.size() - 1);
        classUnderTest.doDelete(bean.getId());
    }

    @Test
    void testDoDelete() {
        Studente studente = new Studente();
        studente.setCodiceMateria("01");
        studente.setEmail("root@gmail.com");
        studente.setAnnoAccademico(2);
        studente.setTelefono("08292332");
        studente.setLuogoDiNascita("Salerno");
        studente.setNome("Luca");
        studente.setCognome("Rossi");
        studente.setIdCoordinatore(1);
        studente.setSesso("M");
        studente.setNazionalita("Italiana");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setId(2);

        MappingEsame mappingEsameUno = new MappingEsame();
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

        mappingEsameUno.setLearningAgreement(1);
        mappingEsameUno.setStato("compilato");
        mappingEsameUno.setLingua("spagnolo");
        mappingEsameUno.setEsameEsterno(esameEsterno);
        mappingEsameUno.setEsameInterno(esameInterno);

        bean = new LearningAgreement();
        bean.setTipologiaErasmus("studio");
        bean.setConoscenzaLingua("A1");
        bean.setDataInizio("12/09/2019");
        bean.setDataFine("01/02/2020");
        bean.setMatricolaStudente("000510");
        bean.setStato("compilato");
        bean.setStudente(studente);

        classUnderTest.doSave(bean);

        List<LearningAgreement> list = (List<LearningAgreement>) classUnderTest.doRetrieveAll();
        LearningAgreement bean = list.get(list.size() - 1);

        boolean ok = false;

        try {
            classUnderTest.doDelete(bean.getId());
            ok = true;
        } catch (Exception e){
            ok = false;
            e.printStackTrace();
        }

        assertTrue(ok);
    }

    @Test
    void testDoRetrieveAll() {
        Studente studente = new Studente();
        studente.setCodiceMateria("01");
        studente.setEmail("root@gmail.com");
        studente.setAnnoAccademico(2);
        studente.setTelefono("08292332");
        studente.setLuogoDiNascita("Salerno");
        studente.setNome("Luca");
        studente.setCognome("Rossi");
        studente.setIdCoordinatore(1);
        studente.setSesso("M");
        studente.setNazionalita("Italiana");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setId(2);

        MappingEsame mappingEsameUno = new MappingEsame();
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

        mappingEsameUno.setLearningAgreement(1);
        mappingEsameUno.setStato("compilato");
        mappingEsameUno.setLingua("spagnolo");
        mappingEsameUno.setEsameEsterno(esameEsterno);
        mappingEsameUno.setEsameInterno(esameInterno);

        bean = new LearningAgreement();
        bean.setTipologiaErasmus("studio");
        bean.setConoscenzaLingua("A1");
        bean.setDataInizio("12/09/2019");
        bean.setDataFine("01/02/2020");
        bean.setMatricolaStudente("000510");
        bean.setStato("compilato");
        bean.setStudente(studente);

        classUnderTest.doSave(bean);

        List<LearningAgreement> list = (List<LearningAgreement>) classUnderTest.doRetrieveAll();
        LearningAgreement bean = list.get(list.size() - 1);

        assertNotEquals(0,list.size());


        classUnderTest.doDelete(bean.getId());
    }

    @Test
    void testDoUpdate(){
        Studente studente = new Studente();
        studente.setCodiceMateria("01");
        studente.setEmail("root@gmail.com");
        studente.setAnnoAccademico(2);
        studente.setTelefono("08292332");
        studente.setLuogoDiNascita("Salerno");
        studente.setNome("Luca");
        studente.setCognome("Rossi");
        studente.setIdCoordinatore(1);
        studente.setSesso("M");
        studente.setNazionalita("Italiana");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setId(2);

        MappingEsame mappingEsameUno = new MappingEsame();
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

        mappingEsameUno.setLearningAgreement(1);
        mappingEsameUno.setStato("compilato");
        mappingEsameUno.setLingua("spagnolo");
        mappingEsameUno.setEsameEsterno(esameEsterno);
        mappingEsameUno.setEsameInterno(esameInterno);

        bean = new LearningAgreement();
        bean.setTipologiaErasmus("studio");
        bean.setConoscenzaLingua("A1");
        bean.setDataInizio("12/09/2019");
        bean.setDataFine("01/02/2020");
        bean.setMatricolaStudente("000510");
        bean.setStato("compilato");
        bean.setStudente(studente);

        boolean ok = false;

        List<LearningAgreement> list = (List<LearningAgreement>) classUnderTest.doRetrieveAll();
        LearningAgreement learningAgreement = list.get(list.size() - 1);

        learningAgreement.setDataFine("12/12/2019");

        try {
            classUnderTest.doUpdate(learningAgreement);
            ok = true;
        } catch (Exception e){
            ok = false;
            e.printStackTrace();
        }
        classUnderTest.doDelete(learningAgreement.getId());
        assertTrue(ok);
    }

    @Test
    void doRetrieveByStudente() {
        bean = (LearningAgreement) classUnderTest.doRetrieveByStudente(id);
        assertEquals(0, bean.getId());
    }

    @Test
    void doRetrieveById() {
        bean = (LearningAgreement) classUnderTest.doRetrieveById(id);
        assertEquals(0, bean.getId());
    }
}