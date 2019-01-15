package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.MessaggioTicket;
import main.java.it.unisa.ErasmusTracking.model.dao.IMessaggioDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.Messaggio_TicketManager;



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
   *doPost.
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

    String contenuto = request.getParameter("contenuto");

    int ticket = Integer.parseInt(request.getParameter("ticket"));
    Account account = (Account) request.getSession().getAttribute("utente");
    int proprietario = account.getId();

    MessaggioTicket messaggioTicket = new MessaggioTicket();

    LocalDate date = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String dateFormatted = date.format(formatter); // data in formato dd/mm/yyyy
    String oraInvio = date.format(DateTimeFormatter.ISO_LOCAL_TIME); // ora in fomato hh:mm:ss

    messaggioTicket.setContenuto(contenuto);
    messaggioTicket.setDataInvio(dateFormatted);
    messaggioTicket.setOraInvio(oraInvio);


    try {
      manager.doSave(ticket);

    } catch (NullPointerException e) {
      e.printStackTrace();
    }


    //DA MODIFICARE NON APPENA CI SONO LE JSP
    RequestDispatcher dispositivo = getServletContext().getRequestDispatcher("/newCliente.jsp");
    dispositivo.forward(request, response);

  }

}
