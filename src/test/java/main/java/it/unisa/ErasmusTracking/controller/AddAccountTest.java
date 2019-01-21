package main.java.it.unisa.ErasmusTracking.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import javax.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;


public class AddAccountTest extends Mockito {
  private AddAccount servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  public void setUp() {
    System.out.println("print");
    servlet = new AddAccount();
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