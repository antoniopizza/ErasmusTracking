package main.java.it.unisa.ErasmusTracking.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;

class AddSendingInstituteTest extends Mockito {
  private AddSendingInstitute servlet;
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;


  @BeforeEach
  public void setUp() {
    System.out.println("print");
    servlet = new AddSendingInstitute();
    request = new MockHttpServletRequest();
    response = new MockHttpServletResponse();
  }

  @Test
  void doPost() throws ServletException, IOException {
    request.addParameter("codiceErasmus", "05489");
    request.addParameter("indirizzo", "Fisciano");
    request.addParameter("dipartimento", "Informatica");

    servlet.doPost(request, response);
    assertEquals("text/html", response.getContentType());

  }


}