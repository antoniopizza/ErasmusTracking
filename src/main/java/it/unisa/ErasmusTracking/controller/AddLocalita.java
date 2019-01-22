package main.java.it.unisa.ErasmusTracking.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.it.unisa.ErasmusTracking.bean.Account;
import main.java.it.unisa.ErasmusTracking.bean.Localita;
import main.java.it.unisa.ErasmusTracking.model.dao.ILocalitaDao;
import main.java.it.unisa.ErasmusTracking.model.jpa.LocalitaManager;


@WebServlet("/AddLocalita")
public class AddLocalita extends HttpServlet {
  private static final long serialVersionUID = 1L;

  static boolean isDataSource = true;
  static String db = "erasmusTracking";
  static String username = "root";
  static String password = "root";

  static ILocalitaDao manager = new LocalitaManager(db, username, password);

  public AddLocalita() {
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

    Account utente = (Account) request.getSession().getAttribute("utente");
    int coordinatore = utente.getId();
    Localita localita = new Localita();
    localita.setNome(request.getParameter("nome"));
    localita.setCitta(request.getParameter("indirizzo"));
    localita.setCodiceErasmus(request.getParameter("codice_erasmus"));
    localita.setNazione(request.getParameter("nazione"));
    localita.setCoordinatore(coordinatore);

    try {
      manager.doSave(localita);
    } catch (NullPointerException e) {
      e.printStackTrace();
    }

    ServletContext context = request.getSession().getServletContext();

    response.setContentType("text/html");

    RequestDispatcher dispositivo =
        context.getRequestDispatcher("/LocalitaServlet?action=doRetrieveAll");
    dispositivo.forward(request, response);
  }
}