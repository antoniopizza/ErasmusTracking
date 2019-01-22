package main.java.it.unisa.ErasmusTracking.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DocumentiServletTest extends Mockito {
    private DocumentiServlet servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        System.out.println("print");
        servlet = new DocumentiServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();

    }

    @Test
    public void doGet() throws ServletException, IOException {
        request.addParameter("action", "doRetrieveById");
        request.addParameter("id", "1");



        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet1() throws ServletException, IOException {
        request.addParameter("action", "doRetrieveDocumentByUsernameStudent");
        request.addParameter("username", "pascal@gmail.com");



        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet2() throws ServletException, IOException {
        request.addParameter("action", "doRetrieveAll");
        request.addParameter("id", "1");



        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet3() throws ServletException, IOException {
        request.addParameter("action", "doRetrieveByIdAccount");
        request.addParameter("id", "1");



        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
    @Test
    public void doGet4() throws ServletException, IOException {
        request.addParameter("action", "downloadById");
        request.addParameter("id", "1");



        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
    }
}