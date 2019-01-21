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


public class StudenteServletTest extends Mockito {
    private StudenteServlet servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        System.out.println("print");
        servlet = new StudenteServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void doGet() throws ServletException, IOException {

        request.addParameter("action","doRetrieveById");
        request.addParameter("id","2");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet1() throws ServletException, IOException {

        request.addParameter("action","doRetrieveByEmail");
        request.addParameter("email","pascale@gmail.com");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet2() throws ServletException, IOException {

        request.addParameter("action","doRetrieveAll");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet3() throws ServletException, IOException {
        Account account = new Account();
        account.setId(1);
        account.setEmail("fferrucci@gmail.it");
        account.setNome("Filomena");
        account.setCognome("Ferrucci");
        account.setPassword("root");
        account.setRuolo("coordinatore");

        HttpSession session = request.getSession();
        session.setAttribute("utente", account);
        request.addParameter("action","doRetrieveByCoordinatore");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet4() throws ServletException, IOException {
        Account account = new Account();
        account.setId(2);
        account.setEmail("fferrucci@gmail.it");
        account.setNome("Filomena");
        account.setCognome("Ferrucci");
        account.setPassword("root");
        account.setRuolo("coordinatore");

        HttpSession session = request.getSession();
        session.setAttribute("utente", account);
        request.addParameter("action","doUpdateLearningAgreement");
        request.addParameter("nome","Alberto");
        request.addParameter("cognome","Ferrucci");
        request.addParameter("email","pascale@gmail.com");
        request.addParameter("data_di_nascita","01/01/1990");
        request.addParameter("luogo_di_nascita","Salerno");
        request.addParameter("codice_materia","3");
        request.addParameter("telefono","3333333333");
        request.addParameter("anno_accademico","1");
        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet5() throws ServletException, IOException {
        Account account = new Account();
        account.setId(1);
        account.setEmail("fferrucci@gmail.it");
        account.setNome("Filomena");
        account.setCognome("Ferrucci");
        account.setPassword("root");
        account.setRuolo("coordinatore");

        HttpSession session = request.getSession();
        session.setAttribute("utente", account);
        request.addParameter("action","doRetrieveByNull");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
}