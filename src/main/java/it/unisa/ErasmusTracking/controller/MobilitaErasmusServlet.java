package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.LearningAgreement;
import main.java.it.unisa.ErasmusTracking.bean.MobilitaErasmus;
import main.java.it.unisa.ErasmusTracking.bean.ReceivingInstitute;
import main.java.it.unisa.ErasmusTracking.bean.SendingInstitute;
import main.java.it.unisa.ErasmusTracking.model.dao.IMobilitaErasmusDao;
import main.java.it.unisa.ErasmusTracking.model.dao.IReceivingInstituteDao;
import main.java.it.unisa.ErasmusTracking.model.dao.ISendingInstituteDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.MobilitaErasmusManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.ReceivingInstituteManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.SendingInstituteManager;

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


        System.out.println("Aggiunto in pagina: " + page);


        try {
            if (action != null) {
                if (action.equalsIgnoreCase("save")) {
                    String dataInizio = request.getParameter("dataInizio");
                    String dataFine = request.getParameter("dataFine");
                    String statoMobilita = request.getParameter("statoMobilita");
                    int idSendingInstitute = Integer.parseInt(request.getParameter("idSendingInstitute"));
                    int idReceivingInstitute = Integer.parseInt(request.getParameter("idReceivingInstitute"));

                    MobilitaErasmus bean = new MobilitaErasmus();
                    ISendingInstituteDao sendingInstituteManager = new SendingInstituteManager(this.db, this.username, this.password);
                    SendingInstitute sendingInstitute = (SendingInstitute) sendingInstituteManager.doRetrieveById(idSendingInstitute);
                    IReceivingInstituteDao receivingInstituteManager = new ReceivingInstituteManager(this.db, this.username, this.password);
                    ReceivingInstitute receivingInstitute = (ReceivingInstitute) receivingInstituteManager.doRetrieveById(idReceivingInstitute);

                    bean.setSendingInstitute(sendingInstitute);
                    bean.setReceivingInstitute(receivingInstitute);
                    bean.setDataInizio(dataInizio);
                    bean.setDataFine(dataFine);
                    bean.setStato(statoMobilita);

                    manager.doSave(bean);

                    //DA MODIFICARE NON APPENA CI SONO LE JSP
                    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    dispositivo.forward(request, response);
                } else if (action.equalsIgnoreCase("delete")) {
                    int id = Integer.parseInt(request.getParameter("id"));

                    manager.doDelete(id);
                } else if (action.equalsIgnoreCase("doRetrieveById")){
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

