package main.java.it.unisa.ErasmusTracking.model.jpa;

import main.java.it.unisa.ErasmusTracking.bean.MobilitaErasmus;
import main.java.it.unisa.ErasmusTracking.bean.ReceivingInstitute;
import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MobilitaErasmusManagerTest {

    MobilitaErasmusManager manager = new MobilitaErasmusManager("erasmustracking", "root","root");
    MobilitaErasmus mobilitaErasmus;
    Integer id = 0;

    @Test
    void testDoSave() {
        System.out.println("doSave");

        mobilitaErasmus = new MobilitaErasmus();
        mobilitaErasmus.setDataInizio("24/01/2019");
        mobilitaErasmus.setDataFine("26/06/2019");
        mobilitaErasmus.setStato("in corso");
        mobilitaErasmus.setLearningAgreement(1);
        mobilitaErasmus.setReceivingInstitute(new ReceivingInstitute());
        mobilitaErasmus.getReceivingInstitute().setId(1);
        mobilitaErasmus.setSendingInstitute( new SendingInstitute());
        mobilitaErasmus.getReceivingInstitute().setId(1);

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
    }

    @Test
    void testDoDelete() {
        System.out.println("doDelete");

        mobilitaErasmus = new MobilitaErasmus();
        mobilitaErasmus.setDataInizio("24/01/2019");
        mobilitaErasmus.setDataFine("26/06/2019");
        mobilitaErasmus.setStato("in corso");
        mobilitaErasmus.setLearningAgreement(1);
        mobilitaErasmus.setReceivingInstitute(new ReceivingInstitute());
        mobilitaErasmus.getReceivingInstitute().setId(1);
        mobilitaErasmus.setSendingInstitute( new SendingInstitute());
        mobilitaErasmus.getReceivingInstitute().setId(1);
        mobilitaErasmus.setSendingInstitute( new SendingInstitute("15478", "Fisciano", "Unisa"));

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
        mobilitaErasmus.setReceivingInstitute(new ReceivingInstitute("giorgio", "gio@gmail.com", "media",
            "franco", "fra@gmail.com", "www.unisa.it", 1));
        mobilitaErasmus.setSendingInstitute( new SendingInstitute("15478", "Fisciano", "Unisa"));

        manager.doSave(mobilitaErasmus);
        List<MobilitaErasmus> list = manager.doRetrieveAll();
        assertNotEquals(0, list.size());

        MobilitaErasmus bean = list.get(list.size() - 1);
        manager.doDelete(bean.getId());
    }

    @Test
    void testDoRetrieveById() {
        System.out.println("doRetrieveById");
        mobilitaErasmus = (MobilitaErasmus) manager.doRetrieveById(id);
        assertEquals(0, mobilitaErasmus.getId());
    }

    @Test
    void testDoRetrieveByIdSending() {
        System.out.println("doRetrieveByIdSending");

        mobilitaErasmus = new MobilitaErasmus();
        mobilitaErasmus.setDataInizio("24/01/2019");
        mobilitaErasmus.setDataFine("26/06/2019");
        mobilitaErasmus.setStato("in corso");
        mobilitaErasmus.setLearningAgreement(1);
        mobilitaErasmus.setReceivingInstitute(new ReceivingInstitute());
        mobilitaErasmus.getReceivingInstitute().setId(1);
        mobilitaErasmus.setSendingInstitute(new SendingInstitute());
        mobilitaErasmus.getReceivingInstitute().setId(1);

        manager.doSave(mobilitaErasmus);

        List<MobilitaErasmus> list = manager.doRetrieveByIdSending(mobilitaErasmus.getSendingInstitute().getId());
        Iterator<MobilitaErasmus> i = list.iterator();
        boolean ok = true;
        while (i.hasNext()) {
            MobilitaErasmus bean = i.next();
            if (bean.getSendingInstitute().getId() != 1) {
                ok = false;
            }
        }
        assertTrue(ok);

    }
}