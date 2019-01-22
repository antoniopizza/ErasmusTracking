package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.model.jpa.AccountManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddDocumentoTest extends Mockito {
    private AddDocumento servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private static DocumentiManager documentiManager;
    private static AccountManager driverclassTest;
    private static Account beanaccount;
    private static Documenti documento;
    private static final String UPLOAD_DIR = "uploads";

    @BeforeAll
    static void setUp(){
        try{
            documentiManager = new DocumentiManager("", "", "");
            driverclassTest  = new AccountManager("", "", "");
        } catch(Exception e){
            e.printStackTrace();
        }finally{
            documentiManager  = new DocumentiManager("erasmusTracking", "root", "root");
            driverclassTest  = new AccountManager("erasmusTracking", "root", "root");
        }
    }

    @BeforeEach
    public void setUp1() {
        System.out.println("print");
        servlet = new AddDocumento();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }
    @Test
    public void AddDocumento() throws ServletException, IOException {
    }

    @Test
    public void doPostEliminazioneCcartella() throws ServletException, IOException {

        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        File index = new File(uploadFilePath);
        if (!index.exists()) {
            index.mkdir();
        } else {
            index.delete();
        }

        Account account = new Account();
        account.setId(1);
        account.setNome("giovanni");
        HttpSession session = request.getSession();
        session.setAttribute("utente", account);
        request.addParameter("filename", "fferrucci@unisa.it");
        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());



        List<Documenti> list1 = documentiManager.doRetrieveAll();
        documento = list1.get(list1.size()-1);

        boolean ok = false;
        try{
            documentiManager.doDelete(documento.getId());
            ok = true;
        }catch (Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }

    @Test
    public void doPost() throws ServletException, IOException {
        Account account = new Account();
        account.setId(1);
        account.setNome("giovanni");
        HttpSession session = request.getSession();
        session.setAttribute("utente", account);
        request.addParameter("filename", "fferrucci@unisa.it");
        servlet.doGet(request, response);
        assertEquals("text/html", response.getContentType());


        List<Documenti> list1 = documentiManager.doRetrieveAll();
        documento = list1.get(list1.size()-1);

        boolean ok = false;
        try{
            documentiManager.doDelete(documento.getId());
            ok = true;
        }catch (Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }
    @Test
    public void doGet() throws ServletException, IOException {
        Account account = new Account();
        account.setId(1);
        account.setNome("giovanni");
        HttpSession session = request.getSession();
        session.setAttribute("utente", account);
        request.addParameter("filename", "fferrucci@unisa.it");

        servlet.doPost(request, response);
        assertEquals("text/html", response.getContentType());

        List<Documenti> list1 = documentiManager.doRetrieveAll();
        documento = list1.get(list1.size()-1);

        boolean ok = false;
        try{
            documentiManager.doDelete(documento.getId());
            ok = true;
        }catch (Exception e){
            e.printStackTrace();
            ok = false;
        }

        assertTrue(ok);
    }
}