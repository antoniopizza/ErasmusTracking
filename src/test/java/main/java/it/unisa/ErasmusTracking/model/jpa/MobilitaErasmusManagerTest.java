package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.MobilitaErasmus;
import main.java.it.unisa.ErasmusTracking.bean.ReceivingInstitute;
import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MobilitaErasmusManagerTest {

    private static MobilitaErasmusManager manager;
    private static MobilitaErasmus mobilitaErasmus;
    private static Integer id = 1;

    @BeforeAll
    static void setUp() throws SQLException {
        try {
            manager = new MobilitaErasmusManager("", "", "");
        } catch(Exception e) {
            e.printStackTrace();
        }finally {
            manager = new MobilitaErasmusManager("erasmusTracking","root","root");
        }

    }

    @Test
    void testDoSave() {
        System.out.println("doSave");

        mobilitaErasmus = new MobilitaErasmus();
        mobilitaErasmus.setDataInizio("24/01/2019");
        mobilitaErasmus.setDataFine("26/06/2019");
        mobilitaErasmus.setStato("in corso");
        mobilitaErasmus.setLearningAgreement(1);

        SendingInstituteManager sendingInstituteManager = new SendingInstituteManager("erasmustracking", "root","root");
        SendingInstitute  sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");
        sendingInstituteManager.doSave(sendingInstitute);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        ReceivingInstituteManager receivingInstituteManager = new ReceivingInstituteManager("erasmustracking", "root","root");
        ReceivingInstitute receivingInstitute = new ReceivingInstitute();
        receivingInstitute.setEmailContatto("gio@gmail.com");
        receivingInstitute.setNomeContatto("giorgio");
        receivingInstitute.setEmailMentore("fra@gmail.com");
        receivingInstitute.setNomeMentore("franco");
        receivingInstitute.setSizeOfEnterprise("media");
        receivingInstitute.setLocalita(1);
        receivingInstitute.setWebsite("www.unisa.it");
        receivingInstituteManager.doSave(receivingInstitute);
        List<ReceivingInstitute> listReceiving = receivingInstituteManager.doRetrieveAll();
        receivingInstitute = listReceiving.get(listReceiving.size() - 1);

        mobilitaErasmus.setReceivingInstitute(receivingInstitute);
        mobilitaErasmus.setSendingInstitute(sendingInstitute);

        boolean ok = false;
        try{
            manager.doSave(mobilitaErasmus);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);

        List<MobilitaErasmus> list = manager.doRetrieveAll();
        MobilitaErasmus bean = list.get(list.size() - 1);
        manager.doDelete(bean.getId());
        receivingInstituteManager.doDelete(receivingInstitute.getId());
        sendingInstituteManager.doDelete(sendingInstitute.getId());
    }

    @Test
    void testDoSave1() {
        System.out.println("doSave con campi nulli");

        mobilitaErasmus = new MobilitaErasmus();
        System.out.println(mobilitaErasmus.getDataInizio() + mobilitaErasmus.getDataFine());
        mobilitaErasmus.setLearningAgreement(1);

        SendingInstituteManager sendingInstituteManager = new SendingInstituteManager("erasmustracking", "root","root");
        SendingInstitute  sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");
        sendingInstituteManager.doSave(sendingInstitute);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        ReceivingInstituteManager receivingInstituteManager = new ReceivingInstituteManager("erasmustracking", "root","root");
        ReceivingInstitute receivingInstitute = new ReceivingInstitute();
        receivingInstitute.setEmailContatto("gio@gmail.com");
        receivingInstitute.setNomeContatto("giorgio");
        receivingInstitute.setEmailMentore("fra@gmail.com");
        receivingInstitute.setNomeMentore("franco");
        receivingInstitute.setSizeOfEnterprise("media");
        receivingInstitute.setLocalita(1);
        receivingInstitute.setWebsite("www.unisa.it");
        receivingInstituteManager.doSave(receivingInstitute);
        List<ReceivingInstitute> listReceiving = receivingInstituteManager.doRetrieveAll();
        receivingInstitute = listReceiving.get(listReceiving.size() - 1);

        mobilitaErasmus.setReceivingInstitute(receivingInstitute);
        mobilitaErasmus.setSendingInstitute(sendingInstitute);

        boolean ok = false;
        try{
            manager.doSave(mobilitaErasmus);
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);

        List<MobilitaErasmus> list = manager.doRetrieveAll();
        MobilitaErasmus bean = list.get(list.size() - 1);
        manager.doDelete(bean.getId());
        receivingInstituteManager.doDelete(receivingInstitute.getId());
        sendingInstituteManager.doDelete(sendingInstitute.getId());

    }

    @Test
    void testDoDelete() {
        System.out.println("doDelete");

        mobilitaErasmus = new MobilitaErasmus();
        mobilitaErasmus.setDataInizio("24/01/2019");
        mobilitaErasmus.setDataFine("26/06/2019");
        mobilitaErasmus.setStato("in corso");
        mobilitaErasmus.setLearningAgreement(1);

        SendingInstituteManager sendingInstituteManager = new SendingInstituteManager("erasmustracking", "root","root");
        SendingInstitute  sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");
        sendingInstituteManager.doSave(sendingInstitute);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        ReceivingInstituteManager receivingInstituteManager = new ReceivingInstituteManager("erasmustracking", "root","root");
        ReceivingInstitute receivingInstitute = new ReceivingInstitute();
        receivingInstitute.setEmailContatto("gio@gmail.com");
        receivingInstitute.setNomeContatto("giorgio");
        receivingInstitute.setEmailMentore("fra@gmail.com");
        receivingInstitute.setNomeMentore("franco");
        receivingInstitute.setSizeOfEnterprise("media");
        receivingInstitute.setLocalita(1);
        receivingInstitute.setWebsite("www.unisa.it");
        receivingInstituteManager.doSave(receivingInstitute);
        List<ReceivingInstitute> listReceiving = receivingInstituteManager.doRetrieveAll();
        receivingInstitute = listReceiving.get(listReceiving.size() - 1);

        mobilitaErasmus.setReceivingInstitute(receivingInstitute);
        mobilitaErasmus.setSendingInstitute(sendingInstitute);

        manager.doSave(mobilitaErasmus);

        List<MobilitaErasmus> list = manager.doRetrieveAll();
        MobilitaErasmus bean = list.get(list.size() - 1);
        boolean ok;
        try{
            manager.doDelete(bean.getId());
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
        assertTrue(ok);


    }

    @Test
    void testDoRetrieveAll() {
        System.out.println("doretrieveAll");

        mobilitaErasmus = new MobilitaErasmus();
        mobilitaErasmus.setDataInizio("24/01/2019");
        mobilitaErasmus.setDataFine("26/06/2019");
        mobilitaErasmus.setStato("in corso");
        mobilitaErasmus.setLearningAgreement(1);

        SendingInstituteManager sendingInstituteManager = new SendingInstituteManager("erasmustracking", "root","root");
        SendingInstitute  sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");
        sendingInstituteManager.doSave(sendingInstitute);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        ReceivingInstituteManager receivingInstituteManager = new ReceivingInstituteManager("erasmustracking", "root","root");
        ReceivingInstitute receivingInstitute = new ReceivingInstitute();
        receivingInstitute.setEmailContatto("gio@gmail.com");
        receivingInstitute.setNomeContatto("giorgio");
        receivingInstitute.setEmailMentore("fra@gmail.com");
        receivingInstitute.setNomeMentore("franco");
        receivingInstitute.setSizeOfEnterprise("media");
        receivingInstitute.setLocalita(1);
        receivingInstitute.setWebsite("www.unisa.it");
        receivingInstituteManager.doSave(receivingInstitute);
        List<ReceivingInstitute> listReceiving = receivingInstituteManager.doRetrieveAll();
        receivingInstitute = listReceiving.get(listReceiving.size() - 1);

        mobilitaErasmus.setReceivingInstitute(receivingInstitute);
        mobilitaErasmus.setSendingInstitute(sendingInstitute);

        manager.doSave(mobilitaErasmus);
        List<MobilitaErasmus> list = manager.doRetrieveAll();
        assertNotEquals(0, list.size());

        MobilitaErasmus bean = list.get(list.size() - 1);
        manager.doDelete(bean.getId());
        receivingInstituteManager.doDelete(receivingInstitute.getId());
        sendingInstituteManager.doDelete(sendingInstitute.getId());
    }

    @Test
    void testDoRetrieveById() {
        System.out.println("doRetrieveById");

        mobilitaErasmus = new MobilitaErasmus();
        mobilitaErasmus.setDataInizio("24/01/2019");
        mobilitaErasmus.setDataFine("26/06/2019");
        mobilitaErasmus.setStato("in corso");
        mobilitaErasmus.setLearningAgreement(1);

        SendingInstituteManager sendingInstituteManager = new SendingInstituteManager("erasmustracking", "root","root");
        SendingInstitute  sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");
        sendingInstituteManager.doSave(sendingInstitute);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        ReceivingInstituteManager receivingInstituteManager = new ReceivingInstituteManager("erasmustracking", "root","root");
        ReceivingInstitute receivingInstitute = new ReceivingInstitute();
        receivingInstitute.setEmailContatto("gio@gmail.com");
        receivingInstitute.setNomeContatto("giorgio");
        receivingInstitute.setEmailMentore("fra@gmail.com");
        receivingInstitute.setNomeMentore("franco");
        receivingInstitute.setSizeOfEnterprise("media");
        receivingInstitute.setLocalita(1);
        receivingInstitute.setWebsite("www.unisa.it");
        receivingInstituteManager.doSave(receivingInstitute);
        List<ReceivingInstitute> listReceiving = receivingInstituteManager.doRetrieveAll();
        receivingInstitute = listReceiving.get(listReceiving.size() - 1);

        mobilitaErasmus.setReceivingInstitute(receivingInstitute);
        mobilitaErasmus.setSendingInstitute(sendingInstitute);

        List<MobilitaErasmus> list = manager.doRetrieveAll();
        MobilitaErasmus bean = list.get(list.size() - 1);

        mobilitaErasmus = (MobilitaErasmus) manager.doRetrieveById(bean.getId());
        assertEquals(bean.getId(), mobilitaErasmus.getId());

        manager.doDelete(bean.getId());
        receivingInstituteManager.doDelete(receivingInstitute.getId());
        sendingInstituteManager.doDelete(sendingInstitute.getId());

    }

    @Test
    void testDoRetrieveByIdSending() {
        System.out.println("doRetrieveByIdSending");

        mobilitaErasmus = new MobilitaErasmus();
        mobilitaErasmus.setDataInizio("24/01/2019");
        mobilitaErasmus.setDataFine("26/06/2019");
        mobilitaErasmus.setStato("in corso");
        mobilitaErasmus.setLearningAgreement(1);

        SendingInstituteManager sendingInstituteManager = new SendingInstituteManager("erasmustracking", "root","root");
        SendingInstitute  sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");
        sendingInstituteManager.doSave(sendingInstitute);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        ReceivingInstituteManager receivingInstituteManager = new ReceivingInstituteManager("erasmustracking", "root","root");
        ReceivingInstitute receivingInstitute = new ReceivingInstitute();
        receivingInstitute.setEmailContatto("gio@gmail.com");
        receivingInstitute.setNomeContatto("giorgio");
        receivingInstitute.setEmailMentore("fra@gmail.com");
        receivingInstitute.setNomeMentore("franco");
        receivingInstitute.setSizeOfEnterprise("media");
        receivingInstitute.setLocalita(1);
        receivingInstitute.setWebsite("www.unisa.it");
        receivingInstituteManager.doSave(receivingInstitute);
        List<ReceivingInstitute> listReceiving = receivingInstituteManager.doRetrieveAll();
        receivingInstitute = listReceiving.get(listReceiving.size() - 1);

        mobilitaErasmus.setReceivingInstitute(receivingInstitute);
        mobilitaErasmus.setSendingInstitute(sendingInstitute);

        manager.doSave(mobilitaErasmus);

        List<MobilitaErasmus> list = manager.doRetrieveByIdSending(mobilitaErasmus.getSendingInstitute().getId());
        Iterator<MobilitaErasmus> i = list.iterator();
        boolean ok = true;
        while (i.hasNext()) {
            MobilitaErasmus bean = i.next();
            if (bean.getSendingInstitute().getId() != mobilitaErasmus.getSendingInstitute().getId()) {
                ok = false;
            }
        }
        assertTrue(ok);

        manager.doDelete(list.get(list.size() - 1).getId());
        receivingInstituteManager.doDelete(receivingInstitute.getId());
        sendingInstituteManager.doDelete(sendingInstitute.getId());

    }

    @Test
    void testDoUpdate() {
        System.out.println("doUpdate");

        mobilitaErasmus = new MobilitaErasmus();
        mobilitaErasmus.setDataInizio("24/01/2019");
        mobilitaErasmus.setDataFine("26/06/2019");
        mobilitaErasmus.setStato("in corso");
        mobilitaErasmus.setLearningAgreement(1);

        SendingInstituteManager sendingInstituteManager = new SendingInstituteManager("erasmustracking", "root","root");
        SendingInstitute  sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");
        sendingInstituteManager.doSave(sendingInstitute);
        List<SendingInstitute> listSending = sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        ReceivingInstituteManager receivingInstituteManager = new ReceivingInstituteManager("erasmustracking", "root","root");
        ReceivingInstitute receivingInstitute = new ReceivingInstitute();
        receivingInstitute.setEmailContatto("gio@gmail.com");
        receivingInstitute.setNomeContatto("giorgio");
        receivingInstitute.setEmailMentore("fra@gmail.com");
        receivingInstitute.setNomeMentore("franco");
        receivingInstitute.setSizeOfEnterprise("media");
        receivingInstitute.setLocalita(1);
        receivingInstitute.setWebsite("www.unisa.it");
        receivingInstituteManager.doSave(receivingInstitute);
        List<ReceivingInstitute> listReceiving = receivingInstituteManager.doRetrieveAll();
        receivingInstitute = listReceiving.get(listReceiving.size() - 1);

        mobilitaErasmus.setReceivingInstitute(receivingInstitute);
        mobilitaErasmus.setSendingInstitute(sendingInstitute);

        manager.doSave(mobilitaErasmus);

        List<MobilitaErasmus> list = manager.doRetrieveAll();
        MobilitaErasmus bean = list.get(list.size() - 1);

        bean.setStato("concluso");
        bean.setDataFine("12/02/2019");

        boolean ok = false;
        try {
            manager.doUpdate(bean);
            ok = true;
        } catch (Exception e) {
            ok = false;
        }
        assertTrue(ok);

        manager.doDelete(bean.getId());
        receivingInstituteManager.doDelete(receivingInstitute.getId());
        sendingInstituteManager.doDelete(sendingInstitute.getId());

    }

    @Test
    void testDoRetrieveByLearningAgreement() {
        System.out.println("doRetrieveByLearningAgreement");

        mobilitaErasmus = new MobilitaErasmus();
        mobilitaErasmus.setDataInizio("24/01/2019");
        mobilitaErasmus.setDataFine("26/06/2019");
        mobilitaErasmus.setStato("in corso");
        mobilitaErasmus.setLearningAgreement(1);

        SendingInstituteManager sendingInstituteManager = new SendingInstituteManager("erasmustracking", "root","root");
        SendingInstitute  sendingInstitute = new SendingInstitute();
        sendingInstitute.setCodiceErasmus("15478");
        sendingInstitute.setDipartimento("informatica");
        sendingInstitute.setIndirizzo("fisciano");
        sendingInstituteManager.doSave(sendingInstitute);
        List<SendingInstitute> listSending = (ArrayList<SendingInstitute>) sendingInstituteManager.doRetrieveAll();
        sendingInstitute = listSending.get(listSending.size() - 1);

        ReceivingInstituteManager receivingInstituteManager = new ReceivingInstituteManager("erasmustracking", "root","root");
        ReceivingInstitute receivingInstitute = new ReceivingInstitute();
        receivingInstitute.setEmailContatto("gio@gmail.com");
        receivingInstitute.setNomeContatto("giorgio");
        receivingInstitute.setEmailMentore("fra@gmail.com");
        receivingInstitute.setNomeMentore("franco");
        receivingInstitute.setSizeOfEnterprise("media");
        receivingInstitute.setLocalita(1);
        receivingInstitute.setWebsite("www.unisa.it");
        receivingInstituteManager.doSave(receivingInstitute);
        List<ReceivingInstitute> listReceiving = receivingInstituteManager.doRetrieveAll();
        receivingInstitute = listReceiving.get(listReceiving.size() - 1);

        mobilitaErasmus.setReceivingInstitute(receivingInstitute);
        mobilitaErasmus.setSendingInstitute(sendingInstitute);

        manager.doSave(mobilitaErasmus);

        MobilitaErasmus bean = (MobilitaErasmus) manager.doRetrieveByLearningAgreement(mobilitaErasmus.getLearningAgreement());
        mobilitaErasmus.setId(bean.getId());

        assertEquals(bean, mobilitaErasmus);

        manager.doDelete(bean.getId());
        receivingInstituteManager.doDelete(receivingInstitute.getId());
        sendingInstituteManager.doDelete(sendingInstitute.getId());
    }
}