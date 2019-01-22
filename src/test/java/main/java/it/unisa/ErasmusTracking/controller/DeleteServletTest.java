package main.java.it.unisa.ErasmusTracking.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


class DeleteServletTest extends Mockito {
    private DeleteServlet servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        System.out.println("print");
        servlet = new DeleteServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void doGet() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","account");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet1() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","amministatore");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet2() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","documenti");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    } @Test
    public void doGet3() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","ticket");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    } @Test
    public void doGet4() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","studente");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    } @Test
    public void doGet5() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","learningAgreement");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    } @Test
    public void doGet6() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","location");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    } @Test
    public void doGet7() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","sendingInstitute");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    } @Test
    public void doGet8() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","coordinatore");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet9() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","receivingInstitute");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet10() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","mobilitaErasmus");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet11() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","mappingEsame");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet12TabellaSconoscitua() throws ServletException, IOException {
        request.addParameter("prymaryKey", "d");
        request.addParameter("table","ngInstitute");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }






}