package main.java.it.unisa.ErasmusTracking.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;

class LoginServletTest extends Mockito{

  private LoginServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  public void setUp() {
    System.out.println("print");
    servlet = new LoginServlet();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }

  @Test
  void doPost() throws ServletException, IOException {
    System.out.println("Account non presente");
    request.addParameter("username", "root@gmail.com");
    request.addParameter("password", "Root1234");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  void doPost1() throws ServletException, IOException {
    System.out.println("account presente");

    request.addParameter("username", "chardido@gmail.com");
    request.addParameter("password", "root");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }


  @Test
  void doPost2() throws ServletException, IOException {
    System.out.println("account presente, ma password sbagliata");

    request.addParameter("username", "chardido@gmail.com");
    request.addParameter("password", "Root12345");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }

}