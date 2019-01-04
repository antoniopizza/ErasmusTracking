package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Amministratore;
import main.java.it.unisa.ErasmusTracking.bean.MobilitaErasmus;
import main.java.it.unisa.ErasmusTracking.bean.ReceivingInstitute;
import main.java.it.unisa.ErasmusTracking.model.jpa.*;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static String db = "ErasmusTracking";
    static String username = "root";
    static String password = "root";
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String primaryKey = request.getParameter("primaryKey");
        String table = request.getParameter("table");

        System.out.println("Pk= " + primaryKey + ",tabella= " + table + ".");
        boolean result = false ;

        switch (table) {
            case "account": {

                AccountManager manager = new AccountManager(db,username,password);
                System.out.println("Tabella " + table );

                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case "amministratore": {
                AmministratoriManager manager = new AmministratoriManager(db,username,password);
                System.out.println("Tabella " + table);

                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            case "documenti": {
                DocumentiManager manager = new DocumentiManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case "ticket": {
                TicketManager manager = new TicketManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case "messaggioTicket": {
                Messaggio_TicketManager manager = new Messaggio_TicketManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }

            case "studente": {
                StudenteManager manager = new StudenteManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "learningAgreement": {
                LearningAgreementManager manager = new LearningAgreementManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "location": {
                LocalitaManager manager = new LocalitaManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "sendingInstitute": {
                SendingInstituteManager manager = new SendingInstituteManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "coordinatore": {
                CoordinatoriManager manager = new CoordinatoriManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "receivingInstitute": {
                ReceivingInstituteManager manager = new ReceivingInstituteManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "mobilitaErasmus": {
                MobilitaErasmusManager manager = new MobilitaErasmusManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "mappingEsame": {
                MappingEsameManager manager = new MappingEsameManager(db,username,password);
                System.out.println("Tabella" + table);
                try {
                    result = manager.doDelete(Integer.parseInt(primaryKey));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            default : {
                System.out.println("Tabella sconosciuta. Impossibile eliminare.");
                break;
            }
        }
        System.out.println("Deleted item [PrimaryKey:" + primaryKey +"] from table " + table +": " + result);
        //Trasferisco sulla pagina di inserimento
        RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/DynamicTab?tab=" + table);
        dispositivo.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}