/*package main.java.it.unisa.ErasmusTracking.controller;



import main.java.it.unisa.ErasmusTracking.bean.*;
import main.java.it.unisa.ErasmusTracking.model.dao.*;
import main.java.it.unisa.ErasmusTracking.model.jpa.*;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddMessaggioTicket")
public class AddMessaggioTicket extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "ErasmusTracking";
    static String username = "root";
    static String password = "root";

    static IMessaggioDao manager = new Messaggio_TicketManager(db, username, password);

    public AddMessaggioTicket() {
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
  /*  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contenuto= request.getParameter("contenuto");
        String dataInvio = request.getParameter("dataCreazione");
        String oraInvio = request.getParameter("oraInvio");

        int ticket = Integer.parseInt(request.getParameter("ticket"));
        int proprietario = Integer.parseInt(request.getParameter("proprietario"));

        Messaggio_Ticket messaggio_ticket = new Messaggio_Ticket();

        messaggio_ticket.setContenuto(contenuto);
        messaggio_ticket.setData_invio(dataInvio);
        messaggio_ticket.set


        try {
            manager.doSave(ticket);

        } catch(NullPointerException e){
            e.printStackTrace();
        }


        //DA MODIFICARE NON APPENA CI SONO LE JSP
        RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
        dispositivo.forward(request, response);

    }

}
    */