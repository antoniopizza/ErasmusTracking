package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import main.java.it.unisa.ErasmusTracking.model.jpa.AccountManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.CoordinatoriManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.SendingInstituteManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AddCoordinatoreTest extends Mockito {

  private AddCoordinatore servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private static CoordinatoriManager classUnderTest;
  private static SendingInstituteManager sendingInstituteManager;
  private static AccountManager m;
  private static Coordinatore bean;
  private static SendingInstitute sendingInstitute;

  @BeforeAll
  static void setUp() throws SQLException {
    classUnderTest = new CoordinatoriManager("erasmustracking","root","root");
    m =new AccountManager("erasmusTracking","root","root");
    sendingInstituteManager = new SendingInstituteManager("erasmusTracking","root","root");
  }
  @BeforeEach
  public void setUp1() {
    System.out.println("print");
    servlet = new AddCoordinatore();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }

  @Test
  public void doPostUpdate() throws ServletException, IOException {
    Account account = new Account();

    HttpSession session = request.getSession();
    session.setAttribute("utente", account);

    request.addParameter("update", "1");
    request.addParameter("page", "profile");
    request.addParameter("nome","nome");
    request.addParameter("cognome", "cognome");


    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    boolean ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
  }

  @Test
  public void doPostUpdateUtente() throws ServletException, IOException {
    Account account = new Account();

    HttpSession session = request.getSession();
    session.setAttribute("utente", account);

    request.addParameter("update", "1");
    request.addParameter("page", "utente");
    request.addParameter("nome","nome");
    request.addParameter("cognome", "cognome");
    request.addParameter("email", "email@mail.com");
    request.addParameter("password", "root");


    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    boolean ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
  }

  @Test
  public void doPostUpdate2() throws ServletException, IOException {
    request.addParameter("update", "2");
    request.addParameter("nome","nome");
    request.addParameter("cognome", "cognome");
    request.addParameter("email", "email@mail.com");
    request.addParameter("password", "root");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    boolean ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
  }
  @Test
  public void doPostUpdateerr() throws ServletException, IOException {
    request.addParameter("update", "1");
    request.addParameter("nome","nome");
    request.addParameter("cognome", "cognome");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    boolean ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
  }
  @Test
  public void doPostUpdate2err() throws ServletException, IOException {
    request.addParameter("update", "2");
    request.addParameter("nome","nome");
    request.addParameter("cognome", "cognome");
    request.addParameter("email", "email@mail.com");
    request.addParameter("password", "root");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());

    ArrayList<Coordinatore> listCoordinatori = (ArrayList<Coordinatore>) classUnderTest.doRetrieveAll();
    assertNotEquals(0, listCoordinatori.size());
    bean = listCoordinatori.get(listCoordinatori.size() - 1);

    boolean ok = false;
    try {
      classUnderTest.doDelete(bean.getId());
      m.doDelete(bean.getId());
      sendingInstituteManager.doDelete(sendingInstitute.getId());
      ok = true;
    } catch (Exception e) {
      e.printStackTrace();
      ok = false;
    }
  }
}