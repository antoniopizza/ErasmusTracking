package main.java.it.unisa.ErasmusTracking.controller;

import main.java.it.unisa.ErasmusTracking.bean.Ticket;
import main.java.it.unisa.ErasmusTracking.model.dao.ITicketDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.TicketManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/TicketServlet")
public class TicketServlet {
    private static final long serialVersionUID =  76543;

    static boolean isDataSource = true;
    static String db = "ErasmusTracking";
    static String username = "root";
    static String password = "root";

    static ITicketDao manager =  new TicketManager(db, username, password);


    public TicketServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Riceve il parametro per capire quale azione effettuare
        String action = request.getParameter("action");
        //Riceve la
        String page = request.getParameter("page");


        System.out.println("Aggiunto in pagina: " + page);


        try {
            if (action != null) {
                if (action.equalsIgnoreCase("save")) {
                    int mittente = Integer.parseInt(request.getParameter("id_mittente"));
                    String data_caricamento = request.getParameter("data_caricamento");
                    String url = request.getParameter("url");
                    int destinatario = Integer.parseInt(request.getParameter("id_destinatario"));
                    Ticket ticket = new Ticket();
                    ticket.setDestinatario(Integer.parseInt(request.getParameter("id_destinatario")));
                    ticket.setStato(true);
                    ticket.getDataCreazione();
                    ticket.getData_invio();
                    ticket.getMittente();

                    manager.doSave(ticket);


                 //     RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    //  dispositivo.forward(request, response);


                } else if (action.equalsIgnoreCase("close")) {
                    int id = Integer.parseInt(request.getParameter("id"));

                    manager.doDelete(id);


                //    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                //    dispositivo.forward(request, response);

                } else if (action.equalsIgnoreCase("doRetrieveById")){
                    int id = Integer.parseInt(request.getParameter("id"));

                    Ticket ticket =(Ticket) manager.doRetrieveById(id);
                    request.removeAttribute("ticket");
                    request.setAttribute("ticket", ticket);


                //    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
                    //      dispositivo.forward(request, response);




                }
            }
            }catch (Exception e){
            System.out.println("TicketServlet.java] Errore: "+ e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }



}



