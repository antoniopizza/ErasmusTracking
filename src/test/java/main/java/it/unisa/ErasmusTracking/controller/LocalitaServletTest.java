package main.java.it.unisa.ErasmusTracking.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.Localita;
import main.java.it.unisa.ErasmusTracking.model.jpa.LocalitaManager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;

class LocalitaServletTest extends Mockito{

  private LocalitaServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  public void setUp() {
    System.out.println("print");
    servlet = new LocalitaServlet();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }


  @Test
  void doPostById() throws ServletException, IOException {
    System.out.println("action = doRetrieveById");
    Localita localita = new Localita();
    localita.setCitta("Roma");
    localita.setCodiceErasmus("1546");
    localita.setCoordinatore(1);
    localita.setNome("UniRM");
    localita.setNazione("Italia");
    LocalitaManager manager = new LocalitaManager("erasmusTracking", "root", "root");
    manager.doSave(localita);

    request.addParameter("action","doRetrieveById");
    request.addParameter("id","1");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());

  }

  @Test
  void doPostByCity() throws ServletException, IOException {
    System.out.println("action = doRetrieveByCity");
    Localita localita = new Localita();
    localita.setCitta("Roma");
    localita.setCodiceErasmus("1546");
    localita.setCoordinatore(1);
    localita.setNome("UniRM");
    localita.setNazione("Italia");
    LocalitaManager manager = new LocalitaManager("erasmusTracking", "root", "root");
    manager.doSave(localita);

    request.addParameter("action","doRetrieveByCity");
    request.addParameter("citta","Roma");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());

  }

  @Test
  void doPostByNation() throws ServletException, IOException {
    System.out.println("action = doRetrieveByNation");
    Localita localita = new Localita();
    localita.setCitta("Roma");
    localita.setCodiceErasmus("1546");
    localita.setCoordinatore(1);
    localita.setNome("UniRM");
    localita.setNazione("Italia");
    LocalitaManager manager = new LocalitaManager("erasmusTracking", "root", "root");
    manager.doSave(localita);

    request.addParameter("action","doRetrieveByNation");
    request.addParameter("nazione","Italia");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());

  }

  @Test
  void doPostAll() throws ServletException, IOException {
    System.out.println("action = doRetrieveAll");
    Localita localita = new Localita();
    localita.setCitta("Roma");
    localita.setCodiceErasmus("1546");
    localita.setCoordinatore(1);
    localita.setNome("UniRM");
    localita.setNazione("Italia");
    LocalitaManager manager = new LocalitaManager("erasmusTracking", "root", "root");
    manager.doSave(localita);

    request.addParameter("action","doRetrieveAll");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());

  }
}