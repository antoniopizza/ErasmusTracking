package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.LearningAgreementManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LearningAgreementServlet")
public class LearningAgreementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static LearningAgreementManager manager = new LearningAgreementManager(db, username, password);


    public LearningAgreementServlet() {
        super();
    }



    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Riceve il parametro per capire quale azione effettuare
        String action = request.getParameter("action");
        //Riceve la pagina che ha aggiunto l'articolo al carrello per poterci tornare
        String page = request.getParameter("page");


        System.out.println("Aggiunto in pagina: " + page);


        try {
            if (action != null) {
                if (action.equalsIgnoreCase("save")) {
                    String tipologiaErasmus = request.getParameter("tipologiaErasmus");
                    String stato = request.getParameter("stato");
                    String studente = request.getParameter("studente");
                    String conoscenzaLingua = request.getParameter("conoscenzaLingua");

                    LearningAgreement learningAgreement = new LearningAgreement();
                    learningAgreement.setStato(stato);
                    learningAgreement.setTipologiaErasmus(tipologiaErasmus);

                    /*
                    * Waiting for Studente Manager
                    *
                    * */

                    learningAgreement.setMatricolaStudente(studente);
                    learningAgreement.setConoscenzaLingua(conoscenzaLingua);

                    manager.doSave(learningAgreement);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);
                } else if (action.equalsIgnoreCase("delete")) {
                    int id = Integer.parseInt(request.getParameter("id"));

                    manager.doDelete(id);
                } else if (action.equalsIgnoreCase("doRetrieveById")){
                    int id = Integer.parseInt(request.getParameter("id"));
                    LearningAgreement learningAgreement = manager.doRetrieveById(id);

                }
            }
        } catch (Exception e){
            System.out.println("[DocumentiServlet.java] Errore: "+ e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }



}

