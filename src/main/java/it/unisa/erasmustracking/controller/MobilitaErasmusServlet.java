package main.java.it.unisa.erasmustracking.controller;

import main.java.it.unisa.erasmustracking.bean.LearningAgreement;
import main.java.it.unisa.erasmustracking.bean.MobilitaErasmus;
import main.java.it.unisa.erasmustracking.bean.ReceivingInstitute;
import main.java.it.unisa.erasmustracking.bean.SendingInstitute;
import main.java.it.unisa.erasmustracking.model.dao.IMobilitaErasmusDao;
import main.java.it.unisa.erasmustracking.model.dao.IReceivingInstituteDao;
import main.java.it.unisa.erasmustracking.model.dao.ISendingInstituteDao;
import main.java.it.unisa.erasmustracking.model.jpa.MobilitaErasmusManager;
import main.java.it.unisa.erasmustracking.model.jpa.ReceivingInstituteManager;
import main.java.it.unisa.erasmustracking.model.jpa.SendingInstituteManager;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MobilitaErasmusServlet")
public class MobilitaErasmusServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static IMobilitaErasmusDao manager = new MobilitaErasmusManager(db, username, password);


    public MobilitaErasmusServlet() {
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




        try {
            if (action != null) {
                 if (action.equalsIgnoreCase("doRetrieveById")){
                    int id = Integer.parseInt(request.getParameter("id"));
                    MobilitaErasmus mobilitaErasmus = (MobilitaErasmus) manager.doRetrieveById(id);

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

