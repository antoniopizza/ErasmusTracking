package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudenteTest {

    @Test
    void testSetandGet()  {
      Studente tepk = new Studente();
         String sesso="maschio";
         String dataDiNascita="12343444";
         String luogoDiNascita="Salerno";
         String nazionalita="italiana";
         String cicloDiStudi="mangiapregaama";
         String codiceMateria="prima";
         String telefono="iphonex";
         int annoAccademico=1;
         String matricola="0512109513";
         int idCoordinatore=123456789;
         //driver chiavi esterne
         int idstudente=1234567;
         String nomestudente="Dario";
         String cognomestudente="Scola";
          String emailstudente="ahaha@goo.it";
          String passwordstudente="123456";
          String ruolostudente="Studente";



        assertNotNull(tepk);
        tepk.setNome(nomestudente);
        assertEquals(nomestudente, tepk.getNome());

        tepk.setId(idstudente);
        assertEquals(idstudente, tepk.getId());

        tepk.setCognome(cognomestudente);
        assertEquals(cognomestudente, tepk.getCognome());

        tepk.setRuolo(ruolostudente);
        assertEquals(ruolostudente,tepk.getRuolo());


        tepk.setEmail(emailstudente);
        assertEquals(emailstudente,tepk.getEmail());

        tepk.setPassword(passwordstudente);
        assertEquals(passwordstudente,tepk.getPassword());

        tepk.setSesso(sesso);
        assertEquals(sesso,tepk.getSesso());

        tepk.setDataDiNascita(dataDiNascita);
        assertEquals(dataDiNascita,tepk.getDataDiNascita());

        tepk.setLuogoDiNascita(luogoDiNascita);
        assertEquals(luogoDiNascita,tepk.getLuogoDiNascita());

        tepk.setNazionalita(nazionalita);
        assertEquals(nazionalita,tepk.getNazionalita());

        tepk.setCicloDiStudi(cicloDiStudi);
        assertEquals(cicloDiStudi,tepk.getCicloDiStudi());

        tepk.setCodiceMateria(codiceMateria);
        assertEquals(codiceMateria,tepk.getCodiceMateria());

        tepk.setTelefono(telefono);
        assertEquals(telefono,tepk.getTelefono());

        tepk.setAnnoAccademico(annoAccademico);
        assertEquals(annoAccademico,tepk.getAnnoAccademico());

        tepk.setMatricola(matricola);
        assertEquals(matricola,tepk.getMatricola());

        tepk.setIdCoordinatore(idCoordinatore);
        assertEquals(idCoordinatore,tepk.getIdCoordinatore());

    }

}