package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class EsameTest {
    public String semestre = "primo";//driver

 Esame EsameTests = new Esame("Ingegneria del Software","1234",9,semestre);
    @Test
    void testSetandGet()  {





        assertNotNull(EsameTests);

        EsameTests.setNome("Dario");
        assertEquals("Dario", EsameTests.getNome());

        EsameTests.setId(12);
        assertEquals(12,EsameTests.getId());

        EsameTests.setCodice("CODE");
        assertEquals("CODE", EsameTests.getCodice());

        EsameTests.setCreditiFormativi(9);
        assertEquals(9,EsameTests.getCreditiFormativi());

        EsameTests.setSemestre(semestre);
        assertEquals(semestre,EsameTests.getSemestre());


    }
    @Test
    public String tostringtest(){
        String semestre = "primo" ;
        Esame tepk = new Esame();
        //riempo Esame Drivers
        int id= 1234;
        String nomeesame="Dario";
        String codicesame= "ciaociao";
        int creditiformativi = 9;
        tepk.setNome(nomeesame);
        tepk.setId(id);
        tepk.setCodice(codicesame);
        tepk.setCreditiFormativi(creditiformativi);
        tepk.setSemestre(semestre);



        String expected = "id= " + id + "\n"
                +
                "nome esame= " + nomeesame + "\n"
                +
                "codice esame= " + codicesame + "\n"
                +
                "creditiFormativi= " + creditiformativi + "\n"
                +
                "Semestre= " + semestre + "\n"; // put the expected value here
        assertEquals(expected, tepk.toString());
        return tepk.toString();



    }

}