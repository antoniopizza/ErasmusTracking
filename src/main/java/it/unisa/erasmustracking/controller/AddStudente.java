package main.java.it.unisa.erasmustracking.controller;



import main.java.it.unisa.erasmustracking.bean.Account;
import main.java.it.unisa.erasmustracking.bean.Documenti;
import main.java.it.unisa.erasmustracking.bean.Studente;
import main.java.it.unisa.erasmustracking.model.dao.IAccountDao;
import main.java.it.unisa.erasmustracking.model.dao.IDocumentoDao;
import main.java.it.unisa.erasmustracking.model.dao.ILocalitaDao;
import main.java.it.unisa.erasmustracking.model.dao.IStudenteDao;
import main.java.it.unisa.erasmustracking.model.jpa.AccountManager;
import main.java.it.unisa.erasmustracking.model.jpa.DocumentiManager;
import main.java.it.unisa.erasmustracking.model.jpa.LocalitaManager;
import main.java.it.unisa.erasmustracking.model.jpa.StudenteManager;

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
        Account utente = (Account) request.getSession().getAttribute("utente");
        //System.out.println("..............AddStudente 66: "+utente.getId());
        String matricola= request.getParameter("matricola");
        String data_di_nascita = request.getParameter("data_di_nascita");

        String luogoDiNascita = request.getParameter("luogo_di_nascita");
        String sesso = request.getParameter("sesso");
        String nazionalita = request.getParameter("nazionalita");
        String telefono = request.getParameter("telefono");
        String cicloStudi = request.getParameter("ciclo_di_studi");
        String codice_materia = request.getParameter("codice_materia");
        String update = request.getParameter("update");
        //System.out.println(update);
        int coordinatore = utente.getId();
        int annoAccademico = Integer.parseInt(request.getParameter("anno_accademico"));


        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        Studente studente = new Studente();


        studente.setMatricola(matricola);
        studente.setDataDiNascita(data_di_nascita);
        studente.setLuogoDiNascita(luogoDiNascita);
        studente.setSesso(sesso);
        studente.setNazionalita(nazionalita);
        studente.setTelefono(telefono);
        studente.setCicloDiStudi(cicloStudi);
        studente.setIdCoordinatore(coordinatore);
        studente.setAnnoAccademico(annoAccademico);

        studente.setNome(nome);
        studente.setCognome(cognome);
        studente.setEmail(email);
        studente.setPassword(password);
        studente.setCodiceMateria(codice_materia);
        studente.setRuolo("studente");

        System.out.println("studente" + studente);

        try {
            if(update.equalsIgnoreCase("1")) {
                studente.setId(utente.getId());
                studente.setEmail(utente.getEmail());
                manager.doUpdate(studente);
            } else {
                manager.doSave(studente);
            }
        } catch(NullPointerException e) {
            e.printStackTrace();
        }


        //DA MODIFICARE NON APPENA CI SONO LE JSP
        RequestDispatcher dispositivo = null;

        if(update.equalsIgnoreCase("1")) {
            dispositivo = getServletContext().getRequestDispatcher("/LearningAgreementServlet?action=doRetrieveByStudente");
            dispositivo.forward(request, response);
        } else {
            dispositivo = getServletContext().getRequestDispatcher("/StudenteServlet?action=doRetrieveByCoordinatore");
            dispositivo.forward(request, response);
        }

    }

}