package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Studente;
import main.java.it.unisa.ErasmusTracking.bean.Ticket;
import main.java.it.unisa.ErasmusTracking.model.dao.IStudenteDao;
import main.java.it.unisa.ErasmusTracking.model.dao.ITicketDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.StudenteManager;
import main.java.it.unisa.ErasmusTracking.model.jpa.TicketManager;

@WebServlet("/AddTicket")
public class AddTicket extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static ITicketDao manager = new TicketManager(db, username, password);
  static IStudenteDao studenteManager = new StudenteManager(db, username, password);

  public AddTicket() {
    super();
  }

  /**
   *doGet.
   *
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   *
   * @param request
   *
   * @param response
   *
   * @throws ServletException
   *
   * @throws IOException
   *
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request,response);
  }

  /**
   * doPost.
   *
   * @param request
   *
   * @param response
   *
   * @throws ServletException
   *
   * @throws IOException
   *
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    HttpSession session = request.getSession();

    String oggetto = request.getParameter("oggetto");
    Account account = (Account) session.getAttribute("utente");

    int mittente = 0;
    int destinatario = 0;
    if (account.getRuolo().equalsIgnoreCase("studente")) {
      mittente = account.getId();
      Studente studente = (Studente) studenteManager.doRetrieveById(mittente);
      destinatario = studente.getIdCoordinatore();
    } else {
      destinatario = account.getId();
      //parametro inserito da profile.jsp <input type="hidden">
      mittente = Integer.parseInt(request.getParameter("studente"));
    }

    String stato = "aperto";

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dateFormatted = date.format(formatter); //data in dd/mm/yyyy
    Ticket ticket = new Ticket();

    ticket.setObject(oggetto);
    ticket.setDatacreazione(dateFormatted);
    ticket.setMittente(mittente);
    ticket.setDestinatario(destinatario);
    ticket.setStato(stato);

    try {
      manager.doSave(ticket);

    } catch (NullPointerException e) {
      e.printStackTrace();
    }

    Collection<Ticket> tickets = manager.doRetrieveByIdStudente(mittente);

    ServletContext context = request.getSession().getServletContext();

    response.setContentType("text/html");

    //DA MODIFICARE NON APPENA CI SONO LE JSP
    RequestDispatcher dispositivo =
        context.getRequestDispatcher("/TicketServlet?action=doRetrieveByIdAccount");
    dispositivo.forward(request, response);

  }

}