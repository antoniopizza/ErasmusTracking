package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Amministratore;
import main.java.it.unisa.ErasmusTracking.model.jpa.AmministratoriManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AmministratoreServletTest {

    private AmministratoreServlet servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;


    @BeforeEach
    public void setUp() {
        System.out.println("print");
        servlet = new AmministratoreServlet();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void doGetById() throws ServletException, IOException {
        System.out.println("action = ById");
        Amministratore bean= new Amministratore();
        bean.setNome("Federico");
        bean.setCognome("Ripoli");
        bean.setEmail("f.ripoli1@studenti.unisa.it");
        bean.setPassword("root");
        bean.setRuolo("amministratore");
        AmministratoriManager amministratoriManager = new AmministratoriManager("erasmusTracking", "root", "root");
        amministratoriManager.doSave(bean);
        List<Amministratore> amministratoreList = amministratoriManager.doRetrieveAll();
        bean = amministratoreList.get(amministratoreList.size() - 1);
        request.addParameter("action","doRetrieveById");
        request.addParameter("id",bean.getId() + "");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
        amministratoriManager.doDelete(bean.getId());
    }

    @Test
    void doGetByMail() throws ServletException, IOException {
        System.out.println("action = ByEmail");
        Amministratore bean= new Amministratore();
        bean.setNome("Federico");
        bean.setCognome("Ripoli");
        bean.setEmail("f.ripoli1@studenti.unisa.it");
        bean.setPassword("root");
        bean.setRuolo("amministratore");
        AmministratoriManager amministratoriManager = new AmministratoriManager("erasmusTracking", "root", "root");
        amministratoriManager.doSave(bean);
        List<Amministratore> amministratoreList = amministratoriManager.doRetrieveAll();
        bean = amministratoreList.get(amministratoreList.size() - 1);
        request.addParameter("action","doRetrieveByEmail");
        request.addParameter("email",bean.getEmail());

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
        amministratoriManager.doDelete(bean.getId());
    }

    @Test
    void doGetAll() throws ServletException, IOException {
        System.out.println("action = All");
        Amministratore bean= new Amministratore();
        bean.setNome("Federico");
        bean.setCognome("Ripoli");
        bean.setEmail("f.ripoli1@studenti.unisa.it");
        bean.setPassword("root");
        bean.setRuolo("amministratore");
        AmministratoriManager amministratoriManager = new AmministratoriManager("erasmusTracking", "root", "root");
        List<Amministratore> list = amministratoriManager.doRetrieveAll();

        amministratoriManager.doSave(bean);

        request.addParameter("action","doRetrieveAll");

        servlet.doPost(request, response);

        List<Amministratore> amministratoreList = (ArrayList<Amministratore>)request.getAttribute("listaAmministratori");

        assertEquals(list.size() + 1, amministratoreList.size());
        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());
        amministratoriManager.doDelete(bean.getId());
    }
    @Test
    void doPost() {
    }


}