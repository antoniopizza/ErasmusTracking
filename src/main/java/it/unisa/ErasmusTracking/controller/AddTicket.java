package main.java.it.unisa.ErasmusTracking.controller;



import main.java.it.unisa.ErasmusTracking.bean.*;
import main.java.it.unisa.ErasmusTracking.model.dao.*;
import main.java.it.unisa.ErasmusTracking.model.jpa.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AddTicket")
public class AddTicket extends HttpServlet {
    private static final long serialVersionUID = 1L;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static ITicketDao manager = new TicketManager(db, username, password);
    static IStudenteDao studenteManager = new StudenteManager(db, username, password);
    static ICoordinatoreDao coordinatoreManager = new CoordinatoriManager(db, username, password);

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

        HttpSession session = request.getSession();

        String oggetto= request.getParameter("oggetto");
        // String dataCreazione = request.getParameter("dataCreazione");
        Account account = (Account) session.getAttribute("account");

        int mittente = account.getId();

        Studente studente = (Studente) studenteManager.doRetrieveByEmail(account.getEmail());
        Coordinatore coordinatore = (Coordinatore) coordinatoreManager.doRetrieveById(studente.getIdCoordinatore());

        boolean stato = true;

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dateFormatted = date.format(formatter); //data in dd/mm/yyyy
        System.out.println(dateFormatted);
        Ticket ticket = new Ticket();

        ticket.setObject(oggetto);
        ticket.setDatacreazione(dateFormatted);
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