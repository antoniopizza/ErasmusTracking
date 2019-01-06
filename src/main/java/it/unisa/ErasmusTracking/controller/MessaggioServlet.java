package main.java.it.unisa.ErasmusTracking.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import main.java.it.unisa.ErasmusTracking.bean.Messaggio_Ticket;
import main.java.it.unisa.ErasmusTracking.model.dao.IMessaggioDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.Messaggio_TicketManager;


@WebServlet("/MessaggioServlet")
public class MessaggioServlet{

    private static final long serialVersionUID = 798765543;

    static boolean isDataSource = true;
    static String db = "erasmusTracking";
    static String username = "root";
    static String password = "root";

    static IMessaggioDao manager = new Messaggio_TicketManager(db, username, password);


    public MessaggioServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Riceve il parametro per capire quale azione effettuare
        String action = request.getParameter("action");
        //Riceve la pagina che ha aggiunto l'articolo al carrello per poterci tornare
        String page = request.getParameter("page");


        System.out.println("Aggiunto in pagina: " + page);

        try {
            if (action != null) {
                 if (action.equalsIgnoreCase("doRetrieveById")) { //By ticked id to read all the ticket's messages
                    int id = Integer.parseInt(request.getParameter("id_ticket"));

                    Messaggio_Ticket ticket = (Messaggio_Ticket) manager.doRetrieveById(id);
                    request.removeAttribute("ticket");
                    request.setAttribute("ticket", ticket);


                    //    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    //      dispositivo.forward(request, response);


                }
            }
        } catch (Exception e) {
            System.out.println("TicketServlet.java] Errore: " + e);
        }
    }

}

