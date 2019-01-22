package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Amministratore;
import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import main.java.it.unisa.ErasmusTracking.model.jpa.AccountManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.AmministratoriManager;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AddAmministratoreTest extends Mockito {
    private AddAmministratore servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private static AmministratoriManager classUnderTest;
    private static AccountManager m;
    private static Amministratore bean;


    @BeforeAll
    static void setUp() throws SQLException {
        classUnderTest = new AmministratoriManager("erasmustracking","root","root");
        m =new AccountManager("erasmusTracking","root","root");
    }
    @BeforeEach
    public void setUp1() {
        System.out.println("print");
        servlet = new AddAmministratore();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    public void doPost() throws ServletException, IOException {

        request.addParameter("account","1");


        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());

        ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
        bean = list.get(list.size()-1);
        assertNotEquals(0,list.size());

        boolean ok = false;
        try {
            classUnderTest.doDelete(bean.getId());
            m.doDelete(bean.getId());
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }
    }
    @Test
    public void doGet() throws ServletException, IOException {
        request.addParameter("account","1");

        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());

        ArrayList<Amministratore> list = (ArrayList<Amministratore>) classUnderTest.doRetrieveAll();
        bean = list.get(list.size()-1);
        assertNotEquals(0,list.size());

        boolean ok = false;
        try {
            classUnderTest.doDelete(bean.getId());
            m.doDelete(bean.getId());
            ok = true;
        } catch (Exception e) {
            e.printStackTrace();
            ok = false;
        }

    }
}