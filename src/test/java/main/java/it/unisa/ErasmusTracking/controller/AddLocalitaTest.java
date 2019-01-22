package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class AddLocalitaTest extends Mockito {
    private AddLocalita servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        System.out.println("print");
        servlet = new AddLocalita();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void doPost() throws ServletException, IOException {
        Account account = new Account();

        HttpSession session = request.getSession();
        session.setAttribute("utente", account);
        request.addParameter("nome", "doRetrieveById");
        request.addParameter("indirizzo", "studente");
        request.addParameter("codice_erasmus", "2");
        request.addParameter("nazione", "italia");

        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());
    }

}