package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MobilitaErasmusTest {

    @Test
    void testSetandGet()  {

       MobilitaErasmus tepk = new MobilitaErasmus();
        int id=12345678;
        String dataInizio="213467";
        String dataFine="12345678";
        String stato="in corso";
        SendingInstitute sendingInstitute = new SendingInstitute();
        ReceivingInstitute receivingInstitute = new ReceivingInstitute();
        int learningAgreement=12;


        assertNotNull(tepk);

        tepk.setId(id);
        assertEquals(id, tepk.getId());

        tepk.setDataInizio(dataInizio);
        assertEquals(dataInizio, tepk.getDataInizio());

        tepk.setDataFine(dataFine);
        assertEquals(dataFine,tepk.getDataFine());

        tepk.setStato(stato);
        assertEquals(stato,tepk.getStato());

        tepk.setSendingInstitute(sendingInstitute);
        assertEquals(sendingInstitute,tepk.getSendingInstitute());

        tepk.setReceivingInstitute(receivingInstitute);
        assertEquals(receivingInstitute,tepk.getReceivingInstitute());

        tepk.setLearningAgreement(learningAgreement);
        assertEquals(learningAgreement,tepk.getLearningAgreement());

    }
}