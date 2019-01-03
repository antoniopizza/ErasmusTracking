package main.java.it.unisa.ErasmusTracking.controller;



import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.model.dao.IAccountDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IDocumentoDao;
import main.java.it.unisa.ErasmusTracking.model.dao.ILocalitaDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IStudenteDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.AccountManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.LocalitaManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.StudenteManager;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddAccount")
public class AddAccount extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "ErasmusTracking";
    static String username = "root";
    static String password = "root";

    static IAccountDao manager = new AccountManager(db, username, password);

    public AddAccount() {
        super();
    }

    /**
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome= request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String ruolo = request.getParameter("ruolo");

        Account account = new Account();

        account.setNome(nome);
        account.setCognome(cognome);
        account.setEmail(email);
        account.setPassword(password);
        account.setRuolo(ruolo);

        try {
            manager.doSave(account);

        } catch(NullPointerException e){
            e.printStackTrace();
        }


        //DA MODIFICARE NON APPENA CI SONO LE JSP
        RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
        dispositivo.forward(request, response);

    }

}