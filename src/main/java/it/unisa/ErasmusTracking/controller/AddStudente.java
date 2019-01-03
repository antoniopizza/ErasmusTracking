package main.java.it.unisa.ErasmusTracking.controller;



import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.model.dao.IDocumentoDao;
import main.java.it.unisa.ErasmusTracking.model.dao.ILocalitaDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IStudenteDao;
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
    static String db = "ErasmusTracking";
    static String username = "root";
    static String password = "root";

    static IStudenteDao manager = new StudenteManager(db, username, password);

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
        String data_di_nascita = request.getParameter("dataDiNascita");
        String luogoDiNascita = request.getParameter("luogoDiNascita");
        String sesso = request.getParameter("sesso");
        String nazionalita = request.getParameter("nazionalita");
        String telefono = request.getParameter("telefono");
        String ciclo_studi = request.getParameter("cicloStudi");
        int annoAccademico = Integer.parseInt(request.getParameter("annoAccademico"));

        Studente studente = new Studente();

       studente.setMatricola(matricola);
       studente.setDataDiNascita(data_di_nascita);
       studente.setLuogoDiNascita(luogoDiNascita);
       studente.setSesso(sesso);
       studente.setNazionalita(nazionalita);
       studente.setTelefono(telefono);
       studente.setCicloDiStudi(ciclo_studi);
       studente.setAnnoAccademico(annoAccademico);



        try {
            manager.doSave(studente);

        } catch(NullPointerException e){
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //DA MODIFICARE NON APPENA CI SONO LE JSP
        RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
        dispositivo.forward(request, response);

    }

}
