package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class LearningAgreementTest {
    @Test
    void testSetandGet() {
        ArrayList<MappingEsame> listaesami = new ArrayList<>();
        Studente studentetest = new Studente();
        ReceivingInstitute receivingInstitutetest = new ReceivingInstitute();
        SendingInstitute sendingInstitutetest = new SendingInstitute();
        LearningAgreement tepk = new LearningAgreement(12, "1234", "5678","da convalidare","estero",listaesami,studentetest,"italiano","0512104543",receivingInstitutetest,sendingInstitutetest );

        assertNotNull(tepk);

        tepk.setId(12);
        assertEquals(12, tepk.getId());

       tepk.setDataInizio("1234");
        assertEquals("1234", tepk.getDataInizio());

        tepk.setDataFine("5678");
        assertEquals("5678",tepk.getDataFine());

        tepk.setStato("da convalidare");
        assertEquals("da convalidare",tepk.getStato());

        tepk.setTipologiaErasmus("estero");
        assertEquals("estero",tepk.getTipologiaErasmus());

        tepk.setListaEsami(listaesami);
        assertEquals(listaesami,tepk.getListaEsami());

        tepk.setStudente(studentetest);
        assertEquals(studentetest,tepk.getStudente());

        tepk.setConoscenzaLingua("italiano");
        assertEquals("italiano",tepk.getConoscenzaLingua());

        tepk.setMatricolaStudente("051210456");
        assertEquals("051210456",tepk.getMatricolaStudente());

        tepk.setReceivingInstitute(receivingInstitutetest);
        assertEquals(receivingInstitutetest,tepk.getReceivingInstitute());

        tepk.setSendingInstitute(sendingInstitutetest);
        assertEquals(sendingInstitutetest,tepk.getSendingInstitute());
    }

}