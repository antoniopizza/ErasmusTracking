package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MappingEsameManagerTest {
    private static MappingEsameManager classUnderTest;
    private static SendingInstituteManager sendingInstituteManager;
    private static CoordinatoriManager coordinatoreManager;
    private static StudenteManager studenteManager;
    private static LearningAgreementManager learningAgreementManager;
    private static AccountManager m;


    private static MappingEsame bean;
    private static SendingInstitute sendingInstitute;
    private static Coordinatore coordinatore;
    private static Studente studente;
    private static LearningAgreement learningAgreement;

    @BeforeAll
    static void setUp() throws Exception {
        try{
            classUnderTest = new MappingEsameManager("", "", "");
            sendingInstituteManager = new SendingInstituteManager("", "", "");
            coordinatoreManager = new CoordinatoriManager("", "", "");
            studenteManager = new StudenteManager("", "", "");
            learningAgreementManager = new LearningAgreementManager("", "", "");
            m = new AccountManager("", "", "");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            classUnderTest = new MappingEsameManager("erasmusTracking", "root", "root");
            sendingInstituteManager = new SendingInstituteManager("erasmusTracking", "root", "root");
            coordinatoreManager = new CoordinatoriManager("erasmusTracking", "root", "root");
            studenteManager = new StudenteManager("erasmusTracking", "root", "root");
            learningAgreementManager = new LearningAgreementManager("erasmusTracking", "root", "root");
            m = new AccountManager("erasmusTracking", "root", "root");
        }
    }

    @Test
    void testDoSave() {
        /**1-Set Sending Institute*/

        sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");

        boolean ok = false;
        try {
            sendingInstituteManager.doSave(sendingInstitute);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        /**2-Set Coordinatore*/

        coordinatore = new Coordinatore();
        coordinatore.setNome("Alessandro");
        coordinatore.setCognome("Rigido");
        coordinatore.setPassword("root");
        coordinatore.setEmail("a.rigido1@studenti.unisa.it");
        coordinatore.setRuolo("coordinatore");
        coordinatore.setSendingInstitute(sendingInstitute.getId());

        ok = false;
        try {
            coordinatoreManager.doSave(coordinatore);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
        assertNotEquals(0, listCoordinatori.size());
        coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

        /**3-Set Studente*/

        studente = new Studente();
        studente.setAnnoAccademico(1);
        studente.setDataDiNascita("12/12/2018");
        studente.setLuogoDiNascita("Caserta");
        studente.setTelefono("3012322297");
        studente.setCicloDiStudi("1-triennale");
        studente.setNazionalita("Italia");
        studente.setSesso("M");
        studente.setEmail("aleoale@live.it");
        studente.setCognome("Poldo");
        studente.setMatricola("0215456332");
        studente.setNome("alessandro");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setIdCoordinatore(coordinatore.getId());

        ok = false;
        try {
            studenteManager.doSave(studente);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
        studente = listStudenti.get(listStudenti.size() - 1);
        assertNotEquals(0, listStudenti.size());



        /**4-Set Learning Agreement*/

        learningAgreement = new LearningAgreement();
        learningAgreement.setTipologiaErasmus("studio");
        learningAgreement.setConoscenzaLingua("A1");
        learningAgreement.setDataInizio("12/09/2019");
        learningAgreement.setDataFine("01/02/2020");
        learningAgreement.setMatricolaStudente("000510");
        learningAgreement.setStato("compilato");
        learningAgreement.setStudente(studente);

        ok = false;
        try {
            learningAgreementManager.doSave(learningAgreement);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        List<LearningAgreement> listLearning = (List<LearningAgreement>) learningAgreementManager.doRetrieveAll();
        learningAgreement = listLearning.get(listLearning.size() - 1);
        assertNotEquals(0, listLearning.size());

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
        bean.setLearningAgreement(learningAgreement.getId());
        bean.setStato("passato");
        bean.setLingua("spagnolo");
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);

        ok = false;
        try{
            classUnderTest.doSave(bean);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }

        ok = false;
        try {
            learningAgreementManager.doDelete(learningAgreement.getId());
            studenteManager.doDelete(studente.getId());
            m.doDelete(studente.getId());
            coordinatoreManager.doDelete(coordinatore.getId());
            m.doDelete(coordinatore.getId());
            sendingInstituteManager.doDelete(sendingInstitute.getId());
            classUnderTest.doDelete(bean.getId());
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }

    @Test
    void testDoSave1() {
        /**1-Set Sending Institute*/

        sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");

        boolean ok = false;
        try {
            sendingInstituteManager.doSave(sendingInstitute);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        /**2-Set Coordinatore*/

        coordinatore = new Coordinatore();
        coordinatore.setNome("Alessandro");
        coordinatore.setCognome("Rigido");
        coordinatore.setPassword("root");
        coordinatore.setEmail("a.rigido1@studenti.unisa.it");
        coordinatore.setRuolo("coordinatore");
        coordinatore.setSendingInstitute(sendingInstitute.getId());

        ok = false;
        try {
            coordinatoreManager.doSave(coordinatore);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
        assertNotEquals(0, listCoordinatori.size());
        coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

        /**3-Set Studente*/

        studente = new Studente();
        studente.setAnnoAccademico(1);
        studente.setDataDiNascita("12/12/2018");
        studente.setLuogoDiNascita("Caserta");
        studente.setTelefono("3012322297");
        studente.setCicloDiStudi("1-triennale");
        studente.setNazionalita("Italia");
        studente.setSesso("M");
        studente.setEmail("aleoale@live.it");
        studente.setCognome("Poldo");
        studente.setMatricola("0215456332");
        studente.setNome("alessandro");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setIdCoordinatore(coordinatore.getId());

        ok = false;
        try {
            studenteManager.doSave(studente);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
        studente = listStudenti.get(listStudenti.size() - 1);
        assertNotEquals(0, listStudenti.size());



        /**4-Set Learning Agreement*/

        learningAgreement = new LearningAgreement();
        learningAgreement.setTipologiaErasmus("studio");
        learningAgreement.setConoscenzaLingua("A1");
        learningAgreement.setDataInizio("12/09/2019");
        learningAgreement.setDataFine("01/02/2020");
        learningAgreement.setMatricolaStudente("000510");
        learningAgreement.setStato("compilato");
        learningAgreement.setStudente(studente);

        ok = false;
        try {
            learningAgreementManager.doSave(learningAgreement);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        List<LearningAgreement> listLearning = (List<LearningAgreement>) learningAgreementManager.doRetrieveAll();
        learningAgreement = listLearning.get(listLearning.size() - 1);
        assertNotEquals(0, listLearning.size());

        /**5- Set Esame interno*/

        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");

        /**6- Set Esame esterno*/

        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        /**5- Set Mapping Esame*/

        bean = new MappingEsame();
        bean.setLearningAgreement(learningAgreement.getId());
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);

        ok = false;
        try{
            classUnderTest.doSave(bean);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);

        ok = false;
        try {
            learningAgreementManager.doDelete(learningAgreement.getId());
            studenteManager.doDelete(studente.getId());
            m.doDelete(studente.getId());
            coordinatoreManager.doDelete(coordinatore.getId());
            m.doDelete(coordinatore.getId());
            sendingInstituteManager.doDelete(sendingInstitute.getId());
            classUnderTest.doDelete(bean.getId());
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }


    @Test
    void testDoDelete() {
        System.out.println("doDelete");

        /**1-Set Sending Institute*/

        sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");

        boolean ok = false;
        try {
            sendingInstituteManager.doSave(sendingInstitute);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        /**2-Set Coordinatore*/

        coordinatore = new Coordinatore();
        coordinatore.setNome("Alessandro");
        coordinatore.setCognome("Rigido");
        coordinatore.setPassword("root");
        coordinatore.setEmail("a.rigido1@studenti.unisa.it");
        coordinatore.setRuolo("coordinatore");
        coordinatore.setSendingInstitute(sendingInstitute.getId());

        ok = false;
        try {
            coordinatoreManager.doSave(coordinatore);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
        assertNotEquals(0, listCoordinatori.size());
        coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

        /**3-Set Studente*/

        studente = new Studente();
        studente.setAnnoAccademico(1);
        studente.setDataDiNascita("12/12/2018");
        studente.setLuogoDiNascita("Caserta");
        studente.setTelefono("3012322297");
        studente.setCicloDiStudi("1-triennale");
        studente.setNazionalita("Italia");
        studente.setSesso("M");
        studente.setEmail("aleoale@live.it");
        studente.setCognome("Poldo");
        studente.setMatricola("0215456332");
        studente.setNome("alessandro");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setIdCoordinatore(coordinatore.getId());

        ok = false;
        try {
            studenteManager.doSave(studente);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
        studente = listStudenti.get(listStudenti.size() - 1);
        assertNotEquals(0, listStudenti.size());



        /**4-Set Learning Agreement*/

        learningAgreement = new LearningAgreement();
        learningAgreement.setTipologiaErasmus("studio");
        learningAgreement.setConoscenzaLingua("A1");
        learningAgreement.setDataInizio("12/09/2019");
        learningAgreement.setDataFine("01/02/2020");
        learningAgreement.setMatricolaStudente("000510");
        learningAgreement.setStato("compilato");
        learningAgreement.setStudente(studente);

        ok = false;
        try {
            learningAgreementManager.doSave(learningAgreement);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        List<LearningAgreement> listLearning = (List<LearningAgreement>) learningAgreementManager.doRetrieveAll();
        learningAgreement = listLearning.get(listLearning.size() - 1);
        assertNotEquals(0, listLearning.size());

        /**5- Set Esame interno*/

        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");

        /**6- Set Esame esterno*/

        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        /**5- Set Mapping Esame*/

        bean = new MappingEsame();
        bean.setLearningAgreement(learningAgreement.getId());
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);

        ok = false;
        try{
            classUnderTest.doSave(bean);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);

        ok = false;
        try {
            learningAgreementManager.doDelete(learningAgreement.getId());
            studenteManager.doDelete(studente.getId());
            m.doDelete(studente.getId());
            coordinatoreManager.doDelete(coordinatore.getId());
            m.doDelete(coordinatore.getId());
            sendingInstituteManager.doDelete(sendingInstitute.getId());
            classUnderTest.doDelete(bean.getId());
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }

    @Test
    void testDoRetrieveById() {
        /**1-Set Sending Institute*/

        sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");

        boolean ok = false;
        try {
            sendingInstituteManager.doSave(sendingInstitute);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        /**2-Set Coordinatore*/

        coordinatore = new Coordinatore();
        coordinatore.setNome("Alessandro");
        coordinatore.setCognome("Rigido");
        coordinatore.setPassword("root");
        coordinatore.setEmail("a.rigido1@studenti.unisa.it");
        coordinatore.setRuolo("coordinatore");
        coordinatore.setSendingInstitute(sendingInstitute.getId());

        ok = false;
        try {
            coordinatoreManager.doSave(coordinatore);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
        assertNotEquals(0, listCoordinatori.size());
        coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

        /**3-Set Studente*/

        studente = new Studente();
        studente.setAnnoAccademico(1);
        studente.setDataDiNascita("12/12/2018");
        studente.setLuogoDiNascita("Caserta");
        studente.setTelefono("3012322297");
        studente.setCicloDiStudi("1-triennale");
        studente.setNazionalita("Italia");
        studente.setSesso("M");
        studente.setEmail("aleoale@live.it");
        studente.setCognome("Poldo");
        studente.setMatricola("0215456332");
        studente.setNome("alessandro");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setIdCoordinatore(coordinatore.getId());

        ok = false;
        try {
            studenteManager.doSave(studente);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
        studente = listStudenti.get(listStudenti.size() - 1);
        assertNotEquals(0, listStudenti.size());



        /**4-Set Learning Agreement*/

        learningAgreement = new LearningAgreement();
        learningAgreement.setTipologiaErasmus("studio");
        learningAgreement.setConoscenzaLingua("A1");
        learningAgreement.setDataInizio("12/09/2019");
        learningAgreement.setDataFine("01/02/2020");
        learningAgreement.setMatricolaStudente("000510");
        learningAgreement.setStato("compilato");
        learningAgreement.setStudente(studente);

        ok = false;
        try {
            learningAgreementManager.doSave(learningAgreement);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        List<LearningAgreement> listLearning = (List<LearningAgreement>) learningAgreementManager.doRetrieveAll();
        learningAgreement = listLearning.get(listLearning.size() - 1);
        assertNotEquals(0, listLearning.size());

        /**5- Set Esame interno*/

        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");

        /**6- Set Esame esterno*/

        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        /**5- Set Mapping Esame*/

        bean = new MappingEsame();
        bean.setLearningAgreement(learningAgreement.getId());
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);

        ok = false;
        try{
            classUnderTest.doSave(bean);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);

        List<MappingEsame> listMapping = classUnderTest.doRetrieveAll();
        bean = listMapping.get(listMapping.size()-1);
        assertNotEquals(0,listMapping.size());

        MappingEsame ris = classUnderTest.doRetrieveById(bean.getId());
        assertNotEquals(null,ris);

        ok = false;
        try {
            learningAgreementManager.doDelete(learningAgreement.getId());
            studenteManager.doDelete(studente.getId());
            m.doDelete(studente.getId());
            coordinatoreManager.doDelete(coordinatore.getId());
            m.doDelete(coordinatore.getId());
            sendingInstituteManager.doDelete(sendingInstitute.getId());
            classUnderTest.doDelete(bean.getId());
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }

    @Test
    void testDoRetrieveByLearningAgreement() {
        System.out.println("doRetrieveByIdCoordinatore");

        /**1-Set Sending Institute*/

        sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");

        boolean ok = false;
        try {
            sendingInstituteManager.doSave(sendingInstitute);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        /**2-Set Coordinatore*/

        coordinatore = new Coordinatore();
        coordinatore.setNome("Alessandro");
        coordinatore.setCognome("Rigido");
        coordinatore.setPassword("root");
        coordinatore.setEmail("a.rigido1@studenti.unisa.it");
        coordinatore.setRuolo("coordinatore");
        coordinatore.setSendingInstitute(sendingInstitute.getId());

        ok = false;
        try {
            coordinatoreManager.doSave(coordinatore);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
        assertNotEquals(0, listCoordinatori.size());
        coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

        /**3-Set Studente*/

        studente = new Studente();
        studente.setAnnoAccademico(1);
        studente.setDataDiNascita("12/12/2018");
        studente.setLuogoDiNascita("Caserta");
        studente.setTelefono("3012322297");
        studente.setCicloDiStudi("1-triennale");
        studente.setNazionalita("Italia");
        studente.setSesso("M");
        studente.setEmail("aleoale@live.it");
        studente.setCognome("Poldo");
        studente.setMatricola("0215456332");
        studente.setNome("alessandro");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setIdCoordinatore(coordinatore.getId());

        ok = false;
        try {
            studenteManager.doSave(studente);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
        studente = listStudenti.get(listStudenti.size() - 1);
        assertNotEquals(0, listStudenti.size());



        /**4-Set Learning Agreement*/

        learningAgreement = new LearningAgreement();
        learningAgreement.setTipologiaErasmus("studio");
        learningAgreement.setConoscenzaLingua("A1");
        learningAgreement.setDataInizio("12/09/2019");
        learningAgreement.setDataFine("01/02/2020");
        learningAgreement.setMatricolaStudente("000510");
        learningAgreement.setStato("compilato");
        learningAgreement.setStudente(studente);

        ok = false;
        try {
            learningAgreementManager.doSave(learningAgreement);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        List<LearningAgreement> listLearning = (List<LearningAgreement>) learningAgreementManager.doRetrieveAll();
        learningAgreement = listLearning.get(listLearning.size() - 1);
        assertNotEquals(0, listLearning.size());

        /**5- Set Esame interno*/

        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");

        /**6- Set Esame esterno*/

        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        /**5- Set Mapping Esame*/

        bean = new MappingEsame();
        bean.setLearningAgreement(learningAgreement.getId());
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);

        ok = false;
        try{
            classUnderTest.doSave(bean);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);

        List<MappingEsame> listMapping = classUnderTest.doRetrieveAll();
        bean = listMapping.get(listMapping.size()-1);
        assertNotEquals(0,listMapping.size());

        List<MappingEsame> ris = classUnderTest.doRetrieveByLearningAgreement(learningAgreement.getId());
        assertNotEquals(0,ris.size());

        ok = false;
        try {
            learningAgreementManager.doDelete(learningAgreement.getId());
            studenteManager.doDelete(studente.getId());
            m.doDelete(studente.getId());
            coordinatoreManager.doDelete(coordinatore.getId());
            m.doDelete(coordinatore.getId());
            sendingInstituteManager.doDelete(sendingInstitute.getId());
            classUnderTest.doDelete(bean.getId());
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);


    }

    @Test
    void testDoRetrieveAll() {
        System.out.println("doDelete");

        /**1-Set Sending Institute*/

        sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");

        boolean ok = false;
        try {
            sendingInstituteManager.doSave(sendingInstitute);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        /**2-Set Coordinatore*/

        coordinatore = new Coordinatore();
        coordinatore.setNome("Alessandro");
        coordinatore.setCognome("Rigido");
        coordinatore.setPassword("root");
        coordinatore.setEmail("a.rigido1@studenti.unisa.it");
        coordinatore.setRuolo("coordinatore");
        coordinatore.setSendingInstitute(sendingInstitute.getId());

        ok = false;
        try {
            coordinatoreManager.doSave(coordinatore);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
        assertNotEquals(0, listCoordinatori.size());
        coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

        /**3-Set Studente*/

        studente = new Studente();
        studente.setAnnoAccademico(1);
        studente.setDataDiNascita("12/12/2018");
        studente.setLuogoDiNascita("Caserta");
        studente.setTelefono("3012322297");
        studente.setCicloDiStudi("1-triennale");
        studente.setNazionalita("Italia");
        studente.setSesso("M");
        studente.setEmail("aleoale@live.it");
        studente.setCognome("Poldo");
        studente.setMatricola("0215456332");
        studente.setNome("alessandro");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setIdCoordinatore(coordinatore.getId());

        ok = false;
        try {
            studenteManager.doSave(studente);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
        studente = listStudenti.get(listStudenti.size() - 1);
        assertNotEquals(0, listStudenti.size());



        /**4-Set Learning Agreement*/

        learningAgreement = new LearningAgreement();
        learningAgreement.setTipologiaErasmus("studio");
        learningAgreement.setConoscenzaLingua("A1");
        learningAgreement.setDataInizio("12/09/2019");
        learningAgreement.setDataFine("01/02/2020");
        learningAgreement.setMatricolaStudente("000510");
        learningAgreement.setStato("compilato");
        learningAgreement.setStudente(studente);

        ok = false;
        try {
            learningAgreementManager.doSave(learningAgreement);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        List<LearningAgreement> listLearning = (List<LearningAgreement>) learningAgreementManager.doRetrieveAll();
        learningAgreement = listLearning.get(listLearning.size() - 1);
        assertNotEquals(0, listLearning.size());

        /**5- Set Esame interno*/

        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");

        /**6- Set Esame esterno*/

        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        /**5- Set Mapping Esame*/

        bean = new MappingEsame();
        bean.setLearningAgreement(learningAgreement.getId());
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);

        ok = false;
        try{
            classUnderTest.doSave(bean);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);

        List<MappingEsame> listMapping = classUnderTest.doRetrieveAll();
        bean = listMapping.get(listMapping.size()-1);
        assertNotEquals(0,listMapping.size());


        ok = false;
        try {
            learningAgreementManager.doDelete(learningAgreement.getId());
            studenteManager.doDelete(studente.getId());
            m.doDelete(studente.getId());
            coordinatoreManager.doDelete(coordinatore.getId());
            m.doDelete(coordinatore.getId());
            sendingInstituteManager.doDelete(sendingInstitute.getId());
            classUnderTest.doDelete(bean.getId());
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }
    @Test
    void testDoUpdate() {
        /**1-Set Sending Institute*/

        sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");

        boolean ok = false;
        try {
            sendingInstituteManager.doSave(sendingInstitute);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        /**2-Set Coordinatore*/

        coordinatore = new Coordinatore();
        coordinatore.setNome("Alessandro");
        coordinatore.setCognome("Rigido");
        coordinatore.setPassword("root");
        coordinatore.setEmail("a.rigido1@studenti.unisa.it");
        coordinatore.setRuolo("coordinatore");
        coordinatore.setSendingInstitute(sendingInstitute.getId());

        ok = false;
        try {
            coordinatoreManager.doSave(coordinatore);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) coordinatoreManager.doRetrieveAll();
        assertNotEquals(0, listCoordinatori.size());
        coordinatore = listCoordinatori.get(listCoordinatori.size() - 1);

        /**3-Set Studente*/

        studente = new Studente();
        studente.setAnnoAccademico(1);
        studente.setDataDiNascita("12/12/2018");
        studente.setLuogoDiNascita("Caserta");
        studente.setTelefono("3012322297");
        studente.setCicloDiStudi("1-triennale");
        studente.setNazionalita("Italia");
        studente.setSesso("M");
        studente.setEmail("aleoale@live.it");
        studente.setCognome("Poldo");
        studente.setMatricola("0215456332");
        studente.setNome("alessandro");
        studente.setPassword("root");
        studente.setRuolo("studente");
        studente.setIdCoordinatore(coordinatore.getId());

        ok = false;
        try {
            studenteManager.doSave(studente);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        ArrayList<Studente> listStudenti = (ArrayList<Studente>) studenteManager.doRetrieveAll();
        studente = listStudenti.get(listStudenti.size() - 1);
        assertNotEquals(0, listStudenti.size());


        /**4-Set Learning Agreement*/

        learningAgreement = new LearningAgreement();
        learningAgreement.setTipologiaErasmus("studio");
        learningAgreement.setConoscenzaLingua("A1");
        learningAgreement.setDataInizio("12/09/2019");
        learningAgreement.setDataFine("01/02/2020");
        learningAgreement.setMatricolaStudente("000510");
        learningAgreement.setStato("compilato");
        learningAgreement.setStudente(studente);

        ok = false;
        try {
            learningAgreementManager.doSave(learningAgreement);
            ok = true;
        } catch (Exception e) {
            ok = false;
            e.printStackTrace();
        }
        assertTrue(ok);

        List<LearningAgreement> listLearning = (List<LearningAgreement>) learningAgreementManager.doRetrieveAll();
        learningAgreement = listLearning.get(listLearning.size() - 1);
        assertNotEquals(0, listLearning.size());

        /**5- Set Esame interno*/

        Esame esameInterno = new Esame();
        esameInterno.setCodice("01");
        esameInterno.setNome("fisica");
        esameInterno.setCreditiFormativi(6);
        esameInterno.setSemestre("1");

        /**6- Set Esame esterno*/

        Esame esameEsterno = new Esame();
        esameEsterno.setCodice("10");
        esameEsterno.setNome("fisica");
        esameEsterno.setCreditiFormativi(6);
        esameEsterno.setSemestre("2");

        /**5- Set Mapping Esame*/

        bean = new MappingEsame();
        bean.setLearningAgreement(learningAgreement.getId());
        bean.setEsameEsterno(esameEsterno);
        bean.setEsameInterno(esameInterno);

        ok = false;
        try{
            classUnderTest.doSave(bean);
            ok = true;
        }catch(Exception e){
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);

        List<MappingEsame> listMapping = classUnderTest.doRetrieveAll();
        bean = listMapping.get(listMapping.size() - 1);
        assertNotEquals(0, listMapping.size());

        bean.setEsameInterno(esameInterno);
        bean.setEsameEsterno(esameEsterno);
        bean.setLingua("A1");
        bean.setStato("non pervenuto");
        try {
            classUnderTest.doUpdate(bean);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);

        listMapping = classUnderTest.doRetrieveAll();
        bean = listMapping.get(listMapping.size() - 1);
        assertNotEquals(0, listMapping.size());


        ok = false;
        try {
            learningAgreementManager.doDelete(learningAgreement.getId());
            studenteManager.doDelete(studente.getId());
            m.doDelete(studente.getId());
            coordinatoreManager.doDelete(coordinatore.getId());
            m.doDelete(coordinatore.getId());
            sendingInstituteManager.doDelete(sendingInstitute.getId());
            classUnderTest.doDelete(bean.getId());
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }
}