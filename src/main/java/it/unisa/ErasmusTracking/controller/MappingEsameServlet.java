package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Esame;
import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.bean.MappingEsame;
import main.java.it.unisa.ErasmusTracking.model.dao.IMappingEsameDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.DocumentiManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.MappingEsameManager;

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

@WebServlet("/MappingEsameServlet")
public class MappingEsameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static IMappingEsameDao manager = new MappingEsameManager(db, username, password);


    public MappingEsameServlet() {
        super();
    }



    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Riceve il parametro per capire quale azione effettuare
        String action = request.getParameter("action");
        System.out.println("MApping esame 46: "+action);

        try {
            if (action != null) {
                 if (action.equalsIgnoreCase("doRetrieveById")){
                    int id = Integer.parseInt(request.getParameter("id"));

                    MappingEsame mappingEsame = (MappingEsame) manager.doRetrieveById(id);

                } else if (action.equalsIgnoreCase("doRetrieveByLearningAgreement")){
                     LearningAgreement learningAgreement = (LearningAgreement) request.getAttribute("learningAgreement");
                     System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");

                    List<MappingEsame> mappingEsame = manager.doRetrieveByLearningAgreement(learningAgreement.getId());

                    System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+mappingEsame.toString());
                    request.setAttribute("mappingEsame", mappingEsame);

                     RequestDispatcher dispositivo =
                             getServletContext().getRequestDispatcher("/learning-agreement.jsp");
                     dispositivo.forward(request, response);

                 } else if (action.equalsIgnoreCase("update")) {

                 }
            }
        } catch (Exception e){
            System.out.println("[MappingEsameServlet.java] Errore: "+ e);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }



}

