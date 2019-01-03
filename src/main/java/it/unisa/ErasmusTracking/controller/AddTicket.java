package main.java.it.unisa.ErasmusTracking.controller;



import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Documenti;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.bean.Ticket;
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

@WebServlet("/AddTicket")
public class AddTicket extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "ErasmusTracking";
    static String username = "root";
    static String password = "root";

    static ITicketDao manager = new TicketManager(db, username, password);

    public AddTicket() {
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

        String oggetto= request.getParameter("oggetto");
        String dataCreazione = request.getParameter("dataCreazione");
        int mittente = Integer.parseInt(request.getParameter("mittente"));
        int destinatario = Integer.parseInt(request.getParameter("destinatario"));
        boolean stato = Boolean.parseBoolean(request.getParameter("stato"));

        Ticket ticket = new Ticket();

        ticket.setObject(oggetto);
        ticket.setDatacreazione(dataCreazione);
        ticket.setMittente(mittente);


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