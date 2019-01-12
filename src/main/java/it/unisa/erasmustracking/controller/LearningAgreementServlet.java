package main.java.it.unisa.erasmustracking.controller;

import main.java.it.unisa.erasmustracking.bean.Account;
import main.java.it.unisa.erasmustracking.bean.Documenti;
import main.java.it.unisa.erasmustracking.bean.LearningAgreement;
import main.java.it.unisa.erasmustracking.model.dao.ILearningAgreementDao;
import main.java.it.unisa.erasmustracking.model.jpa.DocumentiManager;
import main.java.it.unisa.erasmustracking.model.jpa.LearningAgreementManager;

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

    static ILearningAgreementDao manager = new LearningAgreementManager(db, username, password);


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
                if (action.equalsIgnoreCase("doRetrieveById")){
                    int id = Integer.parseInt(request.getParameter("id"));
                    //LearningAgreement learningAgreement = manager.doRetrieveById(id);

                } else if (action.equalsIgnoreCase("doRetrieveByStudente")) {
                    Account account = (Account) request.getSession().getAttribute("utente");
                    LearningAgreement learningAgreement = manager.doRetrieveByStudente(account.getId());
                    request.removeAttribute("learningAgreement");
                    request.setAttribute("learningAgreement", learningAgreement);

                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/learning-agreement.jsp");
                    dispositivo.forward(request, response);
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

