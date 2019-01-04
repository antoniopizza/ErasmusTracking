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

@WebServlet("/AddStudente")
public class AddStudente extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static IStudenteDao manager = new StudenteManager(db, username, password);
    static IAccountDao account = new AccountManager(db, username, password);


    public AddStudente() {
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

        String matricola= request.getParameter("matricola");
        /*
            String data_di_nascita = request.getParameter("dataDiNascita");
            String luogoDiNascita = request.getParameter("luogoDiNascita");
            String sesso = request.getParameter("sesso");
            String nazionalita = request.getParameter("nazionalita");
            String telefono = request.getParameter("telefono");

        */

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        Studente studente = new Studente();


        studente.setMatricola(matricola);

        studente.setDataDiNascita("01/12/2018");
        studente.setLuogoDiNascita("0");
        studente.setSesso("0");
        studente.setNazionalita("0");
        studente.setTelefono("0");
        studente.setCicloDiStudi("0");
        studente.setAnnoAccademico(1);

        studente.setNome(nome);
        studente.setCognome(cognome);
        studente.setEmail(email);
        studente.setPassword(password);
        studente.setRuolo("studente");



        try {
            account.doSave((Account) studente);
            manager.doSave(studente);
        } catch(NullPointerException e) {
            e.printStackTrace();
        }


        //DA MODIFICARE NON APPENA CI SONO LE JSP
        RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/AccountServlet?action=doRetrieveAll");
        dispositivo.forward(request, response);

    }

}