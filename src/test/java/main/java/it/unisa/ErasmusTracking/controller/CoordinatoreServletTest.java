package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Amministratore;
import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.model.jpa.CoordinatoriManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatoreServletTest {
  private CoordinatoreServlet servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;

  @BeforeEach
  public void setUp() {
    System.out.println("CoordinatoreServlet");
    servlet = new CoordinatoreServlet();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }

  @Test
  void doGetById() throws ServletException, IOException {
    System.out.println("action = ById");
    Coordinatore bean= new Coordinatore();
    bean.setNome("Federico");
    bean.setCognome("Ripoli");
    bean.setEmail("f.ripoli1@studenti.unisa.it");
    bean.setPassword("root");
    bean.setRuolo("amministratore");
    bean.setSendingInstitute(1);
    CoordinatoriManager manager = new CoordinatoriManager("erasmusTracking", "root", "root");
    manager.doSave(bean);
    List<Coordinatore> coordinatoreList = manager.doRetrieveAll();
    bean = coordinatoreList.get(coordinatoreList.size() - 1);
    request.addParameter("action","doRetrieveById");
    request.addParameter("id",bean.getId() + "");

    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
    manager.doDelete(bean.getId());
  }

  @Test
  void doGetByMail()  throws ServletException, IOException {
    System.out.println("action = ByMail");
    Coordinatore bean= new Coordinatore();
    bean.setNome("Federico");
    bean.setCognome("Ripoli");
    bean.setEmail("f.ripoli1@studenti.unisa.it");
    bean.setPassword("root");
    bean.setRuolo("amministratore");
    bean.setSendingInstitute(1);
    CoordinatoriManager manager = new CoordinatoriManager("erasmusTracking", "root", "root");
    manager.doSave(bean);
    List<Coordinatore> coordinatoreList = manager.doRetrieveAll();
    bean = coordinatoreList.get(coordinatoreList.size() - 1);
    request.addParameter("action","doRetrieveByEMail");
    request.addParameter("email",bean.getEmail());

    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
    manager.doDelete(bean.getId());
  }

  @Test
  void doGetAll()  throws ServletException, IOException {
    System.out.println("action = All");
    Coordinatore bean= new Coordinatore();
    bean.setNome("Federico");
    bean.setCognome("Ripoli");
    bean.setEmail("f.ripoli1@studenti.unisa.it");
    bean.setPassword("root");
    bean.setRuolo("amministratore");
    bean.setSendingInstitute(1);
    CoordinatoriManager manager = new CoordinatoriManager("erasmusTracking", "root", "root");
    List<Coordinatore> coordinatoreList = manager.doRetrieveAll();

    manager.doSave(bean);
    request.addParameter("action","doRetrieveAll");
    servlet.doPost(request, response);

    List<Coordinatore> list = (ArrayList<Coordinatore>)request.getAttribute("listaCoordinatori");

    assertEquals(coordinatoreList.size() + 1, list.size());
    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());
    manager.doDelete(bean.getId());
  }
  @Test
  void doPost() {
  }

}