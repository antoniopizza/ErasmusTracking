package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Ticket;
import main.java.it.unisa.ErasmusTracking.model.dao.ITicketDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.TicketManager;




@WebServlet("/TicketServlet")
public class TicketServlet extends HttpServlet {
  private static final long serialVersionUID =  76543;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static ITicketDao manager =  new TicketManager(db, username, password);


  public TicketServlet() {
    super();
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    //Riceve il parametro per capire quale azione effettuare
    String action = request.getParameter("action");

    response.setContentType("text/html");
    ServletContext context = request.getSession().getServletContext();

    try {
      if (action != null) {
        if (action.equalsIgnoreCase("doRetrieveById")) {
          int id = Integer.parseInt(request.getParameter("id"));

          Ticket ticket = (Ticket) manager.doRetrieveById(id);
          request.removeAttribute("ticket");
          request.setAttribute("ticket", ticket);

          RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/ticket.jsp");
          dispositivo.forward(request, response);





        } else if (action.equalsIgnoreCase("doRetrieveByIdCoordinatore")) {
          HttpSession session = request.getSession();
          Account account = (Account) session.getAttribute("utente");

          List<?> ticket = (List<?>) manager.doRetrieveByIdCoordinatore(account.getId());
          request.removeAttribute("tickets");
          request.setAttribute("tickets", ticket);

          RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/tickets.jsp");
          dispositivo.forward(request, response);


        } else if (action.equalsIgnoreCase("doRetrieveByIdStudente")) {
          HttpSession session = request.getSession();
          Account account = (Account) session.getAttribute("utente");

          List<?> ticket = (List<?>) manager.doRetrieveByIdStudente(account.getId());
          request.removeAttribute("tickets");
          request.setAttribute("tickets", ticket);

          RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/tickets.jsp");
          dispositivo.forward(request, response);


        } else if (action.equalsIgnoreCase("search")) {
          String search = request.getParameter("q");

          if (search != "" && search != null) {
            request.setAttribute("search",search);

          }
          RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/tickets.jsp");
          dispositivo.forward(request, response);

        } else if (action.equalsIgnoreCase("close")) {
          int id = Integer.parseInt((String) request.getAttribute("id"));
          manager.doClose(id);
          RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/tickets.jsp");
          dispositivo.forward(request, response);
        }
      }
    } catch (Exception e) {
      System.out.println("TicketServlet.java] Errore: " + e);
    }
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // TODO Auto-generated method stub
    doGet(request, response);
  }



}



