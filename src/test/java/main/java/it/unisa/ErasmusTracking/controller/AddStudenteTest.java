package main.java.it.unisa.ErasmusTracking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.model.dao.IAccountDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


public class AddStudenteTest extends Mockito {
    private AddStudente servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        System.out.println("print");
        servlet = new AddStudente();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void doPost() throws ServletException, IOException {
        Account account = new Account();
        account.setId(1);
        account.setEmail("fferrucci@gmail.it");
        account.setNome("Filomena");
        account.setCognome("Ferrucci");
        account.setPassword("root");
        account.setRuolo("coordinatore");

        HttpSession session = request.getSession();
        session.setAttribute("utente", account);

        request.addParameter("update","0");
        request.addParameter("nome", "fferrucci@unisa.it");
        request.addParameter("cognome", "fferrucci@unisa.it");
        request.addParameter("password", "password");
        request.addParameter("email", "fferrucci@unisa.it");
        request.addParameter("ruolo", "studente");
        request.addParameter("matricola","12345");
        request.addParameter("data_di_nascita", "01/01/1990");
        request.addParameter("luogo_di_nascita", "Salerno");
        request.addParameter("sesso", "M");
        request.addParameter("nazionalita", "Italia");
        request.addParameter("telefono", "3333333333");
        request.addParameter("anno_accademico", "1");
        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doPost1() throws ServletException, IOException {
        Account account = new Account();
        account.setId(1);
        account.setEmail("fferrucci@gmail.it");
        account.setNome("Filomena");
        account.setCognome("Ferrucci");
        account.setPassword("root");
        account.setRuolo("coordinatore");

        HttpSession session = request.getSession();
        session.setAttribute("utente", account);
        request.addParameter("page","learning-agreement");
        request.addParameter("update","1");
        request.addParameter("nome", "fferrucci@unisa.it");
        request.addParameter("cognome", "fferrucci@unisa.it");
        request.addParameter("password", "password");
        request.addParameter("email", "fferrucci@unisa.it");
        request.addParameter("ruolo", "studente");
        request.addParameter("matricola","12345");
        request.addParameter("data_di_nascita", "01/01/1990");
        request.addParameter("luogo_di_nascita", "Salerno");
        request.addParameter("sesso", "M");
        request.addParameter("nazionalita", "Italia");
        request.addParameter("telefono", "3333333333");
        request.addParameter("anno_accademico", "1");
        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doPost2() throws ServletException, IOException {
        Account account = new Account();
        account.setId(1);
        account.setEmail("fferrucci@gmail.it");
        account.setNome("Filomena");
        account.setCognome("Ferrucci");
        account.setPassword("root");
        account.setRuolo("coordinatore");

        HttpSession session = request.getSession();
        session.setAttribute("utente", account);
        request.addParameter("page","profile");
        request.addParameter("update","1");
        request.addParameter("nome", "fferrucci@unisa.it");
        request.addParameter("cognome", "fferrucci@unisa.it");
        request.addParameter("password", "password");
        request.addParameter("email", "fferrucci@unisa.it");
        request.addParameter("ruolo", "studente");
        request.addParameter("matricola","12345");
        request.addParameter("data_di_nascita", "01/01/1990");
        request.addParameter("luogo_di_nascita", "Salerno");
        request.addParameter("sesso", "M");
        request.addParameter("nazionalita", "Italia");
        request.addParameter("telefono", "3333333333");
        request.addParameter("anno_accademico", "1");
        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());
    }
}