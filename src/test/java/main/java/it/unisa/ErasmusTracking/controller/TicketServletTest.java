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


public class TicketServletTest extends Mockito {
    private TicketServlet servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        System.out.println("print");
        servlet = new TicketServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void doGet() throws ServletException, IOException {

        request.addParameter("action","doRetrieveById");
        request.addParameter("id","1");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet1() throws ServletException, IOException {

        request.addParameter("action","doRetrieveByIdCoordinatore");
        Account account = new Account();
        account.setId(1);
        account.setEmail("fferrucci@gmail.it");
        account.setNome("Filomena");
        account.setCognome("Ferrucci");
        account.setPassword("root");
        account.setRuolo("coordinatore");

        HttpSession session = request.getSession();
        session.setAttribute("utente", account);

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet2() throws ServletException, IOException {

        request.addParameter("action","doRetrieveByIdStudente");
        Account account = new Account();
        account.setId(2);
        account.setEmail("fferrucci@gmail.it");
        account.setNome("Filomena");
        account.setCognome("Ferrucci");
        account.setPassword("root");
        account.setRuolo("coordinatore");

        HttpSession session = request.getSession();
        session.setAttribute("utente", account);
        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet3() throws ServletException, IOException {
        request.addParameter("action","search");
        request.addParameter("q","ciao");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet4() throws ServletException, IOException {
        request.addParameter("action","search");
        request.addParameter("q","");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }

}
