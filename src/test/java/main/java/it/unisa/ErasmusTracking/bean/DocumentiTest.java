package main.java.it.unisa.ErasmusTracking.bean;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class DocumentiTest {

    @Test
    void testsetandget(){
        Documenti tepk = new Documenti();
        tepk.setNome("Dario");
        assertEquals("Dario", tepk.getNome());

        tepk.setId(1234);
        assertEquals(1234, tepk.getId());

        tepk.setDataCaricamento("1234567");
        assertEquals("1234567",tepk.getDataCaricamento());

        tepk.setId(12);
        assertEquals(12,tepk.getId());

        //tepk.setUrl("darioscola015@gmail.com");
        //assertEquals("darioscola015@gmail.com",tepk.getUrl());

        tepk.setProprietario(21);
        assertEquals(21,tepk.getProprietario());

        OutputStream outputStream = null;
        tepk.setOutputStream(outputStream);
        assertEquals(outputStream,tepk.getOutputStream());

        InputStream inputStream = null;
        tepk.setInputStream(inputStream);
        assertEquals(inputStream,tepk.getInputStream());

        int filesize = 12;
        tepk.setFileSize(filesize);
        assertEquals(12,tepk.getFileSize());



    }
   }