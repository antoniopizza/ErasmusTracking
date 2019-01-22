package main.java.it.unisa.ErasmusTracking.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AccountServletTest extends Mockito {
  private AccountServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;


  @BeforeEach
  public void setUp() {
    System.out.println("print");
    servlet = new AccountServlet();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }

  @Test
  public void doGetStudente() throws ServletException, IOException {
    request.addParameter("action", "doRetrieveById");
    request.addParameter("ruolo", "studente");
    request.addParameter("id", "2");

    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }
  @Test
  public void doGetCoordinatore() throws ServletException, IOException {
    request.addParameter("action", "doRetrieveById");
    request.addParameter("ruolo", "coordinatore");
    request.addParameter("id", "1");

    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }
  @Test
  public void doGetAmministratore() throws ServletException, IOException {
    request.addParameter("action", "doRetrieveById");
    request.addParameter("ruolo", "amministratore");
    request.addParameter("id", "3");

    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }
  @Test
  public void doGet1() throws ServletException, IOException {
    request.addParameter("action", "doRetrieveByEmail");
    request.addParameter("email", "root@gmail.com");


    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }
  @Test
  public void doGet2() throws ServletException, IOException {
    request.addParameter("action", "doRetrieveAll");

    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }
  @Test
  public void doPost() throws ServletException, IOException{
    request.addParameter("action", "doRetrieveAll");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }
  @Test
  public void doGetError() throws ServletException, IOException {
    request.addParameter("action", "doRetrieveById");
    request.addParameter("ruolo", "amministrator");

    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
  }
  @Test
  public void doGetActionError() throws ServletException, IOException{
    request.addParameter("action", "doRetrievell");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }

  @Test
  public void doGetMethodError() throws ServletException, IOException{
    request.addParameter("action", "doRetrievell");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());
  }


}
