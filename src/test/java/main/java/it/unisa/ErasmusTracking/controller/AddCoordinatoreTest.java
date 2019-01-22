package main.java.it.unisa.ErasmusTracking.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class AddCoordinatoreTest extends Mockito {

    private AddCoordinatore servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    public void setUp() {
        System.out.println("print");
        servlet = new AddCoordinatore();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void doPost() throws ServletException, IOException {
        request.addParameter("nome", "fferrucci@unisa.it");
        request.addParameter("cognome", "fferrucci@unisa.it");
        request.addParameter("password", "password");
        request.addParameter("email", "fferrucci@unisa.it");
        request.addParameter("ruolo", "coordinatore");
        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());
    }
}