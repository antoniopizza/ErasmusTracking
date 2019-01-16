package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReceivingInstituteTest {

    @Test
    void testSetandGet()  {
        ReceivingInstitute tepk = new ReceivingInstitute();
         int id=12;
         int localita=34;
         String nomeContatto="carciofo";
         String emailContatto="dassssco@gmail.com";
         String sizeOfEnterprise="assai grossa";
         String nomeMentore="gandalf";
         String emailMentore="ilmagico1930@magicmail.com";
         String website="lacompagniadellanello.it";



        assertNotNull(tepk);

        /** Problema Receiving Institute */

        tepk.setId(id);
        assertEquals(id, tepk.getId());

        tepk.setLocalita(localita);
        assertEquals(localita,tepk.getLocalita());

        tepk.setNomeContatto(nomeContatto);
        assertEquals(nomeContatto,tepk.getNomeContatto());

        tepk.setEmailContatto(emailContatto);
        assertEquals(emailContatto,tepk.getEmailContatto());

        tepk.setSizeOfEnterprise(sizeOfEnterprise);
        assertEquals(sizeOfEnterprise,tepk.getSizeOfEnterprise());

        tepk.setNomeMentore(nomeMentore);
        assertEquals(nomeMentore,tepk.getNomeMentore());

        tepk.setEmailMentore(emailMentore);
        assertEquals(emailMentore,tepk.getEmailMentore());

        tepk.setWebsite(website);
        assertEquals(website,tepk.getWebsite());

    }
}