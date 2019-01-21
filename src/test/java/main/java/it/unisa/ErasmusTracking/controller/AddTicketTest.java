package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.model.jpa.AccountManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.CoordinatoriManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.LearningAgreementManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.StudenteManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddTicketTest extends Mockito {

  private AddTicket servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private StudenteManager manager;
  private LearningAgreementManager laManager;
  private CoordinatoriManager coordinatoriManager;

  @BeforeEach
  public void setUp() {
    servlet = new AddTicket();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
    manager = new StudenteManager("erasmusTracking", "root", "root");
    laManager = new LearningAgreementManager("erasmusTracking", "root", "root");
    coordinatoriManager = new CoordinatoriManager("erasmusTracking", "root", "root");
  }

  @Test
  void doGetStudente() throws ServletException, IOException {
    System.out.println("doGet: ruolo = studente");
    request.addParameter("oggetto", "Compilazione LA");
    Studente utente = new Studente();
    utente.setNome("Federico");
    utente.setCognome("Ripoli");
    utente.setEmail("f.ripoli1@studenti.unisa.it");
    utente.setPassword("root");
    utente.setRuolo("studente");
    utente.setMatricola("0512103771");
    utente.setIdCoordinatore(1);
    manager.doSave(utente);
    List<Studente> studenteList = manager.doRetrieveAll();
    utente = studenteList.get(studenteList.size() - 1);
    HttpSession session = request.getSession();
    session.setAttribute("utente", utente);

    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());

    LearningAgreement la = laManager.doRetrieveByStudente(utente.getId());
    laManager.doDelete(la.getId());
    manager.doDelete(utente.getId());
  }

  @Test
  void doGetCoordinatore() throws ServletException, IOException {
    System.out.println("doGet: ruolo = coordinatore");
    request.addParameter("oggetto", "Compilazione LA");
    Coordinatore utente = new Coordinatore();
    utente.setNome("Federico");
    utente.setCognome("Ripoli");
    utente.setEmail("f.ripoli1@studenti.unisa.it");
    utente.setPassword("root");
    utente.setRuolo("coordinatore");
    utente.setSendingInstitute(1);
    coordinatoriManager.doSave(utente);

    List<Coordinatore> coordinatoreList = (ArrayList<Coordinatore>) coordinatoriManager.doRetrieveAll();
    utente = coordinatoreList.get(coordinatoreList.size() - 1);
    HttpSession session = request.getSession();
    session.setAttribute("utente", utente);
    request.addParameter("studente", 2 + "");

    servlet.doGet(request, response);
    assertEquals("text/html", response.getContentType());

    coordinatoriManager.doDelete(utente.getId());
  }

  @Test
  void doPost() {

  }


}