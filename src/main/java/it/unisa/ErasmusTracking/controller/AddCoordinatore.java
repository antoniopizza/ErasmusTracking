package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Coordinatore;
import main.java.it.unisa.ErasmusTracking.model.dao.ICoordinatoreDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.CoordinatoriManager;



@WebServlet("/AddCoordinatore")
public class AddCoordinatore extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static ICoordinatoreDao manager = new CoordinatoriManager(db, username, password);

  public AddCoordinatore() {
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
   *doPst.
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

    Coordinatore coordinatore = new Coordinatore();
    Account utente = (Account) request.getSession().getAttribute("utente");

    String page = request.getParameter("page");
    String update = request.getParameter("update");

    if (update.equalsIgnoreCase("1")) {

      String nome = request.getParameter("nome");
      String cognome = request.getParameter("cognome");
      coordinatore.setNome(nome);
      coordinatore.setCognome(cognome);

    } else {
      String nome = request.getParameter("nome");
      String cognome = request.getParameter("cognome");
      String email = request.getParameter("email");
      String password = request.getParameter("password");
      coordinatore.setNome(nome);
      coordinatore.setCognome(cognome);
      coordinatore.setEmail(email);
      coordinatore.setPassword(password);
      coordinatore.setRuolo("coordinatore");
      coordinatore.setSendingInstitute(1);

    }


    try {
      if (update.equalsIgnoreCase("1")) {
        coordinatore.setId(utente.getId());
        coordinatore.setEmail(utente.getEmail());
        coordinatore.setPassword(utente.getPassword());
        coordinatore.setSendingInstitute(coordinatore.getSendingInstitute());
        coordinatore.setRuolo("coordinatore");
        manager.doUpdate(coordinatore);
      } else {
        manager.doSave(coordinatore);
      }

    } catch (NullPointerException e) {
      e.printStackTrace();
    }


    if (page.equalsIgnoreCase("profile")) {
      RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher(
                      "/AccountServlet?action=doRetrieveById&id=" + coordinatore.getId());
      dispositivo.forward(request, response);
    } else if (page.equalsIgnoreCase("utente")) {
      RequestDispatcher dispositivo =
              getServletContext().getRequestDispatcher("/AccountServlet?action=doRetrieveAll");
    }
  }

}